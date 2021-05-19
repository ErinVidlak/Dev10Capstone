package learn.inventory.data.mappers;

import learn.inventory.models.MaterialPurchase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialPurchaseMapper implements RowMapper<MaterialPurchase> {
    @Override
    public MaterialPurchase mapRow(ResultSet resultSet, int i) throws SQLException {
        MaterialPurchase purchase = new MaterialPurchase();
        purchase.setPurchaseId(resultSet.getInt("material_purchase_id"));
        purchase.setPurchasePrice(resultSet.getBigDecimal("purchase_price"));
        purchase.setQuantityPurchased(resultSet.getInt("purchase_quantity"));
        purchase.setUnits(resultSet.getString("quantity_units"));
        if (resultSet.getDate("purchase_date") != null) {
            purchase.setDatePurchased(resultSet.getDate("purchase_date").toLocalDate());
        }
        purchase.setDescription(resultSet.getString("purchase_description"));
        purchase.setMaterialId(resultSet.getInt("material_id"));

        return purchase;
    }
}
