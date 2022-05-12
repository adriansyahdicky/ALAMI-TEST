package com.id.cooperative.service.contact;

import com.id.cooperative.dto.response.Response;
import com.id.cooperative.entity.Contact;
import com.id.cooperative.repository.ContactRepository;
import com.id.cooperative.util.PageableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ContactFilterService {

    private final ContactRepository contactRepository;

    public ContactFilterService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Response<Map<String, Object>> findAll(){
        try {
            Map<String, Object> mapResult = new HashMap<>();
            Pageable paging = PageRequest.
                    of(0, 100);
            Page<Contact> contacts =
                    contactRepository.findAll(paging);
            mapResult.put("data", contacts.getContent());
            mapResult.put("pagination", PageableUtil.pageToPagination(contacts));
            return Response.result(mapResult);
        }catch (Exception ex){
            log.error("Error find all contact {} ", ex.getMessage());
            throw ex;
        }
    }

    public Response<Map<String, Object>> findByName(String parameter){
        try {
            Map<String, Object> mapResult = new HashMap<>();
            mapResult.put("data", contactRepository.findByName(parameter));
            return Response.result(mapResult);
        }catch (Exception ex){
            log.error("Error find all contact {} ", ex.getMessage());
            throw ex;
        }
    }
}
