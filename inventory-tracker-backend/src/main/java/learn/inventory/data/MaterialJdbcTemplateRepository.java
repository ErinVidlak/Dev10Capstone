package learn.inventory.data;

import learn.inventory.data.mappers.*;
import learn.inventory.models.Material;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MaterialJdbcTemplateRepository implements MaterialRepository{

    private final JdbcTemplate jdbcTemplate;

    public MaterialJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Material> findAll() {
        final String sql = "select material_id, material_name, price_per_unit, user_id "
                + "from material limit 1000;";

        return jdbcTemplate.query(sql, new MaterialMapper());
    }

    @Override
    public Material findById(int materialId) {

        final String sql = "select material_id, material_name, price_per_unit, user_id "
                + "from material "
                + "where material_id = ?;";

        Material material = jdbcTemplate.query(sql, new MaterialMapper(), materialId).stream()
                .findAny().orElse(null);

        if (material != null) {
            addInventory(material);
            addPurchases(material);
            addProducts(material);
        }

        return material;
    }

    @Override
    public Material add(Material material) {
        final String sql = "insert into material (material_name, price_per_unit, user_id) "
                + "values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, material.getMaterialName());
            ps.setString(2, material.getPricePerUnit().toString());
            ps.setString(3, material.getUserId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        material.setMaterialId(keyHolder.getKey().intValue());
        return material;
    }

    @Override
    public boolean update(Material material) {
        final String sql = "update material set "
                + "material_name = ?, "
                + "price_per_unit = ?, "
                + "user_id = ?, "
                + "where agent_id = ?;";

        return jdbcTemplate.update(sql,
                material.getMaterialName(),
                material.getPricePerUnit(),
                material.getUserId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int materialId) {
        jdbcTemplate.update("delete from material_inventory where material_id = ?;", materialId);
        jdbcTemplate.update("delete from material_purchase where material_id = ?;", materialId);
        jdbcTemplate.update("delete from product_material where material_id = ?;", materialId);
        return jdbcTemplate.update("delete from material where material_id = ?;", materialId) > 0;


    }

    private void addInventory(Material material) {

        final String sql = "select material_inventory_id, total_quantity, material_id "
                + "from material_inventory "
                + "where material_id = ?;";

        var inventory = jdbcTemplate.query(sql, new MaterialInventoryMapper(), material.getMaterialId())
                .stream().findAny().orElse(null);
        material.setInventory(inventory);
    }

    private void addPurchases(Material material) {

        final String sql = "select material_purchase_id, purchase_price, purchase_quantity, "
                + "quantity_units, purchase_date, purchase_description, material_id "
                + "from material_purchase "
                + "where material_id = ?;";

        var purchases = jdbcTemplate.query(sql, new MaterialPurchaseMapper(), material.getMaterialId());
        material.setPurchases(purchases);
    }

    private void addProducts(Material material) {

        final String sql = "select pm.material_quantity_used, pm.material_id, pm.product_id, "
                + "p.product_id, p.product_name, p.total_materials_cost, p.time_to_make, p.user_id, "
                + "m.material_name, m.price_per_unit, m.user_id "
                + "from product_material pm "
                + "inner join product p on pm.product_id = p.product_id "
                + "inner join material m on pm.material_id = ? "
                + "where pm.material_id = ?;";

        var products = jdbcTemplate.query(sql, new ProductMaterialMapper(), material.getMaterialId(), material.getMaterialId());
        material.setProducts(products);
    }

//    private void addAgents(Agency agency) {
//
//        final String sql = "select aa.agency_id, aa.agent_id, aa.identifier, aa.activation_date, aa.is_active, "
//                + "sc.security_clearance_id, sc.name security_clearance_name, "
//                + "a.first_name, a.middle_name, a.last_name, a.dob, a.height_in_inches "
//                + "from agency_agent aa "
//                + "inner join agent a on aa.agent_id = a.agent_id "
//                + "inner join security_clearance sc on aa.security_clearance_id = sc.security_clearance_id "
//                + "where aa.agency_id = ?";
//
//        var agencyAgents = jdbcTemplate.query(sql, new AgencyAgentMapper(), agency.getAgencyId());
//        agency.setAgents(agencyAgents);
//    }

}
