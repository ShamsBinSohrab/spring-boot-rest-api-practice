package com.shams.spring.rest.practice.comshamsspringrestpractice.controller;

import com.shams.spring.rest.practice.comshamsspringrestpractice.model.Contact;
import com.shams.spring.rest.practice.comshamsspringrestpractice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity get(@PathVariable(value = "id") Integer id){
        return contactService.getContact(id);
    }
}
