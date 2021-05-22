package learn.inventory.domain;

import learn.inventory.data.MaterialRepository;
import learn.inventory.models.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository repository;

    public MaterialService(MaterialRepository repository) {
        this.repository = repository;
    }

    public List<Material> findAll() {
        return repository.findAll();
    }

    public Result<Material> add(Material material){
        return null;
    }

    public Result<Material> update(Material material){
        return null;
    }



    public Material findById(int materialId){
        return repository.findById(materialId);
    }

    public boolean deleteById(int materialId){
        return repository.deleteById(materialId);
    }
}
