package learn.inventory.data.mappers;

import learn.inventory.models.MaterialInventory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialInventoryMapper implements RowMapper<MaterialInventory> {


    @Override
    public MaterialInventory mapRow(ResultSet resultSet, int i) throws SQLException {
        MaterialInventory inventory = new MaterialInventory();
        inventory.setInventoryId(resultSet.getInt("material_inventory_id"));
        inventory.setTotalQuantity(resultSet.getInt("total_quantity"));
        inventory.setMaterialId(resultSet.getInt("material_id"));
        return inventory;
    }
}
