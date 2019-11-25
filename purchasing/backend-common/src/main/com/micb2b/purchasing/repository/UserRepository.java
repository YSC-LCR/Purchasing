package com.micb2b.purchasing.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.micb2b.purchasing.domain.User;

/**
 * @author jay
 * @date 2019-11-19
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {

    /**
     * findByUsername
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * findByEmail
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * modify password
     * @param id
     * @param pass
     */
    @Modifying
    @Query(value = "update user set password = ?2 , last_password_reset_time = ?3 where id = ?1",nativeQuery = true)
    void updatePass(Long id, String pass, Date lastPasswordResetTime);

    /**
     * modify photo
     * @param id
     * @param url
     */
    @Modifying
    @Query(value = "update user set avatar = ?2 where id = ?1",nativeQuery = true)
    void updateAvatar(Long id, String url);

    /**
     * modify mail
     * @param id
     * @param email
     */
    @Modifying
    @Query(value = "update user set email = ?2 where id = ?1",nativeQuery = true)
    void updateEmail(Long id, String email);
}
