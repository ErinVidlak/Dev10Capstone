package learn.inventory.domain;

import learn.inventory.data.ProductRepository;
import learn.inventory.models.Product;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product findById(int productId) {
        return repository.findById(productId);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Result<Product> add(Product product) {
        Result<Product> result = validate(product);
        validateNoDuplicatesExist(product, result);
        if (result.isSuccess()) {
            if (product.getProductId() != 0) {
                result.addMessage("productId cannot be set for `add` operation", ResultType.INVALID);
                return result;
            }
            result.setPayload(repository.add(product));
        }
        return result;
    }

    public Result<Product> update(Product product) {
        Result<Product> result = validate(product);

        if (result.isSuccess()) {
            if (product.getProductId() <= 0) {
                result.addMessage("productId must be set for `update` operation", ResultType.INVALID);
                return result;
            }
            if (!repository.update(product)) {
                String msg = String.format("productId: %s, not found", product.getProductId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }
        }
        return result;
    }

    public boolean deleteById(int productId) {
        return repository.deleteById(productId);
    }

    private Result<Product> validate(Product product) {
        Result<Product> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Product> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        return result;
    }

    private void validateNoDuplicatesExist(Product newProduct, Result<Product> result) {
        List<Product> products = repository.findAll();
        for (Product product : products) {
            if (newProduct.getProductName().equalsIgnoreCase(product.getProductName())
                    && newProduct.getTimeToMake() == product.getTimeToMake()
                    && newProduct.getUserId().equalsIgnoreCase(product.getUserId())
                    && newProduct.getMaterials() == product.getMaterials()) {
                result.addMessage("Products must be unique", ResultType.INVALID);
            }
        }
    }

}
