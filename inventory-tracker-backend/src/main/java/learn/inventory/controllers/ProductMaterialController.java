package learn.inventory.controllers;

import learn.inventory.domain.ProductMaterialService;
import learn.inventory.domain.Result;
import learn.inventory.models.ProductMaterial;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/productMaterial")
public class ProductMaterialController {

    private final ProductMaterialService service;

    public ProductMaterialController(ProductMaterialService service) { this.service = service; }

    @GetMapping
    public List<ProductMaterial> findAll() {
        return service.findAll();
    }

    @GetMapping("/{productMaterialId}")
    public ProductMaterial findById(@PathVariable int productMaterialId) {
        return service.findById(productMaterialId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ProductMaterial productMaterial) {
        Result<ProductMaterial> result = service.add(productMaterial);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{productMaterialId}")
    public ResponseEntity<Object> update(@PathVariable int productMaterialId, @RequestBody ProductMaterial productMaterial) {
        if (productMaterialId != productMaterial.getInventoryId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<ProductMaterial> result = service.update(productMaterial);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{productMaterialId}")
    public ResponseEntity<Void> deleteById(@PathVariable int productMaterialId) {
        if (service.deleteById(productMaterialId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}