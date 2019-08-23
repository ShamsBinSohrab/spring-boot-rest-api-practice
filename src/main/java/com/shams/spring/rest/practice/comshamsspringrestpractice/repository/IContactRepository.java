package com.shams.spring.rest.practice.comshamsspringrestpractice.repository;

import com.shams.spring.rest.practice.comshamsspringrestpractice.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactRepository extends CrudRepository<Contact, Integer> {
}
