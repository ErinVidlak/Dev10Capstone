package learn.inventory.data;

import learn.inventory.models.ProductMaterial;

public interface ProductMaterialRepository {
    boolean add(ProductMaterial productMaterial);

    boolean update(ProductMaterial productMaterial);

    boolean deleteByKey(int productId, int materialId);
}
