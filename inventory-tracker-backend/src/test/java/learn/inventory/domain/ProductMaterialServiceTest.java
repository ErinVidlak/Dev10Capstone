package learn.inventory.domain;

import learn.inventory.data.ProductMaterialRepository;
import learn.inventory.models.Material;
import learn.inventory.models.ProductMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductMaterialServiceTest {

    @Autowired
    ProductMaterialService service;

    @MockBean
    ProductMaterialRepository repository;


    @Test
    void findByProductId() {
        List<ProductMaterial> initialList = repository.findByProductId(1);
        assertEquals(initialList.size(), 2);

        ProductMaterial expected = initialList.get(0);
        when(repository.findByProductId(1).get(0)).thenReturn(expected);
        ProductMaterial actual = service.findByProductId(1).get(0);
        assertEquals(expected, actual);
    }

//    @Test
//    void shouldAdd() {
//        ProductMaterial expected = makeProductMaterial();
//        ProductMaterial mockOut = makeProductMaterial();
//        when(repository.add(expected)).thenReturn(mockOut);
//        Result<ProductMaterial> actual = service.add(expected);
//        assertEquals(ResultType.SUCCESS, actual.getType());
//        assertEquals(mockOut, actual.getPayload());
//    }

    @Test
    void shouldNotAddDuplicate() {
        ProductMaterial productMaterial = makeProductMaterial();
        when(repository.findByProductId(1)).thenReturn(Arrays.asList(productMaterial));
        ProductMaterial duplicate = makeProductMaterial();
        Result<ProductMaterial> actual = service.add(duplicate);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() {
        ProductMaterial productMaterial = makeProductMaterial();
        productMaterial.setMaterialQuantity(50);
        when(repository.update(productMaterial)).thenReturn(true);
        Result<ProductMaterial> actual = service.update(productMaterial);
        assertEquals(ResultType.SUCCESS, actual.getType());

    }

    @Test
    void shouldNotUpdateInvalidId() {
        ProductMaterial productMaterial = makeProductMaterial();
        productMaterial.setProductId(0);
        when(repository.update(productMaterial)).thenReturn(false);
        Result<ProductMaterial> actual = service.update(productMaterial);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenIdNotFound() {
        ProductMaterial productMaterial = makeProductMaterial();
        productMaterial.setProductId(20);
        when(repository.update(productMaterial)).thenReturn(false);
        Result<ProductMaterial> actual = service.update(productMaterial);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    private ProductMaterial makeProductMaterial() {
        ProductMaterial productMaterial = new ProductMaterial();
        productMaterial.setProductId(1);
        productMaterial.setMaterialQuantity(1000);
        productMaterial.setMaterial(makeMaterial());
        return productMaterial;
    }

    private Material makeMaterial(){
        Material material = new Material();
        material.setMaterialName("Vibranium");
        material.setPricePerUnit(new BigDecimal("5000000"));
        material.setUserId("username");
        return material;
    }

}
