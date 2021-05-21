package learn.inventory.domain;

import learn.inventory.data.UserRepository;
import learn.inventory.models.User;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(String userId) {
        return repository.findById(userId);
    }

    public Result<User> add(User user) {
        Result<User> result = validate(user);
        validateNoDuplicatesExist(user, result);
        if (result.isSuccess()) {
            result.setPayload(repository.add(user));
        }
        return result;
    }

    private Result<User> validate(User user) {
        Result<User> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        return result;
    }

    private void validateNoDuplicatesExist(User newUser, Result<User> result) {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (newUser.getUserId().equalsIgnoreCase(user.getUserId())) {
                result.addMessage("That username is already in use", ResultType.INVALID);
            }
        }
    }

}
