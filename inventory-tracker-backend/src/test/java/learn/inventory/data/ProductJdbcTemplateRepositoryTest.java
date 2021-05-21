package learn.inventory.data;

import learn.inventory.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductJdbcTemplateRepositoryTest {

    @Autowired
    ProductJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findById() {
        Product paperweight = makeProduct();
        paperweight = repository.add(paperweight);
        Product actual = repository.findById(paperweight.getProductId());
        assertEquals(paperweight, actual);
    }

    @Test
    void shouldFindProductWithNonNullFields(){
        Product earrings = repository.findById(1);
        System.out.println(earrings);
        assertTrue(earrings.getListedProduct() != null && !earrings.getMaterials().isEmpty());
    }

    @Test
    void shouldFindAll() {
        List<Product> total = repository.findAll();
        assertTrue(total.size() > 0);
    }

    @Test
    void shouldAdd() {
        Product product = makeProduct();
        Product actual = repository.add(product);
        assertNotNull(actual);
    }

    @Test
    void shouldUpdate() {
        Product product = makeProduct();
        product = repository.add(product);
        product.setProductName("Resin necklace");
        assertTrue(repository.update(product));
    }

    @Test
    void shouldDeleteById() {
        Product product = repository.findById(2);
        assertTrue(repository.deleteById(product.getProductId()));
        assertFalse(repository.deleteById(product.getProductId()));
    }

    private Product makeProduct() {
        Product product = new Product();
        product.setProductName("Resin paperweight");
        product.setTimeToMake(1);
        product.setUserId("username");
        product.setTotalMaterialsCost(new BigDecimal("10.00"));
        return product;
    }
}
