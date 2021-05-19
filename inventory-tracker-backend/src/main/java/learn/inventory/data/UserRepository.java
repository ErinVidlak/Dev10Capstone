package learn.inventory.data;

import learn.inventory.models.User;

public interface UserRepository {
    User findById(int userId);

    User add(User user);

    boolean update(User user);

    boolean deleteById(int userId);
}
