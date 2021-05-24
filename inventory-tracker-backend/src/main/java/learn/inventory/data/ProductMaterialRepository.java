package learn.inventory.data;

import learn.inventory.models.ProductMaterial;

import java.util.List;

public interface ProductMaterialRepository {
    List<ProductMaterial> findByProductId(int productId);

    boolean add(ProductMaterial productMaterial);

    boolean update(ProductMaterial productMaterial);

    boolean deleteByKey(int productId, int materialId);
}
