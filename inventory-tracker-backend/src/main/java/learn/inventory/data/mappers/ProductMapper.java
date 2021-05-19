package learn.inventory.data.mappers;

import learn.inventory.models.Product;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setTotalMaterialsCost(resultSet.getBigDecimal("total_materials_cost"));
        product.setTimeToMake(resultSet.getInt("time_to_make"));
        product.setUserId(resultSet.getString("user_id"));
        return product;
    }
}
