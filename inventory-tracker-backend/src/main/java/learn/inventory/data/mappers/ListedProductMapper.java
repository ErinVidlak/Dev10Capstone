package learn.inventory.data.mappers;

import learn.inventory.models.ListedProduct;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListedProductMapper implements RowMapper<ListedProduct> {


    @Override
    public ListedProduct mapRow(ResultSet resultSet, int i) throws SQLException {
        ListedProduct listedProduct = new ListedProduct();
        listedProduct.setListedProductId(resultSet.getInt("listed_product_id"));
        listedProduct.setListingName(resultSet.getString("listing_name"));
        listedProduct.setListedPrice(resultSet.getBigDecimal("listed_price"));
        listedProduct.setDateListed(resultSet.getDate("date_listed").toLocalDate());
        if (resultSet.getDate("date_sold") != null) {
            listedProduct.setDateSold(resultSet.getDate("date_sold").toLocalDate());
        }
        listedProduct.setSold(resultSet.getBoolean("is_sold"));
        listedProduct.setProductId(resultSet.getInt("product_id"));
        listedProduct.setFeeAmount(resultSet.getBigDecimal("fee_amount"));
        return listedProduct;
    }
}
