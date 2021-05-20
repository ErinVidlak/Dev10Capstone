package learn.inventory.data;

import learn.inventory.models.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository {
    User findById(String userId);

    User add(User user);

    @Transactional
    boolean deleteById(String userId);
}
