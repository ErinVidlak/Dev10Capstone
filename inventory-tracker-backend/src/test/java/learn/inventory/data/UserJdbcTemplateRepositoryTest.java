package learn.inventory.data;

import learn.inventory.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {

    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
<<<<<<< Updated upstream
    void findById() { assertTrue(true); }
=======
    void shouldFindTest() {
        User test = repository.findById("test");
        assertEquals("test", test.getUserId());
    }
>>>>>>> Stashed changes

    @Test
    void shouldAddNewbie() {
        User user = new User();
        user.setUserId("Newbie");
        User actual = repository.add(user);
        assertNotNull(actual);
        assertEquals("Newbie", actual.getUserId());
    }


    @Test
    void shouldDeleteUser() {
        assertTrue(repository.deleteById("test"));
        assertTrue(repository.deleteById("fake"));
    }
}