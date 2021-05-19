package learn.inventory.data;

import learn.inventory.models.Material;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MaterialJdbcTemplateRepository implements MaterialRepository{

    private final JdbcTemplate jdbcTemplate;

    public MaterialJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Material> findAll() {
        return null;
    }

    @Override
    public Material findById(int materialId) {
        return null;
    }

    @Override
    public Material add(Material material) {
        return null;
    }

    @Override
    public boolean update(Material material) {
        return false;
    }

    @Override
    @Transactional
    public boolean deleteById(int materialId) {
        return false;
    }
}
