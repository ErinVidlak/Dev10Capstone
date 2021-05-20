package learn.inventory.data;

import learn.inventory.models.MaterialInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MaterialInventoryJdbcTemplateRepositoryTest {

    @Autowired
    MaterialInventoryJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void findAll() {
        List<MaterialInventory> all = repository.findAll();
        assertTrue(all.size() > 0);
    }

    @Test
    void findById() {
        MaterialInventory inventory = repository.findById(2);
        assertEquals(2, inventory.getInventoryId());
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

    private MaterialInventory makeInventory(){
        MaterialInventory inventory = new MaterialInventory();
        inventory.setTotalQuantity(10);
        inventory.setMaterialId(1);
        return inventory;
    }
}