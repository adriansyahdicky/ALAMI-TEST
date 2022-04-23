package com.id.cooperative.controller;

import com.id.cooperative.dto.contact.RequestSaveContactDto;
import com.id.cooperative.service.contact.ContactFilterService;
import com.id.cooperative.service.contact.ContactSaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/contact")
public class ContactController {

    private final ContactSaveService contactSaveService;
    private final ContactFilterService contactFilterService;

    public ContactController(ContactSaveService contactSaveService,
                             ContactFilterService contactFilterService) {
        this.contactSaveService = contactSaveService;
        this.contactFilterService = contactFilterService;
    }

    @PostMapping
    public ResponseEntity<Object> saving(@Valid @RequestBody
                                                     RequestSaveContactDto requestSaveContactDto){
        return ResponseEntity.ok(contactSaveService.save(requestSaveContactDto));
    }

    @GetMapping
    public ResponseEntity<Object> getContacts(){
        return ResponseEntity.ok(contactFilterService.findAll());
    }
}
