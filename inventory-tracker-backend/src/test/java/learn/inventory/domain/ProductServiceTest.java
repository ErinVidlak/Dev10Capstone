package learn.inventory.domain;

import learn.inventory.data.ProductRepository;
import learn.inventory.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
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

    private Product makeProduct() {
        Product product = new Product();
        product.setProductName("Resin paperweight");
        product.setTimeToMake(1);
        product.setUserId("username");
        product.setTotalMaterialsCost(new BigDecimal("10.00"));
        return product;
    }

}
