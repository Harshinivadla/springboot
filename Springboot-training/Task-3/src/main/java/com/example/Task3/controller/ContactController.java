package com.example.Task3.controller;

import com.example.Task3.Model.Contacts;
import com.example.Task3.Model.PhoneNum;
import com.example.Task3.Model.UserInfo;
import com.example.Task3.api.ApiResponse;
import com.example.Task3.constants.Constant;
import com.example.Task3.exception.ContactNotFoundException;
import com.example.Task3.exception.UnauthorizedAccessException;
import com.example.Task3.service.contact.ContactService;
import com.example.Task3.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Contacts")
@Slf4j
public class ContactController {

    @Autowired
    private ContactService contactsService;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @PostMapping("/save")
    @PreAuthorize("hasRole('" + Constant.ROLE_USER + "')")
    public ResponseEntity<?> saveContact(@Valid @RequestBody Contacts contact) {
        log.info(Constant.RECEIVED_REQUEST_TO_SAVE_CONTACT, contact);

        String errorMessage = null;
        if (contact.getPhonenums() != null) {
            for (PhoneNum phoneNum : contact.getPhonenums()) {
                if (contactsService.existsContactByPhoneNumber(phoneNum.getMbNo())) {
                    errorMessage = Constant.ERROR_MESSAGE;
                    break;
                }
            }
        }

        Contacts savedContact = null;
        if (errorMessage == null) {
            savedContact = contactsService.saveContact(contact);
            log.info(Constant.CONTACT_SAVED_SUCCESSFULLY, savedContact);
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("contact", savedContact);
        if (errorMessage != null) {
            responseMap.put("error", errorMessage);
        }

        return ResponseEntity.ok(responseMap);
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('" + Constant.ROLE_USER + "')")
    public ResponseEntity<List<Contacts>> getContactsForUser() {
        log.info(Constant.RECEIVED_REQUEST_TO_GET_CONTACTS_FOR_USER);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserInfo user = userServiceImpl.findByEmailOrPhNo(username);
        log.debug(Constant.USER_FOUND, user);

        List<Contacts> userContacts = contactsService.getContactsForUser(user);

        List<Contacts> simplifiedContacts = userContacts.stream()
                .map(contact -> new Contacts(
                        contact.getId(),
                        contact.getFirstName(),
                        contact.getLastName(),
                        null,
                        contact.getPhonenums(),
                        contact.getEmail(),
                        contact.getCreatedAt(),
                        contact.getUpdatedAt()
                ))
                .collect(Collectors.toList());

        log.info(Constant.RETURNING_CONTACTS_FOR_USER, simplifiedContacts.size());
        return ResponseEntity.ok(simplifiedContacts);
    }

    @GetMapping("/status/{PhNum}")
    @PreAuthorize("hasRole('" + Constant.ROLE_USER + "')")
    public ResponseEntity<ApiResponse<String>> getContactStatus(@PathVariable String PhNum) {
        log.info(Constant.RECEIVED_REQUEST_TO_GET_CONTACT_STATUS, PhNum);
        ApiResponse<String> response = new ApiResponse<>();

        LocalDateTime latestUpdateTime = contactsService.getContactStatusByPhoneNumber(PhNum);

        if (latestUpdateTime != null) {
            log.info(Constant.CONTACT_LAST_UPDATED_AT, latestUpdateTime);
            response = new ApiResponse<>(200, "Contact was last updated at", latestUpdateTime.toString());
        } else {
            log.info(Constant.CONTACT_NOT_UPDATED_SINCE_CREATION);
            response = new ApiResponse<>(200, "Contact was not updated since creation", null);
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{contactId}")
    @PreAuthorize("hasRole('" + Constant.ROLE_USER + "')")
    public ResponseEntity<String> updateContact(@PathVariable Long contactId, @RequestBody Contacts updatedContact) {
        try {
            Contacts updatedContactResult = contactsService.updateContact(contactId, updatedContact);
            return ResponseEntity.ok(Constant.CONTACT_UPDATED_SUCCESSFULLY);
        } catch (ContactNotFoundException e) {
            log.error(Constant.CONTACT_WITH_ID_NOT_FOUND_FOR_UPDATING, contactId);
            return ResponseEntity.notFound().build();
        } catch (UnauthorizedAccessException e) {
            log.error(Constant.AUTHENTICATED_USER_DOES_NOT_HAVE_PERMISSION_TO_UPDATE_CONTACT, contactId);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Constant.PERMISSION_TO_UPDATE_CONTACT_DENIED);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('" + Constant.ROLE_SUPERADMIN + "')")
    public ResponseEntity<List<Contacts>> getAllContacts() {
        log.info(Constant.RECEIVED_REQUEST_TO_GET_ALL_CONTACTS);
        List<Contacts> allContacts = contactsService.getAllContacts();
        log.info(Constant.RETURNING_CONTACTS, allContacts.size());
        return ResponseEntity.ok(allContacts);
    }
}