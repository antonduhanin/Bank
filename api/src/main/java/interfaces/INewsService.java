package interfaces;

import entity.News;
import entity.User;

import java.util.List;


public interface INewsService {
    News publishNewsForUsers(List<Integer> usersId,int idNews);
    News publishNews(int idUser,int idNews);
    void removeNews(int idNews);
    List<News> NewsList();
}
