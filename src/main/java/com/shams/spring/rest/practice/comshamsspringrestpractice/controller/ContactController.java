package com.shams.spring.rest.practice.comshamsspringrestpractice.controller;

import com.shams.spring.rest.practice.comshamsspringrestpractice.model.Contact;
import com.shams.spring.rest.practice.comshamsspringrestpractice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/contacts")
    public ResponseEntity createContact(@Valid @RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @GetMapping("/contacts")
    public ResponseEntity getContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity getContact(@PathVariable(value = "id") Integer id){
        return contactService.getContact(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateContact(@PathVariable(value = "id") Integer id, @Valid @RequestBody Contact contact) {
        return contactService.updateContact(id, contact);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteContact(@PathVariable(value = "id") Integer id) {
        return contactService.deleteContact(id);
    }
}
