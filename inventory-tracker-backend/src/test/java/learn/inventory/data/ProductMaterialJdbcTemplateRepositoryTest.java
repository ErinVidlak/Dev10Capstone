package learn.inventory.data;

import learn.inventory.models.Material;
import learn.inventory.models.Product;
import learn.inventory.models.ProductMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

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
    void shouldFind() {
        List<ProductMaterial> actual = repository.findByProductId(1);
        assertEquals(actual.size(), 2);

        actual = repository.findByProductId(15);
        assertEquals(actual.size(), 0);
    }

    @Test
    void shouldAdd() {
        ProductMaterial productMaterial = makeProductMaterial();
        assertTrue(repository.add(productMaterial));
        try {
            repository.add(productMaterial);
            fail("cannot add the same ProductMaterial twice");
        } catch (DuplicateKeyException ex) {
            // this is expected
        }
    }

    @Test
    void shouldUpdate() {
        ProductMaterial productMaterial = makeProductMaterial();
        productMaterial.setProductId(3);
        repository.add(productMaterial);
        productMaterial.setMaterialQuantity(10);
        assertTrue(repository.update(productMaterial));
    }

    @Test
    void shouldDelete() {
        ProductMaterial productMaterial = makeProductMaterial();
        productMaterial.setProductId(3);
        Material material = new Material();
        material.setMaterialId(5);
        productMaterial.setMaterial(material);
        assertTrue(repository.deleteByKey(3, 5));
        assertFalse(repository.deleteByKey(3, 5));
    }

    private ProductMaterial makeProductMaterial() {
        ProductMaterial productMaterial = new ProductMaterial();
        productMaterial.setMaterialQuantity(1);

        Material material = new Material();
        material.setMaterialId(6);

        productMaterial.setMaterial(material);
        productMaterial.setProductId(4);
        return productMaterial;
    }
}
