package com.twowasik_project.repository;

import com.twowasik_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByUsername(String username);

    User findById(int id);

    @Transactional
    @Modifying
    @Query(value = "update users set password = :pass where user_id = :userId", nativeQuery = true)
    void updatePassword(@Param("pass") String newPassword, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET user_name = :user_name WHERE user_id = :user_id", nativeQuery = true)
    void updateUsername(@Param("user_name") String username, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET avatar = :avatar WHERE user_id = :user_id", nativeQuery = true)
    void updateAvatar(@Param("avatar") String avatar, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET teams = :teams WHERE user_id = :user_id", nativeQuery = true)
    void updateTeams(@Param("teams") String teams, @Param("user_id") int userId);
}