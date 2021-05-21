package learn.inventory.data;

import learn.inventory.data.MaterialJdbcTemplateRepository;
import learn.inventory.models.Material;
import learn.inventory.models.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MaterialJdbcTemplateRepositoryTest {

    @Autowired
    MaterialJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindYarn() {
        Material yarn = repository.findById(5);
        assertTrue("yarn".equals(yarn.getMaterialName()));
    }

    @Test
    void shouldAddVibranium() {
        Material vibranium = new Material();
        vibranium.setMaterialName("Vibranium");
        vibranium.setPricePerUnit(BigDecimal.valueOf(1000000));
        vibranium.setUserId("test");

        Material actual = repository.add(vibranium);
        assertNotNull(actual);
        assertEquals(6, actual.getMaterialId());
        assertEquals("Vibranium", actual.getMaterialName());
    }

    @Test
    void shouldUpdate() {
        Material yarn = repository.findById(5);
        yarn.setMaterialName("wool yarn");
        assertTrue(repository.update(yarn));
        Material result = repository.findById(5);
        assertEquals(yarn.getMaterialName(), "wool yarn");

    }

    @Test
    void shouldDeleteMaterial() {
        assertTrue(repository.deleteById(4));
    }
}