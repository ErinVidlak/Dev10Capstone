package learn.inventory.controllers;

import learn.inventory.domain.MaterialService;
import learn.inventory.domain.Result;
import learn.inventory.models.Material;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService service;


    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @GetMapping
    public List<Material> findAll(){
        return service.findAll();
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<Material> findById(@PathVariable int materialId){
        Material material = service.findById(materialId);
        if (material == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(material);
    }

//    @PostMapping
//    public ResponseEntity<Object> add(@RequestBody Material material){
//        Result<Void> result = service.add(material);
//        if(result.isSuccess()){
//            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
//        }
//        return ErrorResponse.build(result);
//    }
//
//    @PutMapping("/{materialId}")
//    public ResponseEntity<Object> update(@PathVariable int materialId, @RequestBody Material material) {
//        if(materialId != material.getMaterialId()){
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//        Result<Material> result = service.update(material);
//        if(result.isSuccess()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return ErrorResponse.build(result);
//    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<Void> deleteById(@PathVariable int materialId){
        if (service.deleteById(materialId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
