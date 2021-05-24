package learn.inventory.domain;

import learn.inventory.data.MaterialInventoryRepository;
import learn.inventory.models.MaterialInventory;
import learn.inventory.models.MaterialPurchase;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class MaterialInventoryService {

    private final MaterialInventoryRepository repository;

    public MaterialInventoryService(MaterialInventoryRepository repository) {
        this.repository = repository;
    }

    public List<MaterialInventory> findAll(){
        return repository.findAll();
    }

    public MaterialInventory findById(int inventoryId){
        return repository.findById(inventoryId);
    }

    public Result<MaterialInventory> add(MaterialInventory inventory){
        Result<MaterialInventory> result = validate(inventory);
        result = checkInventoryForMaterial(result, inventory);
        if (result.isSuccess()){
            if (inventory.getInventoryId() != 0) {
                result.addMessage("InventoryId cannot be set for `add` operation", ResultType.INVALID);
                return result;
            }
            result.setPayload(repository.add(inventory));
        }
        return result;
    }

    public Result<MaterialInventory> update(MaterialInventory inventory){
        Result<MaterialInventory> result = validate(inventory);

        if(result.isSuccess()){
            if (inventory.getInventoryId() <= 0) {
                result.addMessage("InventoryId  must be set for `update` operation", ResultType.INVALID);
                return result;
            }
            if (!repository.update(inventory)) {
                String msg = String.format("InventoryId: %s, not found", inventory.getInventoryId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }
        }

        return result;
    }

    public boolean deleteById(int inventoryId){
        return repository.deleteById(inventoryId);
    }

    private Result<MaterialInventory> validate(MaterialInventory inventory) {
        Result<MaterialInventory> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<MaterialInventory>> violations = validator.validate(inventory);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<MaterialInventory> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        
        return result;
    }

    private Result<MaterialInventory> checkInventoryForMaterial(Result<MaterialInventory> result, MaterialInventory inventory) {

        List<MaterialInventory> inventoryList = findAll();
        for (MaterialInventory i : inventoryList) {
            if (i.getMaterialId() == inventory.getMaterialId()) {
                result.addMessage("This material already has an inventory object.", ResultType.INVALID);
                return result;
            }
        }
        return result;
    }
}
