package learn.inventory.data;

import learn.inventory.models.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository {
    User findById(String userId);

    List<User> findAll();

    User add(User user);
}
