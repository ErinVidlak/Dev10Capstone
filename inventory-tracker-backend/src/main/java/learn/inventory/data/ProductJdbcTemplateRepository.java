package learn.inventory.data;

import learn.inventory.data.mappers.ListedProductMapper;
import learn.inventory.data.mappers.ProductMapper;
import learn.inventory.data.mappers.ProductMaterialMapper;
import learn.inventory.models.ListedProduct;
import learn.inventory.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ProductJdbcTemplateRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Product findById(int productId) {

        final String sql = "select product_id, product_name, total_materials_cost, time_to_make, user_id  "
                + "from product "
                + "where product_id = ?;";

        Product product = jdbcTemplate.query(sql, new ProductMapper(), productId)
                .stream()
                .findFirst().orElse(null);
        if (product != null) {
            addMaterials(product);
            addListedProduct(product);
        }

        return product;
    }

    @Override
    public List<Product> findAll() {

        final String sql = "select product_id, product_name, total_materials_cost, time_to_make, user_id "
                + "from product limit 1000;";

        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    @Transactional
    public Product add(Product product) {

        final String sql = "insert into product (product_name, total_materials_cost, time_to_make, user_id) "
                + "values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setBigDecimal(2, product.getTotalMaterialsCost());
            ps.setInt(3, product.getTimeToMake());
            ps.setString(4, product.getUserId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        product.setProductId(keyHolder.getKey().intValue());
        product.setListedProduct(generateListedProductOnAdd(product));
        return product;
    }

    private ListedProduct generateListedProductOnAdd(Product product) {
        ListedProduct listing = product.getListedProduct();
        listing.setProductId(product.getProductId());

        final String sql = "insert into listed_product " +
                "(listed_price, fee_amount, date_listed, date_sold, is_sold, listing_name, product_id) " +
                "values (?, ?, ?, ?, ?, ?, ?); ";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, listing.getListedPrice());
            ps.setBigDecimal(2, listing.getFeeAmount());
            ps.setDate(3, Date.valueOf(LocalDate.of(2000, 1, 1)));
            ps.setDate(4, null);
            ps.setBoolean(5, listing.isSold());
            ps.setString(6, listing.getListingName());
            ps.setInt(7, listing.getProductId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        listing.setListedProductId(keyHolder.getKey().intValue());
        return listing;
    }

    @Override
    public boolean update(Product product) {

        final String sql = "update product set "
                + "product_name = ?, "
                + "total_materials_cost = ?, "
                + "time_to_make = ? "
                + "where product_id = ?;";

        return jdbcTemplate.update(sql,
                product.getProductName(),
                product.getTotalMaterialsCost(),
                product.getTimeToMake(),
                product.getProductId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int productId) {
        jdbcTemplate.update("delete from product_material where product_id = ?;", productId);
        jdbcTemplate.update("delete from listed_product where product_id = ?;", productId);
        return jdbcTemplate.update("delete from product where product_id = ?;", productId) > 0;
    }

    private void addListedProduct(Product product) {
        final String sql = "select listed_product_id, listing_name, listed_price, fee_amount, "
                + "date_listed, date_sold, is_sold, product_id "
                + "from listed_product "
                + "where product_id = ?;";
        var listedProduct = jdbcTemplate.query(sql, new ListedProductMapper(), product.getProductId()).stream().findAny().orElse(null);
        product.setListedProduct(listedProduct);
    }

    private void addMaterials(Product product) {
        final String sql = "select pm.material_quantity_used, pm.product_id, pm.material_id, "
                + "m.material_name, m.price_per_unit, m.user_id "
                + "from product_material pm "
                + "inner join material m on pm.material_id = m.material_id "
                + "where pm.product_id = ?;";
        var materials = jdbcTemplate.query(sql, new ProductMaterialMapper(), product.getProductId());
        product.setMaterials(materials);

    }
}
