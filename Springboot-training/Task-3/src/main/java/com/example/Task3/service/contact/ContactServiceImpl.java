package com.example.Task3.service.contact;

import com.example.Task3.Model.Contacts;
import com.example.Task3.Model.UserInfo;
import com.example.Task3.constants.Constant;
import com.example.Task3.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {


    @Autowired
    private ContactRepository contactsRepository;


    @Override
    public boolean existsContactByPhoneNumber(String phoneNumber) {
        return contactsRepository.existsByPhonenums_MbNo(phoneNumber);
    }

    @Override
    public Contacts saveContact(Contacts contact) {
        log.info(Constant.SAVING_CONTACT, contact);
        return contactsRepository.save(contact);
    }

    @Override
    public List<Contacts> getContactsForUser(UserInfo user) {
        log.info(Constant.GETTING_CONTACTS_FOR_USER, user);
        return contactsRepository.getContactsByUserInfo(user);
    }

    @Override
    public Contacts getContactByPhoneNumber(String phoneNumber) {
        Optional<Contacts> contactOptional = contactsRepository.findFirstByPhonenums_MbNo(phoneNumber);
        return contactOptional.orElse(null);
    }

    @Override
    public LocalDateTime getContactStatusByPhoneNumber(String phoneNumber) {
        log.debug(Constant.GETTING_CONTACT_STATUS_FOR_PHONE_NUMBER, phoneNumber);

        Contacts contact = getContactByPhoneNumber(phoneNumber);

        if (contact != null) {
            log.debug(Constant.CONTACT_FOUND, contact);

            LocalDateTime createdTime = contact.getCreatedAt();
            LocalDateTime updatedTime = contact.getUpdatedAt();

            if (updatedTime != null && updatedTime.isEqual(createdTime)) {
                log.debug(Constant.CONTACT_NOT_UPDATED_SINCE_CREATION);
                return null;
            } else {
                log.debug(Constant.CONTACT_LAST_UPDATED_AT, updatedTime);
                return updatedTime;
            }
        } else {
            log.debug(Constant.NO_CONTACT_FOUND_FOR_PHONE_NUMBER);
            return null;
        }
    }

    @Override
    public Contacts updateContact(Long contactId, Contacts updatedContact) {
        log.info(Constant.UPDATING_CONTACT_WITH_ID, contactId, updatedContact);
        Contacts existingContact = contactsRepository.findById(contactId).orElse(null);

        if (existingContact != null) {
            existingContact.setFirstName(updatedContact.getFirstName());
            existingContact.setLastName(updatedContact.getLastName());
            existingContact.setEmail(updatedContact.getEmail());

            existingContact.setUpdatedAt(LocalDateTime.now());

            Contacts updatedContactResult = contactsRepository.save(existingContact);
            log.info(Constant.CONTACT_UPDATED_SUCCESSFULLY, updatedContactResult);
            return updatedContactResult;
        } else {
            log.error(Constant.CONTACT_NOT_FOUND_FOR_UPDATING, contactId);
        }

        return null;
    }

    @Override
    public List<Contacts> getAllContacts() {
        log.info(Constant.GETTING_ALL_CONTACTS);
        return contactsRepository.findAll();
    }
}