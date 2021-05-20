package learn.inventory.data;

import learn.inventory.data.mappers.MaterialInventoryMapper;
import learn.inventory.models.MaterialInventory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
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

    //TODO: if a material ever only has one inventory, then would it not make more sense
    //TODO: to create an inventory at the same time as a new material?
    //TODO: rather than adding an inventory
    @Override
    public MaterialInventory add(MaterialInventory inventory) {
        final String sql = "insert into material_inventory " +
                "( total_quantity, material_id ) " +
                " values (?, ? );";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inventory.getTotalQuantity());
            ps.setInt(2, inventory.getMaterialId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        inventory.setInventoryId(keyHolder.getKey().intValue());

        return inventory;

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
