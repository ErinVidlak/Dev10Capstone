package learn.inventory.domain;

import learn.inventory.data.UserRepository;
import learn.inventory.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {

    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;

    @Test
    void findById() {
        User expected = repository.findById("username");
        assertNotNull(expected);
        when(repository.findById("username")).thenReturn(expected);
        User actual = service.findById("username");
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        User expected = makeUser();
        User mockOut = makeUser();
        when(repository.add(expected)).thenReturn(mockOut);
        Result<User> actual = service.add(expected);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddDuplicate() {
        User user = makeUser();
        when(repository.findAll()).thenReturn(Arrays.asList(user));
        User duplicate = makeUser();
        Result<User> actual = service.add(duplicate);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    private User makeUser() {
        User user = new User();
        user.setUserId("Newbie");
        return user;
    }

}
