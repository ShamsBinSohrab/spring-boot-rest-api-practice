package com.shams.spring.rest.practice.comshamsspringrestpractice.service;

import com.shams.spring.rest.practice.comshamsspringrestpractice.model.Contact;
import com.shams.spring.rest.practice.comshamsspringrestpractice.repository.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    IContactRepository contactRepository;

    public ResponseEntity createContact(Contact contact){
        try {
            contact.validate();
            contactRepository.save(contact);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contact.getId()).toUri();
            return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(contact);
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    public ResponseEntity getContact(Integer id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(contact);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact doesn't exist!!");
    }

    public ResponseEntity getAllContacts() {
        List<Contact> contactList = (List<Contact>) contactRepository.findAll();
        if (contactList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(contactList);
    }

    public ResponseEntity updateContact(Integer id, Contact contact) {
        try {
            contact.validate();
            Optional<Contact> contactFromDB = contactRepository.findById(id);
            if (contactFromDB.isPresent()) {
                Contact contactToUpdate = contactFromDB.get();
                contactToUpdate.setFirstName(contact.getFirstName());
                contactToUpdate.setLastName(contact.getLastName());
                contactToUpdate.setEmailAddress(contact.getEmailAddress());
                contactToUpdate.setPhoneNumber(contact.getPhoneNumber());
                contactRepository.save(contactToUpdate);
                return ResponseEntity.status(HttpStatus.OK).body(contactToUpdate);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact doesn't exist!!");
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    public ResponseEntity deleteContact(Integer id) {
        Optional<Contact> contactToDelete = contactRepository.findById(id);
        if (contactToDelete.isPresent()) {
            contactRepository.delete(contactToDelete.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact doesn't exist!!");
    }
}
