package learn.inventory.data;

import learn.inventory.data.mappers.ListedProductMapper;
import learn.inventory.models.ListedProduct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ListedProductJdbcTemplateRepository implements ListedProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ListedProductJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ListedProduct findById(int listedProductId) {

        final String sql = "select listed_product_id, listing_name "
                + "from listed_product "
                + "where listed_product_id = ?;";

        return jdbcTemplate.query(sql, new ListedProductMapper(), listedProductId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<ListedProduct> findAll() {

        final String sql = "select listed_product_id, listing_name, listed_price, fee_amount, "
                + "date_listed, date_sold, is_sold, product_id "
                + "from listed_product;";

        return jdbcTemplate.query(sql, new ListedProductMapper());
    }

    @Override
    public ListedProduct add(ListedProduct listedProduct) {

        final String sql = "insert into listed_product (listing_name, listed_price, fee_amount, date_listed, "
                + "date_sold, is_sold, product_id) "
                + "values (?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, listedProduct.getListingName());
            ps.setBigDecimal(2, listedProduct.getListedPrice());
            ps.setBigDecimal(3, listedProduct.getFeeAmount());
            ps.setDate(4, Date.valueOf(listedProduct.getDateListed()));
            ps.setDate(5, listedProduct.getDateSold() == null ? null : Date.valueOf(listedProduct.getDateSold()));
            ps.setBoolean(6, listedProduct.isSold());
            ps.setInt(7, listedProduct.getProductId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        listedProduct.setListedProductId((keyHolder.getKey().intValue()));
        return listedProduct;
    }

    @Override
    public boolean update(ListedProduct listedProduct) {

        final String sql = "update listed_product set "
                + "listing_name = ?, "
                + "listed_price = ?, "
                + "fee_amount = ?, "
                + "date_listed = ?, "
                + "date_sold = ?, "
                + "is_sold = ?, "
                + "product_id = ? "
                + "where listed_product_id = ?;";

        return jdbcTemplate.update(sql,
                listedProduct.getListingName(),
                listedProduct.getListedPrice(),
                listedProduct.getFeeAmount(),
                listedProduct.getDateListed(),
                listedProduct.getDateSold(),
                listedProduct.isSold(),
                listedProduct.getProductId(),
                listedProduct.getListedProductId()) > 0;
    }

    @Override
    public boolean deleteById(int listedProductId) {
        return jdbcTemplate.update("delete from listed_product where listed_product_id = ?;", listedProductId) > 0;
    }
}
