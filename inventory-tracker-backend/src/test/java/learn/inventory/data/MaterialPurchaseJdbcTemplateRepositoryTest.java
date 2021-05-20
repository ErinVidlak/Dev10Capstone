package learn.inventory.data;

import learn.inventory.models.MaterialPurchase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
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
        MaterialPurchase purchase = new MaterialPurchase();
        //(2050.00, 100, '', '2021-11-26', 'small chain, bought from Michaels', 3)
        purchase.setPurchasePrice(new BigDecimal("2050.00"));
        purchase.setQuantityPurchased(100);
        purchase.setUnits("");
        purchase.setDatePurchased(Date.valueOf("2021-11-26").toLocalDate());
        purchase.setDescription("small chain, bought in bulk from Michaels");
        purchase.setMaterialId(3);

        MaterialPurchase result = repository.add(purchase);
        System.out.println(purchase);
        System.out.println(result);
        assertEquals(purchase, result);
        assertNotNull(result);

    }


    @Test
    void update() {
        MaterialPurchase purchase = new MaterialPurchase();
        purchase.setPurchasePrice(new BigDecimal("20500.00"));
        purchase.setQuantityPurchased(1000);
        purchase.setUnits("");
        purchase.setDatePurchased(Date.valueOf("2021-11-27").toLocalDate());
        purchase.setDescription("small chain, bought in bulk from Michaels");
        purchase.setMaterialId(3);

        MaterialPurchase result = repository.add(purchase);

        result.setDescription("small chain bought in bulk from Home Goods");
        assertTrue(repository.update(result));

    }

    @Test
    void deleteById() {
    }
}