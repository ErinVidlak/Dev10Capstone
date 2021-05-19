package learn.inventory.data;

import learn.inventory.models.MaterialPurchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MaterialPurchaseJdbcTemplateRepository implements MaterialPurchaseRepository{

    private final JdbcTemplate jdbcTemplate;

    public MaterialPurchaseJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<MaterialPurchase> findAll() {
        return null;
    }

    @Override
    public MaterialPurchase findById(int purchaseId) {
        return null;
    }

    @Override
    @Transactional
    public MaterialPurchase add(MaterialPurchase purchase) {
        return null;
    }

    @Override
    @Transactional
    public boolean update(MaterialPurchase purchase) {
        return false;
    }

    @Override
    @Transactional
    public boolean deleteById(int purchaseId) {
        return false;
    }
}
