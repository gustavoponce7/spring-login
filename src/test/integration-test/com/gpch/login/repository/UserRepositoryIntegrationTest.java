package com.gpch.login.repository;

import com.gpch.login.model.Role;
import com.gpch.login.model.User;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {UserRepositoryIntegrationTest.Initializer.class})
@SpringBootTest
public class UserRepositoryIntegrationTest {

    @ClassRule
    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7.22");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void testSaveNewUser() {
        userRepository.deleteAll();
        roleRepository.deleteAll();

        Role role = roleRepository.save(Role.builder().role("ADMIN").build());
        User user = userRepository.save(User.builder()
                .name("Gustavo")
                .lastName("Ponce")
                .email("test@test.com")
                .userName("gustavo.ponce")
                .active(true)
                .roles(new HashSet<>(Arrays.asList(role)))
                .password(bCryptPasswordEncoder.encode("1234"))
                .build());

        assertThat(user)
                .matches(u -> u.getId() != null && u.getName().equals("Gustavo") && u.getUserName().equals("gustavo.ponce"));
        assertThat(userRepository.findAll().size() == 1);
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mySQLContainer.getUsername(),
                    "spring.datasource.password=" + mySQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
