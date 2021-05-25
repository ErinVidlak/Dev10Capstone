package learn.inventory.domain;

import learn.inventory.data.MaterialJdbcTemplateRepository;
import learn.inventory.models.Material;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class MaterialService {
    private final MaterialJdbcTemplateRepository materialRepository;

    public MaterialService(MaterialJdbcTemplateRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public List<Material> findAllUserMaterials(String userId){return null;  }
    public Material findById(int materialId) {
        return materialRepository.findById(materialId);
    }

    public Result<Material> add(Material material) {
        Result<Material> result = validate(material);
        validateNoDuplicatesExist(material, result);
        if (!result.isSuccess()) {
            return result;
        }

        if (material.getMaterialId() != 0) {
            result.addMessage("materialId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        material = materialRepository.add(material);
        result.setPayload(material);
        return result;
    }

    public Result<Material> update(Material material) {
        Result<Material> result = validate(material);
        if (!result.isSuccess()) {
            return result;
        }

        if (material.getMaterialId() <= 0) {
            result.addMessage("materialId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!materialRepository.update(material)) {
            String msg = String.format("materialId: %s, not found", material.getMaterialId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int materialId) {
        return materialRepository.deleteById(materialId);
    }

    private Result<Material> validate(Material material) {
        Result<Material> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Material>> violations = validator.validate(material);

        if (material == null) {
            result.addMessage("material cannot be null", ResultType.INVALID);
            return result;
        }

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Material> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        return result;
    }

    private void validateNoDuplicatesExist(Material newMaterial, Result<Material> result) {
        List<Material> materials = materialRepository.findAll();
        for (Material material : materials) {
            if (newMaterial.getMaterialName().equalsIgnoreCase(material.getMaterialName())
                    && newMaterial.getUserId().equalsIgnoreCase(material.getUserId())) {
                result.addMessage("Materials must have a unique name for a user", ResultType.INVALID);
            }
        }
    }
}


