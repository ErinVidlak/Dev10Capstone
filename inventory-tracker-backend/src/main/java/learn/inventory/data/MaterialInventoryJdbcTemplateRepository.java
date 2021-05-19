package learn.inventory.data;

import learn.inventory.models.MaterialInventory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialInventoryJdbcTemplateRepository implements MaterialInventoryRepository{

    private final JdbcTemplate jdbcTemplate;

    public MaterialInventoryJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


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
