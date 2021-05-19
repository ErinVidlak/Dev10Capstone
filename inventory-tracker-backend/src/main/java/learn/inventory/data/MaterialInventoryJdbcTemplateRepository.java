package learn.inventory.data;

import learn.inventory.models.MaterialInventory;

import java.util.List;

public class MaterialInventoryJdbcTemplateRepository implements MaterialInventoryRepository{
    @Override
    public List<MaterialInventory> findAll() {
        return null;
    }

    @Override
    public MaterialInventory findById(int inventoryId) {
        return null;
    }

    @Override
    public MaterialInventory add(MaterialInventory inventory) {
        return null;
    }

    @Override
    public boolean update(MaterialInventory inventory) {
        return false;
    }

    @Override
    public boolean deleteById(int inventoryId) {
        return false;
    }
}
