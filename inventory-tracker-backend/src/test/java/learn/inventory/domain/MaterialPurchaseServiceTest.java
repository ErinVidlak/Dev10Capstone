package learn.inventory.domain;

import learn.inventory.data.ListedProductRepository;
import learn.inventory.data.MaterialPurchaseRepository;
import learn.inventory.models.ListedProduct;
import learn.inventory.models.MaterialPurchase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MaterialPurchaseServiceTest {

    @Autowired
    MaterialPurchaseService service;

    @MockBean
    MaterialPurchaseRepository repository;

    @Test
    void shouldAdd() {
        MaterialPurchase expected = makePurchase();
        MaterialPurchase mockOut = makePurchase();

        when(repository.add(expected)).thenReturn(mockOut);
        Result<MaterialPurchase> actual = service.add(expected);
        System.out.println(actual.getMessages());
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }


    @Test
    void shouldUpdate() {
        MaterialPurchase purchase = makePurchase();
        purchase.setPurchaseId(2);

        when(repository.update(purchase)).thenReturn(true);
        Result<MaterialPurchase> actual = service.update(purchase);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateIfIdNotFound() {
        MaterialPurchase purchase = makePurchase();
        purchase.setPurchaseId(20);

        when(repository.update(purchase)).thenReturn(false);
        Result<MaterialPurchase> actual = service.update(purchase);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }
    @Test
    void shouldNotUpdateIfIdInvalid() {
        MaterialPurchase purchase = makePurchase();
        purchase.setPurchaseId(0);

        when(repository.update(purchase)).thenReturn(false);
        Result<MaterialPurchase> actual = service.update(purchase);
        assertEquals(ResultType.INVALID, actual.getType());
    }





    //		(1000.00, 2, '3 carats', '2020-05-12', 'two 3 carat cut emeralds from Kay Jewelers', 2),
    private MaterialPurchase makePurchase(){
        MaterialPurchase purchase = new MaterialPurchase();
        purchase.setPurchasePrice(new BigDecimal("1500.00"));
        purchase.setQuantityPurchased(3);
        purchase.setMaterialId(2);
        return purchase;
    }
}