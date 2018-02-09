package dev5.duhanin.repository;

import dev5.duhanin.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionDAO extends JpaRepository<Transaction, Long> {

    @Query(value = "select id,date,title,recipient,id_card,summa from transactions where transactions.recipient = :recipient", nativeQuery = true)
    List<Transaction> transactionListByAccount(@Param("recipient") long recipient);
}
