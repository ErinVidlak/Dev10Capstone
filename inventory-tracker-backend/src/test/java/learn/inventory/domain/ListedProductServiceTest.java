package learn.inventory.domain;

import learn.inventory.data.ListedProductRepository;
import learn.inventory.models.ListedProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ListedProductServiceTest {
    @Autowired
    ListedProductService service;

    @MockBean
    ListedProductRepository repository;


    @Test
    void findById() {
        ListedProduct expected = repository.findById(1);
        when(repository.findById(1)).thenReturn(expected);
        ListedProduct actual = service.findById(1);
        assertEquals(expected, actual);
    }


    @Test
    void shouldAdd() {
        ListedProduct expected = makeProductListing();
        ListedProduct mockOut = makeProductListing();

        when(repository.add(expected)).thenReturn(mockOut);
        Result<ListedProduct> actual = service.add(expected);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());


    }

    @Test
    void shouldUpdate() {
        ListedProduct listing = makeProductListing();
        listing.setListedProductId(1);

        when(repository.update(listing)).thenReturn(true);
        Result<ListedProduct> actual = service.update(listing);
        assertEquals(ResultType.SUCCESS, actual.getType());

    }

    @Test
    void shouldNotUpdateIfDatesInvalid() {
        ListedProduct listing = makeProductListing();
        listing.setListedProductId(1);
        listing.setDateSold(LocalDate.of(2019,1,1));

        when(repository.update(listing)).thenReturn(false);
        Result<ListedProduct> actual = service.update(listing);
        System.out.println(actual.getMessages());
        assertEquals(ResultType.INVALID, actual.getType());

    }

    @Test
    void shouldNotUpdateIfIdNotFound() {
        ListedProduct listing = makeProductListing();
        listing.setListedProductId(20);
        when(repository.update(listing)).thenReturn(false);
        Result<ListedProduct> actual = service.update(listing);
        assertEquals(ResultType.NOT_FOUND, actual.getType());

    }



    @Test
    void shouldNotUpdateIfBadId() {
        ListedProduct listing = makeProductListing();
        listing.setListedProductId(0);
        when(repository.update(listing)).thenReturn(false);
        Result<ListedProduct> actual = service.update(listing);
        assertEquals(ResultType.INVALID, actual.getType());

    }

    private ListedProduct makeProductListing(){
        ListedProduct product = new ListedProduct();
        product.setListingName("Customizable Silver Keychain");
        product.setListedPrice(new BigDecimal("39.99"));
        product.setFeeAmount(new BigDecimal("5.00"));
        product.setDateListed(LocalDate.of(2021, 3,15));
        product.setProductId(2);
        return product;



    }
}