package dev5.duhanin.repository;

import dev5.duhanin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDAO extends JpaRepository<Role, Long> {
}
