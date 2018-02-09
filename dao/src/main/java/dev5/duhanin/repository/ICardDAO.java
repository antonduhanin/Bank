package dev5.duhanin.repository;

import dev5.duhanin.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardDAO extends JpaRepository<Card, Long> {
}
