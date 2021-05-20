package learn.inventory.data.mappers;

import learn.inventory.models.ProductMaterial;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMaterialMapper implements RowMapper<ProductMaterial> {

    @Override
    public ProductMaterial mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductMaterial productMaterial = new ProductMaterial();
        productMaterial.setMaterialQuantity(resultSet.getInt("material_quantity_used"));
        productMaterial.setProductId(resultSet.getInt("product_id"));

        MaterialMapper materialMapper = new MaterialMapper();
        productMaterial.setMaterial(materialMapper.mapRow(resultSet, i));

        return productMaterial;
    }
}