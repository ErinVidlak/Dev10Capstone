package learn.inventory.data;

import learn.inventory.models.MaterialPurchase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MaterialPurchaseJdbcTemplateRepositoryTest {

    @Autowired
    MaterialPurchaseJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @AfterEach
    void teardown() {
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<MaterialPurchase> all = repository.findAll();
        all.forEach(System.out::println);
        assertTrue(all.size() > 0);
    }

    @Test
    void findById() {
        MaterialPurchase purchase = repository.findById(3);
        assertEquals(3, purchase.getPurchaseId());
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}