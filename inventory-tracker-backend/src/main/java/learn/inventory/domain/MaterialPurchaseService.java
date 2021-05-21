package learn.inventory.domain;

import learn.inventory.data.MaterialPurchaseRepository;
import learn.inventory.models.ListedProduct;
import learn.inventory.models.MaterialPurchase;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class MaterialPurchaseService {

    private final MaterialPurchaseRepository repository;

    public MaterialPurchaseService(MaterialPurchaseRepository repository) {
        this.repository = repository;
    }

    //.trim on description
    public List<MaterialPurchase> findAll() {
        return repository.findAll();
    }

    public MaterialPurchase findById(int purchaseId) {
        return repository.findById(purchaseId);
    }

    public Result<MaterialPurchase> add(MaterialPurchase purchase) {
        if (purchase.getDescription() != null) {
            purchase.setDescription(purchase.getDescription().trim());
        }
        if (purchase.getUnits() != null) {
            purchase.setUnits(purchase.getUnits().trim());
        }

        Result<MaterialPurchase> result = validate(purchase);

        if (result.isSuccess()) {
            if (purchase.getPurchaseId() != 0) {
                result.addMessage("PurchaseId cannot be set for `add` operation", ResultType.INVALID);
                return result;
            }
            result.setPayload(repository.add(purchase));
        }
        return result;
    }

    public Result<MaterialPurchase> update(MaterialPurchase purchase) {
        if (purchase.getDescription() != null) {
            purchase.setDescription(purchase.getDescription().trim());
        }
        if (purchase.getUnits() != null) {
            purchase.setUnits(purchase.getUnits().trim());
        }

        Result<MaterialPurchase> result = validate(purchase);

        if (result.isSuccess()) {
            if (purchase.getPurchaseId() <= 0) {
                result.addMessage("PurchaseId  must be set for `update` operation", ResultType.INVALID);
                return result;
            }
            if (!repository.update(purchase)) {
                String msg = String.format("PurchaseId: %s, not found", purchase.getPurchaseId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }
        }
        return result;
    }

    public boolean deleteById(int purchaseId) {
        return repository.deleteById(purchaseId);
    }


    private Result<MaterialPurchase> validate(MaterialPurchase purchase) {
        Result<MaterialPurchase> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<MaterialPurchase>> violations = validator.validate(purchase);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<MaterialPurchase> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        return result;
    }
}
