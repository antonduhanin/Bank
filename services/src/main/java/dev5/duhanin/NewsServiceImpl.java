package dev5.duhanin;

import dev5.duhanin.dto.NewsDTO;
import dev5.duhanin.dto.converters.ConverterNews;
import dev5.duhanin.entity.News;
import dev5.duhanin.entity.User;
import dev5.duhanin.exceptions.NotFoundException;
import dev5.duhanin.interfaces.NewsService;
import dev5.duhanin.repository.INewsDAO;
import dev5.duhanin.repository.IUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final static Logger LOG = LoggerFactory.getLogger(NewsService.class);
    @Autowired
    private INewsDAO newsDAO;
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private ConverterNews converterNews;

    @Transactional
    public NewsDTO publishNewsForAll(NewsDTO newsDTO) {
        LOG.info("start creating news for all in newsService");
        LOG.trace("newsDTO: ", newsDTO);
        News news = converterNews.newsToEntity(newsDTO);
        news = newsDAO.save(news);
        return converterNews.newsToDTO(news);
    }

    @Transactional
    public NewsDTO publishNewsForUser(NewsDTO newsDTO, long idUser) {
        LOG.info("start creating news for user in newsService");
        LOG.trace("NewsDTO: ", newsDTO);
        News news = converterNews.newsToEntity(newsDTO);
        User user = userDAO.findOne(idUser);
        if (user == null) {
            throw new NotFoundException("user not found in newsService");
        }
        news.setRecipient(user);
        news = newsDAO.save(news);
        LOG.info("news saved in newsService");
        return converterNews.newsToDTO(news);
    }

    @Override
    public NewsDTO updateNews(NewsDTO newsDTO) {
        LOG.info("start updating news  in newsService");
        LOG.trace("newsDTO: ", newsDTO);
        News news = converterNews.newsToEntity(newsDTO);
        if (newsDAO.findOne(newsDTO.getId()) != null) {
            news = newsDAO.save(news);
            LOG.info("news was updated");
        } else {
            throw new NotFoundException("news not exist in newsService");
        }
        return converterNews.newsToDTO(news);
    }

    public List<NewsDTO> newsListForAll() {
        LOG.info("start output news for all in newsService");
        List<News> newsList = newsDAO.newsListForAll();
        return newsList.stream()
                .map(news -> converterNews.newsToDTO(news))
                .collect(Collectors.toList());
    }

    public List<NewsDTO> newsListForUser(long idUser) {
        LOG.info("start output news for user in newsService");
        if (userDAO.findOne(idUser) == null) {
            throw new NotFoundException("user not found");
        }
        List<News> newsList = newsDAO.newsListForUser(idUser);
        return newsList.stream()
                .map(news -> converterNews.newsToDTO(news))
                .collect(Collectors.toList());
    }

    public List<NewsDTO> newsList() {
        LOG.info("start output all news in newsService");
        List<News> newsList = newsDAO.findAll();
        return newsList.stream()
                .map(news -> converterNews.newsToDTO(news))
                .collect(Collectors.toList());
    }

    @RolesAllowed(value = {"Administrator"})
    @Transactional
    public void removeNews(long idNews) {
        LOG.info("start deleting news  in newsService");
        if (newsDAO.findOne(idNews) != null) {
            newsDAO.delete(idNews);
            LOG.info("news deleted");
        } else {
            throw new NotFoundException("news not found in NewsService");
        }
    }
}
