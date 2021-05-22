package learn.inventory.controllers;

import learn.inventory.domain.ProductService;
import learn.inventory.domain.Result;
import learn.inventory.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @GetMapping
    public List<Product> findAll() { return service.findAll(); }

    @GetMapping("/{productId}")
    public Product findById(@PathVariable int productId) { return service.findById(productId); }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Product product) {
        Result<Product> result = service.add(product);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> update(@PathVariable int productId, @RequestBody Product product) {
        if (productId != product.getProductId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Product> result = service.update(product);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteById(@PathVariable int productId) {
        if (service.deleteById(productId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
