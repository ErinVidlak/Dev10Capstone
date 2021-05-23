package learn.inventory.controllers;

import learn.inventory.domain.MaterialInventoryService;
import learn.inventory.domain.Result;
import learn.inventory.models.MaterialInventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/materialInventory")
public class MaterialInventoryController {

    private final MaterialInventoryService service;

    public MaterialInventoryController(MaterialInventoryService service) { this.service = service; }

    @GetMapping
    public List<MaterialInventory> findAll() {
        return service.findAll();
    }

    @GetMapping("/{materialInventoryId}")
    public MaterialInventory findById(@PathVariable int materialInventoryId) {
        return service.findById(materialInventoryId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody MaterialInventory materialInventory) {
        Result<MaterialInventory> result = service.add(materialInventory);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{materialInventoryId}")
    public ResponseEntity<Object> update(@PathVariable int materialInventoryId, @RequestBody MaterialInventory materialInventory) {
        materialInventory.setInventoryId(materialInventoryId);
        if (materialInventoryId != materialInventory.getInventoryId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<MaterialInventory> result = service.update(materialInventory);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{materialInventoryId}")
    public ResponseEntity<Void> deleteById(@PathVariable int materialInventoryId) {
        if (service.deleteById(materialInventoryId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}