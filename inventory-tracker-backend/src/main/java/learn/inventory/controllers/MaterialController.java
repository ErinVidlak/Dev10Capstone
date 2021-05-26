package learn.inventory.controllers;

import learn.inventory.domain.MaterialService;
import learn.inventory.domain.Result;
import learn.inventory.models.Material;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialService service;


    public MaterialController(MaterialService service) {
        this.service = service;
    }



    @GetMapping
    public List<Material> findAll() {
        return service.findAll();
    }

    @GetMapping("/user")
    public List<Material> findAllUserMaterials(@RequestParam String userId) {
        System.out.println(userId);
        return service.findAllUserMaterials(userId);
    }


    @GetMapping("/{materialId}")
    public Material findById(@PathVariable int materialId) {
        return service.findById(materialId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Material material) {
        Result<Material> result = service.add(material);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<Object> update(@PathVariable int materialId, @RequestBody Material material) {
        if (materialId != material.getMaterialId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Material> result = service.update(material);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<Void> deleteById(@PathVariable int materialId) {
        if (service.deleteById(materialId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
