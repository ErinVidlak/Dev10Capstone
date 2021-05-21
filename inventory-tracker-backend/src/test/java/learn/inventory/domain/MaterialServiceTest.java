package learn.inventory.domain;

import learn.inventory.data.MaterialJdbcTemplateRepository;
import learn.inventory.models.Material;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MaterialServiceTest {

    @Autowired
    MaterialService service;

    @MockBean
    MaterialJdbcTemplateRepository repository;

    @Test
    void findById() {
        Material expected = repository.findById(1);
        when(repository.findById(1)).thenReturn(expected);
        Material actual = service.findById(1);
        assertEquals(expected, actual);
    }


    @Test
    void shouldAdd() {
        Material expected = makeMaterial();
        Material mockOut = makeMaterial();

        when(repository.add(expected)).thenReturn(mockOut);
        Result<Material> actual = service.add(expected);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldUpdate() {
        Material material = makeMaterial();
        material.setMaterialName("Titanium");

        when(repository.update(material)).thenReturn(true);
        Result<Material> actual = service.update(material);
        System.out.println(actual.getMessages());
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateIfIdNotFound() {
        Material material = makeMaterial();
        material.setMaterialId(1616161);
        when(repository.update(material)).thenReturn(false);
        Result<Material> actual = service.update(material);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateIfBadId() {
        Material material = makeMaterial();
        material.setMaterialId(-3);
        when(repository.update(material)).thenReturn(false);
        Result<Material> actual = service.update(material);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    private Material makeMaterial(){
        Material material = new Material();
        material.setMaterialName("Vibranium");
        material.setPricePerUnit(new BigDecimal("5000000"));
        material.setUserId("username");
        return material;
    }
}