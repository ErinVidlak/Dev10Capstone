package learn.inventory.data.mappers;

import learn.inventory.models.ProductMaterial;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMaterialMapper implements RowMapper<ProductMaterial> {

    @Override
    public ProductMaterial mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
