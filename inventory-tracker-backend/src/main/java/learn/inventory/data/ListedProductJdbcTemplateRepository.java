package learn.inventory.data;

import learn.inventory.models.ListedProduct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ListedProductJdbcTemplateRepository implements ListedProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ListedProductJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ListedProduct findById(int listedProductId) {
        return null;
    }

    @Override
    public List<ListedProduct> findAll() {
        return null;
    }

    @Override
    public ListedProduct add(ListedProduct listedProduct) {
        return null;
    }

    @Override
    public boolean update(ListedProduct listedProduct) {
        return false;
    }

    @Override
    public boolean deleteById(int listedProductId) {
        return false;
    }
}
