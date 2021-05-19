package learn.inventory.data.mappers;

import learn.inventory.models.MaterialPurchase;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialPurchaseMapper implements RowMapper<MaterialPurchase> {
    @Override
    public MaterialPurchase mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
