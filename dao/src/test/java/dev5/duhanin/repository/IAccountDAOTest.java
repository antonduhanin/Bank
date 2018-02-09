package dev5.duhanin.repository;


import dev5.duhanin.TestApplication;
import dev5.duhanin.entity.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
public class IAccountDAOTest {
    @Autowired
    IAccountDAO accountDAO;

    @Test
    public void accountListByUser() throws Exception {
        List<Account> accounts = accountDAO.accountListByUser(2);
        for (Account account : accounts) {
            Assert.assertEquals("id user is not 2", account.getUser().getId(), 2);
        }
    }
}