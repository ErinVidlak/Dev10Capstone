package learn.inventory.data;

import learn.inventory.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductJdbcTemplateRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product findById(int productId) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product add(Product product) {
        return null;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean deleteById(int productId) {
        return false;
    }
}
