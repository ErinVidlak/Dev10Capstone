package learn.inventory.data.mappers;

import learn.inventory.models.Material;
import learn.inventory.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialMapper implements RowMapper<Material> {


    @Override
    public Material mapRow(ResultSet resultSet, int i) throws SQLException {
        Material material = new Material();

        material.setMaterialId((resultSet.getInt("material_id")));
        material.setMaterialName(resultSet.getString("material_name"));
        material.setPricePerUnit(resultSet.getBigDecimal("price_per_unit"));
        material.setUserId(resultSet.getString("user_id"));
        
        return material;
    }
}
