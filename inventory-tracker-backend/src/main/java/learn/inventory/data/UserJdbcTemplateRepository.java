package learn.inventory.data;

import learn.inventory.data.mappers.MaterialMapper;
import learn.inventory.data.mappers.ProductMapper;
import learn.inventory.data.mappers.UserMapper;
import learn.inventory.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //This method sets the list of materials and products belonging to a user who is currently logged in
    //May have to alter after adding Security features
    @Override
    @Transactional
    public User findById(String userId) {
        final String sql = "select user_id "
                + "from user "
                + "where user_id = ?;";

        User user = jdbcTemplate.query(sql, new UserMapper(), userId).stream()
                .findAny().orElse(null);
        if(user != null){
            addMaterials(user);
            addProducts(user);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        final String sql = "select user_id "
                + "from user limit 1000;";

        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        for (User user : users) {
            addMaterials(user);
            addProducts(user);
        }
        return users;
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

    private void addMaterials(User user){
        final String sql = "select material_id, material_name, price_per_unit, user_id " +
                "from material " +
                "where user_id = ? ";
        var materials = jdbcTemplate.query(sql, new MaterialMapper(), user.getUserId());
        user.setMaterials(materials);

    }

    private void addProducts(User user){
        final String sql = "select product_id, product_name, total_materials_cost, time_to_make, user_id  "
                + "from product "
                + "where user_id = ?;";
        var products = jdbcTemplate.query(sql, new ProductMapper(), user.getUserId());
        user.setProducts(products);

    }
}
