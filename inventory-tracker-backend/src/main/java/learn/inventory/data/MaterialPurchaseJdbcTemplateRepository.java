package learn.inventory.data;

import learn.inventory.data.mappers.MaterialPurchaseMapper;
import learn.inventory.models.MaterialPurchase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MaterialPurchaseJdbcTemplateRepository implements MaterialPurchaseRepository{

    private final JdbcTemplate jdbcTemplate;

    public MaterialPurchaseJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //  private int purchaseId;
//    private BigDecimal purchasePrice;
//    private int quantityPurchased;
//    private String units;
//    private LocalDate datePurchased;
//    private String description;
//    private int materialId;
    /*
    material_purchase_id int primary key auto_increment,
    purchase_price decimal (10,2) not null default 0.00,
    purchase_quantity int not null,
    quantity_units varchar(25),
    purchase_date date,
    purchase_description text,
    material_id int not null,
    constraint fk_material_purchase_material_id
        foreign key (material_id)
        references material(material_id)
     */
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
        return null;
    }

    @Override
    @Transactional
    public boolean update(MaterialPurchase purchase) {
        return false;
    }

    @Override
    @Transactional
    public boolean deleteById(int purchaseId) {
        return false;
    }
}
