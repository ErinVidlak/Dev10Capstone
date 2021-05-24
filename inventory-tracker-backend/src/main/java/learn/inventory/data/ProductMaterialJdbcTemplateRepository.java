package learn.inventory.data;

import learn.inventory.data.mappers.ListedProductMapper;
import learn.inventory.data.mappers.MaterialMapper;
import learn.inventory.data.mappers.MaterialPurchaseMapper;
import learn.inventory.data.mappers.ProductMaterialMapper;
import learn.inventory.models.Product;
import learn.inventory.models.ProductMaterial;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductMaterialJdbcTemplateRepository implements ProductMaterialRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductMaterialJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public List<ProductMaterial> findByProductId(int productId) {
        final String sql = "select pm.material_quantity_used, pm.material_id, pm.product_id, "
                + "m.material_id, m.material_name, m.price_per_unit, m.user_id "
                + "from product_material pm "
                + "inner join material m on pm.material_id = m.material_id "
                + "where pm.product_id = ?;";

        return jdbcTemplate.query(sql, new ProductMaterialMapper(), productId);
    }

    @Override
    public boolean add(ProductMaterial productMaterial) {

        final String sql = "insert into product_material (material_quantity_used, material_id, product_id) values "
                + "(?,?,?);";

        return jdbcTemplate.update(sql,
                productMaterial.getMaterialQuantity(),
                productMaterial.getMaterial().getMaterialId(),
                productMaterial.getProductId()) > 0;
    }

    @Override
    public boolean update(ProductMaterial productMaterial) {

        final String sql = "update product_material set "
                + "material_quantity_used = ? "
                + "where material_id = ? and product_id = ?;";

        return jdbcTemplate.update(sql,
                productMaterial.getMaterialQuantity(),
                productMaterial.getMaterial().getMaterialId(),
                productMaterial.getProductId()) > 0;
    }

    @Override
    public boolean deleteByKey(int productId, int materialId) {

        final String sql = " delete from product_material "
                + "where material_id = ? and product_id = ?;";

        return jdbcTemplate.update(sql, materialId, productId) > 0;
    }
}
