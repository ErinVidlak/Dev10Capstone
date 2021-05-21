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
        ListedProduct keychain = makeListedProduct();
        ListedProduct actual = repository.add(keychain);
        ListedProduct expected = repository.findById(actual.getListedProductId());
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAll() {
        List<ListedProduct> total = repository.findAll();
        assertTrue(total.size() > 0);
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
        listedProduct = repository.add(listedProduct);
        listedProduct.setListingName("Resin Paperweight with Purple Wildflowers");
        assertTrue(repository.update(listedProduct));
    }

    @Test
    void shouldDeleteById() {
        ListedProduct listedProduct = makeListedProduct();
        listedProduct = repository.add(listedProduct);
        assertTrue(repository.deleteById(listedProduct.getListedProductId()));
    }

    private ListedProduct makeListedProduct() {
        ListedProduct listedProduct = new ListedProduct();
        listedProduct.setListingName("Personalized silver keychain");
        listedProduct.setListedPrice(new BigDecimal("30.00"));
        listedProduct.setDateListed(LocalDate.of(2021, 5,1));
        listedProduct.setFeeAmount(new BigDecimal("3.50"));
        listedProduct.setProductId(4);
        return listedProduct;
    }
}
