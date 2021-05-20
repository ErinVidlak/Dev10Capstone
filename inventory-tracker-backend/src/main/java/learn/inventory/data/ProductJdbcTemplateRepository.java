package learn.inventory.data;

import learn.inventory.data.mappers.ProductMapper;
import learn.inventory.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProductJdbcTemplateRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product findById(int productId) {

        final String sql = "select product_id, product_name "
                + "from product "
                + "where product_id = ?;";

        return jdbcTemplate.query(sql, new ProductMapper(), productId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<Product> findAll() {

        final String sql = "select product_id, product_name, total_materials_cost, time_to_make, user_id "
                + "from product;";

        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public Product add(Product product) {

        final String sql = "insert into product (product_name, total_materials_cost, time_to_make, user_id) "
                + "values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setBigDecimal(2, product.getTotalMaterialsCost());
            ps.setInt(3, product.getTimeToMake());
            ps.setString(4, product.getUserId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        product.setProductId(keyHolder.getKey().intValue());
        return product;
    }

    @Override
    public boolean update(Product product) {

        final String sql = "update product set "
                + "product_name = ?, "
                + "total_materials_cost = ?, "
                + "time_to_make = ?, "
                + "user_id = ? "
                + "where product_id = ?;";

        return jdbcTemplate.update(sql,
                product.getProductName(),
                product.getTotalMaterialsCost(),
                product.getTimeToMake(),
                product.getUserId(),
                product.getProductId()) > 0;
    }

    @Override
    public boolean deleteById(int productId) {
        return jdbcTemplate.update("delete from product where product_id = ?;", productId) > 0;
    }
}
