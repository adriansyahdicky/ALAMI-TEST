package com.id.cooperative.service.contact;

import com.id.cooperative.dto.response.Response;
import com.id.cooperative.dto.contact.RequestSaveContactDto;
import com.id.cooperative.entity.Contact;
import com.id.cooperative.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactSaveService {

    private final ContactRepository contactRepository;

    public ContactSaveService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Response<RequestSaveContactDto> save(RequestSaveContactDto requestSaveContactDto){
        try {
            Contact contact =
                    Contact.builder()
                            .name(requestSaveContactDto.getName())
                            .birthDate(requestSaveContactDto.getBirthDate())
                            .birthPlace(requestSaveContactDto.getBirthPlace())
                            .address(requestSaveContactDto.getAddress())
                            .enabled(requestSaveContactDto.getEnabled())
                            .hoby(requestSaveContactDto.getHoby())
                            .gaji(requestSaveContactDto.getGaji()).build();
            contactRepository.save(contact);
            return Response.result(requestSaveContactDto);
        }catch (Exception ex){
            log.error("Error save contact request {} result {} ", requestSaveContactDto, ex.getMessage());
            throw ex;
        }
    }

}
