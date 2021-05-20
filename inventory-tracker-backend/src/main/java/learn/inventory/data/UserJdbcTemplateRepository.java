package learn.inventory.data;

import learn.inventory.data.mappers.UserMapper;
import learn.inventory.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserJdbcTemplateRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(String userId) {
        final String sql = "select user_id "
                + "from user "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new UserMapper(), userId).stream()
                .findAny().orElse(null);
    }

    @Override
    public User add(User user) {
        final String sql = "insert into user (user_id) values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        return user;
    }


    @Transactional
    @Override
    public boolean deleteById(String userId) {
        jdbcTemplate.update("delete from product where user_id = ?", userId);
        jdbcTemplate.update("delete from material where user_id = ?", userId);
        return jdbcTemplate.update("delete from user where user_id = ?", userId) > 0;
    }
}
