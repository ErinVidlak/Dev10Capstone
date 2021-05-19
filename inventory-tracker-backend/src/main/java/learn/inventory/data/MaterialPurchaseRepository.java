package learn.inventory.data;

import learn.inventory.models.MaterialProduct;
import learn.inventory.models.MaterialPurchase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaterialPurchaseRepository {

    List<MaterialPurchase> findAll();

    MaterialPurchase findById(int purchaseId);

    MaterialPurchase add(MaterialPurchase purchase);

    boolean update(MaterialPurchase purchase);

    boolean deleteById(int purchaseId);
}
