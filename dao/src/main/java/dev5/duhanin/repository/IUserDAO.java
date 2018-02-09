package dev5.duhanin.repository;

import dev5.duhanin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDAO extends JpaRepository<User, Long> {
    @Query(value = "select id,name,id_role,email,password from users where users.name = :name", nativeQuery = true)
    List<User> findByName(@Param("name") String name);

    @Query(value = "select id,name,id_role,email,password from users where users.email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);
}
