package com.possr.repositories.possr.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.possr.entities.possr.admin.UserEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (employee_id, role_id, username, password) VALUES (:#{#user.employeeId}, :#{#user.roleId}, :#{#user.username}, :#{#user.password})", nativeQuery = true)
    int createUser(@Param("user") UserEntity userEntity);

    @Transactional
    @Query(value = "SELECT * FROM users WHERE username = :username LIMIT 1", nativeQuery = true)
    UserEntity findByUsername(@Param("username") String username);
}

