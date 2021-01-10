package com.bluebik.shorturl.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bluebik.shorturl.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    @Query(value = "SELECT * FROM user WHERE id = :id", nativeQuery = true)
    User findUserById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    User findByUsername(@Param("username") String username);
}
