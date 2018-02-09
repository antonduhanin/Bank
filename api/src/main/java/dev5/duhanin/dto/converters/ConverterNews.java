package dev5.duhanin.dto.converters;

import dev5.duhanin.dto.NewsDTO;
import dev5.duhanin.entity.News;
import dev5.duhanin.repository.INewsDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterNews {
    @Autowired
    private ModelMapper modelMapper;

    public News newsToEntity(NewsDTO newsDTO) {
        News news = modelMapper.map(newsDTO, News.class);
        return news;
    }

    public NewsDTO newsToDTO(News news) {
        if(news==null){
            news = new News();
        }
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setContent(news.getContent());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setId(news.getId());
        if(news.getRecipient()!=null) {
            newsDTO.setRecipientNews(news.getRecipient().getId());
        }
        return newsDTO;
    }
}
