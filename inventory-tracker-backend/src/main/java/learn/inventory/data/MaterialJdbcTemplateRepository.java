package learn.inventory.data;

import learn.inventory.models.Material;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialJdbcTemplateRepository implements MaterialRepository{
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
    public boolean deleteById(int materialId) {
        return false;
    }
}
