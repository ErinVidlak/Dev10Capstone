package learn.inventory.data;

import learn.inventory.models.Material;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaterialRepository {

    List<Material> findAll();

    Material findById(int materialId);

    Material add(Material material);

    boolean update(Material material);

    boolean deleteById(int materialId);
}
