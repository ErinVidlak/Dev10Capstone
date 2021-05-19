package learn.inventory.data.mappers;

import learn.inventory.models.MaterialProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialProductMapper implements RowMapper<MaterialProduct> {
    @Override
    public MaterialProduct mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
