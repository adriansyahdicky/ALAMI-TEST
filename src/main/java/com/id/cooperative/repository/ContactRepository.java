package com.id.cooperative.repository;

import com.id.cooperative.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    @Query(value = "select * from contact where name ilike %?1% ", nativeQuery = true)
    List<Contact> findByName(String name);
}
