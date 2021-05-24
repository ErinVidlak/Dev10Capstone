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


    public ProductMaterialController(ProductMaterialService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ProductMaterial productMaterial) {
        Result<ProductMaterial> result = service.add(productMaterial);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{productId}/{materialId}")
    public ResponseEntity<Object> update(@PathVariable int productId, @PathVariable int materialId, @RequestBody ProductMaterial productMaterial) {
        if ((productId != productMaterial.getProductId()) || (materialId != productMaterial.getMaterial().getMaterialId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<ProductMaterial> result = service.update(productMaterial);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{productId}/{materialId}")
    public ResponseEntity<Void> deleteByKey(@PathVariable int productId, @PathVariable int materialId) {
        if (service.deleteByKey(productId, materialId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
