package com.example.Task3.repository;

import com.example.Task3.Model.Contacts;
import com.example.Task3.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, Long> {
    @Query("SELECT c FROM Contacts c WHERE c.userInfo = :userInfo")
    List<Contacts> getContactsByUserInfo(UserInfo userInfo);
    boolean existsByPhonenums_MbNo(String mbNo);
    Optional<Contacts> findFirstByPhonenums_MbNo(String phoneNumber);


}
