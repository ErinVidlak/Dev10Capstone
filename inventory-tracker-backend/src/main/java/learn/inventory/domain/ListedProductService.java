package learn.inventory.domain;

import learn.inventory.data.ListedProductRepository;
import learn.inventory.models.ListedProduct;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class ListedProductService {

    private final ListedProductRepository repository;

    public ListedProductService(ListedProductRepository repository) {
        this.repository = repository;
    }

    public ListedProduct findById(int listedProductId){
        return repository.findById(listedProductId);
    }

    public List<ListedProduct> findAll(){
        return repository.findAll();
    }

    public Result<ListedProduct> add(ListedProduct listedProduct){
        Result<ListedProduct> result = validate(listedProduct);

        if(result.isSuccess()){
            if (listedProduct.getListedProductId() != 0) {
                result.addMessage("listedProductId cannot be set for `add` operation", ResultType.INVALID);
                return result;
            }
            result.setPayload(repository.add(listedProduct));
        }
        return result;
    }

    public Result<ListedProduct> update(ListedProduct listedProduct){
        Result<ListedProduct> result = validate(listedProduct);

       if(result.isSuccess()){
           if (listedProduct.getListedProductId() <= 0) {
               result.addMessage("listedProductId  must be set for `update` operation", ResultType.INVALID);
               return result;
           }
           if (!repository.update(listedProduct)) {
               String msg = String.format("listedProductId: %s, not found", listedProduct.getListedProductId());
               result.addMessage(msg, ResultType.NOT_FOUND);
           }
       }
        return result;
    }

    public boolean deleteById(int listedProductId) {
        return repository.deleteById(listedProductId);
    }

    private Result<ListedProduct> validate(ListedProduct listedProduct){
        Result<ListedProduct> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ListedProduct>> violations = validator.validate(listedProduct);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<ListedProduct> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        return result;
    }
    //    boolean update(ListedProduct listedProduct);
    //
    //    boolean deleteById(int listedProductId);
}
