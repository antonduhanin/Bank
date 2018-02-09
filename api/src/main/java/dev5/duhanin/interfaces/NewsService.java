package dev5.duhanin.interfaces;

import dev5.duhanin.dto.NewsDTO;

import java.util.List;


public interface NewsService {
    NewsDTO publishNewsForAll(NewsDTO newsDTO);

    NewsDTO publishNewsForUser(NewsDTO newsDTO, long idUser);

    NewsDTO updateNews(NewsDTO newsDTO);

    List<NewsDTO> newsListForAll();

    List<NewsDTO> newsListForUser(long idUser);

    List<NewsDTO> newsList();

    void removeNews(long idNews);
}
