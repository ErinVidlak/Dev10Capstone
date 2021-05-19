package learn.inventory.data.mappers;

import learn.inventory.models.MaterialProduct;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialProductMapper implements RowMapper<MaterialProduct> {

    @Override
    public MaterialProduct mapRow(ResultSet resultSet, int i) throws SQLException {
        MaterialProduct materialProduct = new MaterialProduct();

        materialProduct.setMaterialQuantity(resultSet.getInt("material_quantity_used"));
        materialProduct.setMaterialId(resultSet.getInt("material_id"));

        ProductMapper productMapper = new ProductMapper();
        materialProduct.setProduct(productMapper.mapRow(resultSet, i));

        return materialProduct;
    }
}
