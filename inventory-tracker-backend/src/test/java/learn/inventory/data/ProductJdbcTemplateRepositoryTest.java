package learn.inventory.data;

import learn.inventory.models.Product;
import org.junit.jupiter.api.AfterEach;
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
        assertTrue(true);
    }

    @Test
    void shouldFindAll() {
        List<Product> total = repository.findAll();
        assertEquals(3, total.size());
    }

    @Test
    void shouldAdd() {
        Product product = makeProduct();
        Product actual = repository.add(product);
        assertNotNull(actual);
    }

    private Product makeProduct() {
        Product product = new Product();
        product.setProductId(5);
        product.setProductName("Resin paperweight");
        product.setTimeToMake(1);
        product.setUserId("username");
        product.setTotalMaterialsCost(new BigDecimal("10.00"));
        return product;
    }
}
