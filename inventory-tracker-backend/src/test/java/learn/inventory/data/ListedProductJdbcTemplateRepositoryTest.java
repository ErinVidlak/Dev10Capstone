package learn.inventory.data;

import learn.inventory.models.ListedProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ListedProductJdbcTemplateRepositoryTest {

    @Autowired
    ListedProductJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findById() {
        ListedProduct necklace = makeListedProduct();
        repository.add(necklace);
        ListedProduct actual = repository.findById(3);
        assertEquals(necklace, actual);
    }

    @Test
    void shouldFindAll() {
        List<ListedProduct> total = repository.findAll();
        assertEquals(2, total.size());
    }

    @Test
    void shouldAdd() {
        ListedProduct listedProduct = makeListedProduct();
        ListedProduct actual = repository.add(listedProduct);
        assertNotNull(actual);
    }

    @Test
    void shouldUpdate() {
        ListedProduct listedProduct = makeListedProduct();
        repository.add(listedProduct);
        listedProduct.setListingName("Resin Paperweight with Purple Wildflowers");
        assertTrue(repository.update(listedProduct));
    }

    @Test
    void shouldDeleteById() {
        ListedProduct listedProduct = makeListedProduct();
        repository.add(listedProduct);
        assertTrue(repository.deleteById(3));
    }

    private ListedProduct makeListedProduct() {
        ListedProduct listedProduct = new ListedProduct();
        listedProduct.setListingName("Resin necklace");
        listedProduct.setListedPrice(new BigDecimal("30.00"));
        listedProduct.setDateListed(LocalDate.now());
        listedProduct.setFeeAmount(new BigDecimal("3.50"));
        listedProduct.setSold(false);
        listedProduct.setProductId(2);
        return listedProduct;
    }
}
