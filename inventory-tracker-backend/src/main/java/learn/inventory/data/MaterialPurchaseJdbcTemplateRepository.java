package learn.inventory.data;

import learn.inventory.models.MaterialPurchase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialPurchaseJdbcTemplateRepository implements MaterialPurchaseRepository{


    @Override
    public List<MaterialPurchase> findAll() {
        return null;
    }

    @Override
    public MaterialPurchase findById(int purchaseId) {
        return null;
    }

    @Override
    public MaterialPurchase add(MaterialPurchase purchase) {
        return null;
    }

    @Override
    public boolean update(MaterialPurchase purchase) {
        return false;
    }

    @Override
    public boolean deleteById(int purchaseId) {
        return false;
    }
}
