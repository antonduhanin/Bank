package dev5.duhanin.repository;

import dev5.duhanin.TestApplication;
import dev5.duhanin.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

//@ContextConfiguration(classes = JpaConfig.class, loader = AnnotationConfigContextLoader.class)


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
public class IUserDAOTest {
    @Autowired
    private IUserDAO userDAO;
    private List<User> users;

    @Test
    public void findByName() throws Exception {
        users = userDAO.findByName("Jack");
        for (User user : users) {
            Assert.assertEquals("users name is not Jack", user.getName(), "Jack");
        }
    }

}