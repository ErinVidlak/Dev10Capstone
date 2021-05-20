package learn.inventory.data;

import learn.inventory.data.mappers.MaterialInventoryMapper;
import learn.inventory.models.MaterialInventory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialInventoryJdbcTemplateRepository implements MaterialInventoryRepository{

    private final JdbcTemplate jdbcTemplate;

    public MaterialInventoryJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


//
//    private int inventoryId;
//    private int totalQuantity;
//    private int materialId;


//    material_inventory_id     int primary key auto_increment,
//    total_quantity        int not null,
//    material_id       int not null,

    @Override
    public List<MaterialInventory> findAll() {
        final String sql = "select material_inventory_id, total_quantity, material_id " +
                " from material_inventory limit 1000";
        return jdbcTemplate.query(sql, new MaterialInventoryMapper());
    }

    @Override
    public MaterialInventory findById(int inventoryId) {
        final String sql = "select material_inventory_id, total_quantity, material_id " +
                " from material_inventory " +
                " where material_inventory_id = ?";
        return jdbcTemplate.query(sql, new MaterialInventoryMapper(), inventoryId).stream()
                .findFirst()
                .orElse(null);

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
