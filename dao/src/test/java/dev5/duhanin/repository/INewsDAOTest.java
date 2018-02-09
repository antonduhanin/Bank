package dev5.duhanin.repository;

import dev5.duhanin.TestApplication;
import dev5.duhanin.entity.News;
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



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
public class INewsDAOTest {
    @Autowired
    private INewsDAO newsDAO;
    private List<News> newsList;

    @Test
    public void newsListForUser() throws Exception {
        newsList = newsDAO.newsListForUser(2);
        for (News news : newsList) {
            Assert.assertEquals("recipient of news is not 2", 2, news.getRecipient().getId());
        }
    }

    @Test
    public void newsListForAll() throws Exception {
        newsList = newsDAO.newsListForAll();
        for (News news : newsList) {
            Assert.assertNull("recipient of news is not null", news.getRecipient());
        }
    }


}