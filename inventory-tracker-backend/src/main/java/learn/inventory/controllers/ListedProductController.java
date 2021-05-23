package learn.inventory.controllers;

import learn.inventory.domain.ListedProductService;
import learn.inventory.domain.Result;
import learn.inventory.models.ListedProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/listedProduct")
public class ListedProductController {

    private final ListedProductService service;

    public ListedProductController(ListedProductService service) { this.service = service; }

    @GetMapping
    public List<ListedProduct> findAll() { return service.findAll(); }

    @GetMapping("/{listedProductId}")
    public ListedProduct findById(@PathVariable int listedProductId) { return service.findById(listedProductId); }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ListedProduct listedProduct) {
        Result<ListedProduct> result = service.add(listedProduct);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{listedProductId}")
    public ResponseEntity<Object> update(@PathVariable int listedProductId, @RequestBody ListedProduct listedProduct) {
        if (listedProductId != listedProduct.getListedProductId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<ListedProduct> result = service.update(listedProduct);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{listedProductId}")
    public ResponseEntity<Void> deleteById(@PathVariable int listedProductId) {
        if (service.deleteById(listedProductId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
