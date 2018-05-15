package dev5.duhanin.controllers;

import dev5.duhanin.dto.NewsDTO;
import dev5.duhanin.interfaces.NewsService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/news")
@Api(value = "accountControllerAPI")
public class NewsController {
    private static final Logger LOG = LoggerFactory.getLogger(NewsController.class);
    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/newsForAll", method = RequestMethod.GET)
    public List<NewsDTO> getNewsForAll() {
        LOG.debug("start output news");
        return newsService.newsListForAll();

    }

    @RequestMapping(method = RequestMethod.POST)
    public NewsDTO create(@Valid @RequestBody NewsDTO newsDTO) {
        LOG.debug("start creating news for all");
        return newsService.publishNewsForAll(newsDTO);
    }


    @RequestMapping(value = "/recipient", method = RequestMethod.POST)
    public NewsDTO createForUser(@RequestParam("recipient") long idUser,@Valid @RequestBody NewsDTO newsDTO) {
        LOG.debug("start creating news for user");
        return newsService.publishNewsForUser(newsDTO, idUser);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Validated
    public void deleteNews(@RequestParam("id") long idNews) {
        LOG.debug("start deleting news");
        newsService.removeNews(idNews);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<NewsDTO> getAllNews() {
        LOG.debug("start output all news");
        return newsService.newsList();
    }


}
