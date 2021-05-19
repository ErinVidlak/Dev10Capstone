package learn.inventory.data.mappers;

import learn.inventory.models.ListedProduct;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListedProductMapper implements RowMapper<ListedProduct> {


    @Override
    public ListedProduct mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
