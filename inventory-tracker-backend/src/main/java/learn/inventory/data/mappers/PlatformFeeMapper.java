package learn.inventory.data.mappers;

import learn.inventory.models.PlatformFee;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlatformFeeMapper implements RowMapper<PlatformFee> {


    @Override
    public PlatformFee mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
