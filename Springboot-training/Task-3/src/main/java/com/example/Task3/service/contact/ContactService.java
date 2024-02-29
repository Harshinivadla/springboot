package com.example.Task3.service.contact;

import com.example.Task3.Model.Contacts;
import com.example.Task3.Model.UserInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ContactService {
    Contacts saveContact(Contacts contact);

    List<Contacts> getContactsForUser(UserInfo userInfo);

     Contacts updateContact(Long contactId, Contacts updatedContact);
    Contacts getContactByPhoneNumber(String phoneNumber);

    LocalDateTime getContactStatusByPhoneNumber(String phoneNumber);
    List<Contacts> getAllContacts();
    boolean existsContactByPhoneNumber(String phoneNumber);




}