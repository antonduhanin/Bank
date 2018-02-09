package dev5.duhanin.repository;

import dev5.duhanin.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsDAO extends JpaRepository<News, Long> {
    @Query(value = "select id,title,content,recipient from news where news.recipient = :recipient", nativeQuery = true)
    List<News> newsListForUser(@Param("recipient") long recipient);

    @Query(value = "select id,title,content,recipient from news where news.recipient is NULL", nativeQuery = true)
    List<News> newsListForAll();
}
