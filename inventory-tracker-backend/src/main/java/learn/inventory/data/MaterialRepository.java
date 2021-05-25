package learn.inventory.data;

import learn.inventory.models.Material;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaterialRepository {

    List<Material> findAll();

    List<Material> findAllUserMaterials(String userId);

    Material findById(int materialId);

    Material add(Material material);

    boolean update(Material material);

    boolean deleteById(int materialId);
}
