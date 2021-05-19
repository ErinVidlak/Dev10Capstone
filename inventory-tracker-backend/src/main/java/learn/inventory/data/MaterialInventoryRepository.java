package learn.inventory.data;

import learn.inventory.models.MaterialInventory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaterialInventoryRepository {

    List<MaterialInventory> findAll();

    MaterialInventory findById(int inventoryId);

    MaterialInventory add(MaterialInventory inventory);

    boolean update(MaterialInventory inventory);

    boolean deleteById(int inventoryId);
}
