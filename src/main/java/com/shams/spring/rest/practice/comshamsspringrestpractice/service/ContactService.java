package com.shams.spring.rest.practice.comshamsspringrestpractice.service;

import com.shams.spring.rest.practice.comshamsspringrestpractice.model.Contact;
import com.shams.spring.rest.practice.comshamsspringrestpractice.repository.IContactRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class ContactService {

    @Autowired
    IContactRepository contactRepository;

    public ResponseEntity createContact(Contact contact){
        try {
            contact.validate();
            contactRepository.save(contact);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("contact/{id}").buildAndExpand(contact.getId()).toUri();
            return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(contact);
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    public ResponseEntity getContact(Integer id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact doesn't exist!!"));
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }
}
