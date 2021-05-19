package learn.inventory.data;

import learn.inventory.models.PlatformFee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlatformFeeJdbcTemplateRepository implements PlatformFeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlatformFeeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public PlatformFee findById(int platformFeeId) {
        return null;
    }

    @Override
    public List<PlatformFee> findAll() {
        return null;
    }

    @Override
    public PlatformFee add(PlatformFee platformFee) {
        return null;
    }

    @Override
    public boolean update(PlatformFee platformFee) {
        return false;
    }

    @Override
    public boolean deleteById(int platformFeeId) {
        return false;
    }
}
