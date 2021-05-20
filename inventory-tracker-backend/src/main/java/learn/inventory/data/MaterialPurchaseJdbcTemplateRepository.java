package learn.inventory.data;

import learn.inventory.data.mappers.MaterialPurchaseMapper;
import learn.inventory.models.MaterialPurchase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MaterialPurchaseJdbcTemplateRepository implements MaterialPurchaseRepository {

    private final JdbcTemplate jdbcTemplate;

    public MaterialPurchaseJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private final String PURCHASE_COL_NAMES = " material_purchase_id, purchase_price, purchase_quantity, quantity_units," +
            " purchase_date, purchase_description, material_id ";

    
    
    @Override
    public List<MaterialPurchase> findAll() {
        final String sql = "select " + PURCHASE_COL_NAMES + " from material_purchase limit 1000;";
        return jdbcTemplate.query(sql, new MaterialPurchaseMapper());
    }

    @Override
    public MaterialPurchase findById(int purchaseId) {
        final String sql = "select " + PURCHASE_COL_NAMES +
                " from material_purchase " +
                " where material_purchase_id = ? ";
        return jdbcTemplate.query(sql, new MaterialPurchaseMapper(), purchaseId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional
    public MaterialPurchase add(MaterialPurchase purchase) {
        final String sql = "insert into material_purchase " +
                "( purchase_price, purchase_quantity, quantity_units," +
                " purchase_date, purchase_description, material_id ) " +
                " values (?, ?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, purchase.getPurchasePrice());
            ps.setInt(2, purchase.getQuantityPurchased());
            ps.setString(3, purchase.getUnits());
            ps.setDate(4,
                    purchase.getDatePurchased() == null ? null : Date.valueOf(purchase.getDatePurchased()));
            ps.setString(5, purchase.getDescription());
            ps.setInt(6, purchase.getMaterialId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        purchase.setPurchaseId(keyHolder.getKey().intValue());

        int updatedInventoryQuantity = purchase.getQuantityPurchased();
        final String sqlInventory =
                " update material_inventory set" +
                " total_quantity = total_quantity + ? " +
                " where material_id = ?;";

        boolean hasInventoryUpdated = jdbcTemplate.update(sqlInventory,
                purchase.getQuantityPurchased(),
                purchase.getMaterialId()) == 1;

        if(!hasInventoryUpdated){
            return null;
        }

        return purchase;

    }

    @Override
    public boolean update(MaterialPurchase purchase) {
            final String sql = "update material_purchase set " +
                    " purchase_price = ?, " +
                    " purchase_quantity = ?, " +
                    " quantity_units = ?, " +
                    " purchase_date = ?, " +
                    " purchase_description = ? " +
                    " where material_purchase_id = ?;";

            return jdbcTemplate.update(sql,
                    purchase.getPurchasePrice(),
                    purchase.getQuantityPurchased(),
                    purchase.getUnits(),
                    purchase.getDatePurchased(),
                    purchase.getDescription(),
                    purchase.getPurchaseId()) > 0;
    }

    @Override
    public boolean deleteById(int purchaseId) {
        return jdbcTemplate.update("delete from material_purchase where material_purchase_id = ?", purchaseId) > 0;

    }
}
