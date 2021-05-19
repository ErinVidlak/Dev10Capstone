package learn.inventory.data.mappers;

import learn.inventory.models.MaterialInventory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialInventoryMapper implements RowMapper<MaterialInventory> {


    @Override
    public MaterialInventory mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
