package dev5.duhanin.repository;

import dev5.duhanin.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountDAO extends JpaRepository<Account, Long> {
    @Query(value = "select id,id_customer,balance,state from accounts where accounts.id_customer = :id_customer", nativeQuery = true)
    List<Account> accountListByUser(@Param("id_customer") long id_customer);
}
