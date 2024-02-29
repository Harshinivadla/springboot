package com.example.Task3.repository;

import com.example.Task3.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    @Query("SELECT u FROM UserInfo u WHERE u.email = :identifier OR u.phNo = :identifier")
    UserInfo findByEmailOrPhNo(@Param("identifier") String identifier);
}
