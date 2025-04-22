package com.cmd.WarehouseManager.UserService;

import com.cmd.WarehouseManager.UserService.data.entity.User;
import com.cmd.WarehouseManager.UserService.data.repository.UserRepository;
import org.cmd.WarehouseManager.CommonTypes.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest(properties = {
                "spring.datasource.url=jdbc:h2:mem:test",
                "spring.jpa.hibernate.ddl-auto=create-drop"
        })
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User testUser;

    @BeforeEach
    public void createTestUser() {
        testUser = new User();
        testUser.setRole(Role.ROLE_WORKER);
        testUser.setLogin("test");
        testUser.setPassword("test");
        userRepository.save(testUser);
    }

    @AfterEach
    public void deleteTestUser() {
        userRepository.delete(testUser);
    }

    @Test
    void testUserCanBeFoundById() {
        User savedUser = userRepository.findById(testUser.getId()).orElse(null);
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(testUser.getLogin(), savedUser.getLogin());
        Assertions.assertEquals(testUser.getPassword(), savedUser.getPassword());
        Assertions.assertEquals(testUser.getRole(), savedUser.getRole());
        Assertions.assertEquals(testUser.getId(), savedUser.getId());
    }

    @Test
    void cantCreate2UsersWithTheSameLogin() {
        User userWithTestUserLogin = new User();
        userWithTestUserLogin.setLogin(testUser.getLogin());
        userWithTestUserLogin.setPassword("aaa");
        userWithTestUserLogin.setRole(Role.ROLE_WORKER);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(userWithTestUserLogin);
        });
    }
}
