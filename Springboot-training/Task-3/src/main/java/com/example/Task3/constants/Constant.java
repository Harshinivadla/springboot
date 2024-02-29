package com.example.Task3.constants;

public class Constant {
    public static String ERROR_MESSAGE = "A contact with the same phone number already exists";

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_SUPERADMIN = "ROLE_SUPERADMIN";
    public static final String EMAIL_REG = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String RECEIVED_REQUEST_TO_SAVE_CONTACT = "Received request to save contact: {}";
    public static final String CONTACT_SAVED_SUCCESSFULLY = "Contact saved successfully: {}";
    public static final String RECEIVED_REQUEST_TO_GET_CONTACTS_FOR_USER = "Received request to get contacts for user";
    public static final String USER_FOUND = "User found: {}";
    public static final String RETURNING_CONTACTS_FOR_USER = "Returning {} contacts for user";
    public static final String RECEIVED_REQUEST_TO_GET_CONTACT_STATUS = "Received request to get contact status for phone number: {}";
    public static final String CONTACT_LAST_UPDATED_AT = "Contact was last updated at: {}";
    public static final String CONTACT_NOT_UPDATED_SINCE_CREATION = "Contact was not updated since creation.";
    public static final String CONTACT_UPDATED_SUCCESSFULLY = "Contact updated successfully";
    public static final String CONTACT_WITH_ID_NOT_FOUND_FOR_UPDATING = "Contact with ID {} not found for updating.";
    public static final String AUTHENTICATED_USER_DOES_NOT_HAVE_PERMISSION_TO_UPDATE_CONTACT = "Authenticated user does not have permission to update contact with ID {}";
    public static final String PERMISSION_TO_UPDATE_CONTACT_DENIED = "You do not have permission to update this contact.";
    public static final String RECEIVED_REQUEST_TO_GET_ALL_CONTACTS = "Received request to get all contacts";
    public static final String RETURNING_CONTACTS = "Returning {} contacts";

    public static final String RECEIVED_REQUEST_TO_ADD_NEW_USER = "Received request to add new user: {}";
    public static final String USER_AUTHENTICATED_SUCCESSFULLY = "User {} authenticated successfully";
    public static final String RECEIVED_AUTHENTICATION_REQUEST = "Received authentication request for user: {}";
    public static final String AUTHENTICATION_FAILED = "Authentication failed for user: {}";
    public static final String USER_NOT_FOUND = "User not found: {}";

    public static final String AUTHENTICATION_FAILED_MESSAGE = "Authentication failed";
    public static final String SAVING_CONTACT = "Saving contact: {}";
    public static final String GETTING_CONTACTS_FOR_USER = "Getting contacts for user: {}";
    public static final String GETTING_CONTACT_STATUS_FOR_PHONE_NUMBER = "Getting contact status for phone number: {}";
    public static final String CONTACT_FOUND = "Contact found: {}";
    public static final String NO_CONTACT_FOUND_FOR_PHONE_NUMBER = "No contact found for the provided phone number.";
    public static final String UPDATING_CONTACT_WITH_ID = "Updating contact with ID: {}. New data: {}";
    public static final String CONTACT_NOT_FOUND_FOR_UPDATING = "Contact with ID {} not found for updating.";
    public static final String GETTING_ALL_CONTACTS = "Getting all contacts.";

    public static final String FINDING_USER_BY_EMAIL_OR_PHONE_NUMBER = "Finding user by email or phone number: {}";
    public static final String ADDING_USER_DETAILS = "Adding user details: {}";
    public static final String USER_DETAILS_ADDED_SUCCESSFULLY = "User details added successfully.";
}
