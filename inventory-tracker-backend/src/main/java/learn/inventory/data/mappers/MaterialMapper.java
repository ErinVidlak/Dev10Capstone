package learn.inventory.data.mappers;

import learn.inventory.models.Material;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialMapper implements RowMapper<Material> {


    @Override
    public Material mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
