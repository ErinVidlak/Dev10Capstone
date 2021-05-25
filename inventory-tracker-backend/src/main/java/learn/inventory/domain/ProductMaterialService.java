package learn.inventory.domain;

import learn.inventory.data.ProductMaterialJdbcTemplateRepository;
import learn.inventory.data.ProductMaterialRepository;
import learn.inventory.models.Material;
import learn.inventory.models.Product;
import learn.inventory.models.ProductMaterial;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class ProductMaterialService {

    private final ProductMaterialRepository repository;

    public ProductMaterialService (ProductMaterialRepository repository) {
        this.repository = repository;
    }

    public List<ProductMaterial> findByProductId(int productId) {
        return repository.findByProductId(productId);
    }

    public Result<ProductMaterial> add(ProductMaterial productMaterial) {
        Result<ProductMaterial> result = validate(productMaterial);
        validateNoDuplicatesExist(result, productMaterial);
        if (!result.isSuccess()) {
            return result;
        }

        productMaterial = repository.add(productMaterial);
        result.setPayload(productMaterial);
        return result;
    }

    public Result<ProductMaterial> update(ProductMaterial productMaterial) {
        Result<ProductMaterial> result = validate(productMaterial);
        if (!result.isSuccess()) {
            return result;
        }

        if (productMaterial.getMaterial() == null) {
            result.addMessage("No material selected", ResultType.INVALID);
            return result;
        }

        if (productMaterial.getProductId() <= 0 ) {
            result.addMessage("productId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(productMaterial)) {
            String msg = "Did not update";
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteByKey(int productId, int materialId) {
        return repository.deleteByKey(productId, materialId);
    }

    private Result<ProductMaterial> validate(ProductMaterial productMaterial) {
        Result<ProductMaterial> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ProductMaterial>> violations = validator.validate(productMaterial);

        if (productMaterial == null) {
            result.addMessage("ProductMaterial cannot be null", ResultType.INVALID);
            return result;
        }

        if (!violations.isEmpty()) {
            for (ConstraintViolation<ProductMaterial> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        return result;
    }

    private void validateNoDuplicatesExist(Result<ProductMaterial> result, ProductMaterial productMaterial) {
        List<ProductMaterial> productMaterials = repository.findByProductId(productMaterial.getProductId());
        Material newMaterial = productMaterial.getMaterial();
        for (ProductMaterial pm : productMaterials) {
            Material material = pm.getMaterial();
            if (newMaterial.getMaterialId() == material.getMaterialId()) {
                result.addMessage("Product already is linked to this material", ResultType.INVALID);
            }
        }
    }
}
