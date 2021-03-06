package com.dev10.userapi.data;

import com.dev10.userapi.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class AppUserJdbcTemplateRepositoryTest {
    @Autowired
    AppUserRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    static boolean hasSetUp = false;

    @BeforeEach
    void setup() {
        if (!hasSetUp) {
            hasSetUp = true;
            jdbcTemplate.update("call set_known_good_state();");
        }
    }

    @Test
    void shouldFindByUsername() {
        AppUser expected = new AppUser(
                "983f1224-af4f-11eb-8368-0242ac110002",
                "john@smith.com",
                "$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa",
                false,
                "ADMIN");

        AppUser actual = repository.findByUsername("john@smith.com");

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByUsernameMissing() {
        AppUser actual = repository.findByUsername("unknown@unknown.com");
        assertNull(actual);
    }

    @Test
    void shouldCreate() {
        AppUser expected = new AppUser(
                String.valueOf(UUID.randomUUID()),
                "bob@jones.com",
                "$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa",
                false,
                "USER");

        AppUser result = repository.create(expected);

        assertNotNull(result);

        AppUser actual = repository.findByUsername(expected.getUsername());

        assertEquals(expected, actual);
    }
}