package learn.inventory.domain;

import learn.inventory.data.ProductRepository;
import learn.inventory.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductServiceTest {

    @Autowired
    ProductService service;

    @MockBean
    ProductRepository repository;


    @Test
    void findById() {
        Product expected = repository.findById(1);
        when(repository.findById(1)).thenReturn(expected);
        Product actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Product expected = makeProduct();
        Product mockOut = makeProduct();
        when(repository.add(expected)).thenReturn(mockOut);
        Result<Product> actual = service.add(expected);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddDuplicate() {
        Product product = makeProduct();
        when(repository.findAll()).thenReturn(Arrays.asList(product));
        Product duplicate = makeProduct();
        Result<Product> actual = service.add(duplicate);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() {
        Product product = makeProduct();
        product.setProductId(1);
        when(repository.update(product)).thenReturn(true);
        Result<Product> actual = service.update(product);
        assertEquals(ResultType.SUCCESS, actual.getType());

    }

    @Test
    void shouldNotUpdateInvalidId() {
        Product product = makeProduct();
        product.setProductId(0);
        when(repository.update(product)).thenReturn(false);
        Result<Product> actual = service.update(product);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenIdNotFound() {
        Product product = makeProduct();
        product.setProductId(20);
        when(repository.update(product)).thenReturn(false);
        Result<Product> actual = service.update(product);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
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
