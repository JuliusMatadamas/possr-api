package com.possr.repositories.possr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.possr.entities.possr.UserAppEntity;

import jakarta.transaction.Transactional;

@Repository
public interface AuthRepository extends JpaRepository<UserAppEntity, Long> {
    @Query("SELECT u FROM UserAppEntity u WHERE u.username = :username AND u.userId = :employeeId")
    UserAppEntity findByUsernameAndEmployeeId(String username, Long employeeId);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO sessions
        (
            user_id,
            token,
            ip_address,
            user_agent,
            is_active,
            login_at,
            last_activity_at,
            expires_at
        )
        VALUES
        (
            :userId,
            :token,
            NULL,
            NULL,
            1,
            NOW(),
            NOW(),
            DATE_ADD(NOW(), INTERVAL 1 HOUR)
        )
    """, nativeQuery = true)
    void createSession(Long userId, String token);
}
