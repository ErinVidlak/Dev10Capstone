package learn.inventory.data;

import learn.inventory.models.User;

public class UserJdbcTemplateRepository implements UserRepository{
    @Override
    public User findById(int userId) {
        return null;
    }

    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean deleteById(int userId) {
        return false;
    }
}
