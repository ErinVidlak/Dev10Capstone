package learn.inventory.domain;

import learn.inventory.data.MaterialInventoryRepository;
import learn.inventory.data.MaterialPurchaseRepository;
import learn.inventory.models.MaterialInventory;
import learn.inventory.models.MaterialPurchase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MaterialInventoryServiceTest {

    @Autowired
    MaterialInventoryService service;

    @MockBean
    MaterialInventoryRepository repository;

    @Test
    void shouldAdd() {
        MaterialInventory expected = makeInventory();
        MaterialInventory mockOut = makeInventory();

        when(repository.add(expected)).thenReturn(mockOut);
        Result<MaterialInventory> actual = service.add(expected);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());

        when(repository.add(expected)).thenReturn(mockOut);
        actual = service.add(expected);
        assertEquals(ResultType.INVALID, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldUpdate() {
        MaterialInventory inventory = makeInventory();
        inventory.setInventoryId(6);
        when(repository.update(inventory)).thenReturn(true);

        Result<MaterialInventory> actual = service.update(inventory);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateIfInvalidId() {
        MaterialInventory inventory = makeInventory();
        inventory.setInventoryId(0);
        when(repository.update(inventory)).thenReturn(false);

        Result<MaterialInventory> actual = service.update(inventory);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotUpdateIfIdNotFound() {
        MaterialInventory inventory = makeInventory();
        inventory.setInventoryId(1);
        when(repository.update(inventory)).thenReturn(false);

        Result<MaterialInventory> actual = service.update(inventory);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }
    

    private MaterialInventory makeInventory(){
        MaterialInventory inventory = new MaterialInventory();
        inventory.setTotalQuantity(5);
        inventory.setMaterialId(6);
        return inventory;
    }
}