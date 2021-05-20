package learn.inventory.data;

import learn.inventory.models.ProductMaterial;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductMaterialJdbcTemplateRepository implements ProductMaterialRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductMaterialJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
