package learn.inventory.controllers;


import learn.inventory.domain.MaterialPurchaseService;
import learn.inventory.domain.Result;
import learn.inventory.models.MaterialPurchase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/material/purchases")
public class MaterialPurchaseController {

    private final MaterialPurchaseService service;

    public MaterialPurchaseController(MaterialPurchaseService service) {
        this.service = service;
    }

    @GetMapping
    public List<MaterialPurchase> findAll(){
        return service.findAll();
    }

    @GetMapping("/{purchaseId}")
    public MaterialPurchase findById(@PathVariable int purchaseId){
        return service.findById(purchaseId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody MaterialPurchase purchase){
        Result<MaterialPurchase> result = service.add(purchase);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{purchaseId}")
    public ResponseEntity<Object> update(@PathVariable int purchaseId, @RequestBody MaterialPurchase purchase){
        if(purchaseId != purchase.getPurchaseId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<MaterialPurchase> result = service.update(purchase);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<Void> deleteById(@PathVariable int purchaseId){
        if (service.deleteById(purchaseId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }








}
