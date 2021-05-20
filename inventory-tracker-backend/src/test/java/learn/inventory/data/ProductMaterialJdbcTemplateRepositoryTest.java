package learn.inventory.data;

import learn.inventory.models.Material;
import learn.inventory.models.Product;
import learn.inventory.models.ProductMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductMaterialJdbcTemplateRepositoryTest {

    @Autowired
    ProductMaterialJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldAdd() {
        ProductMaterial productMaterial = makeProductMaterial();
        assertTrue(repository.add(productMaterial));
        try {
            repository.add(productMaterial);
            fail("cannot add the same ProductMaterial twice");
        } catch (DataAccessException ex) {
            // this is expected
        }
    }

    @Test
    void shouldUpdate() {
        ProductMaterial productMaterial = makeProductMaterial();
        repository.add(productMaterial);
        productMaterial.setMaterialQuantity(10);
        assertTrue(repository.update(productMaterial));
    }

    private ProductMaterial makeProductMaterial() {
        ProductMaterial productMaterial = new ProductMaterial();
        productMaterial.setMaterialQuantity(15);

        Material material = new Material();
        material.setMaterialId(4);

        productMaterial.setMaterial(material);
        productMaterial.setProductId(1);
        return productMaterial;
    }
}
