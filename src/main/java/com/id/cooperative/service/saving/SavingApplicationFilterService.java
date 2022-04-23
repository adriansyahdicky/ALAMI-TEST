package com.id.cooperative.service.saving;

import com.id.cooperative.dto.response.Response;
import com.id.cooperative.dto.saving.RequestFilterDateDto;
import com.id.cooperative.entity.SavingApplication;
import com.id.cooperative.exception.ResourceNotFoundException;
import com.id.cooperative.repository.ContactRepository;
import com.id.cooperative.repository.SavingApplicationRepository;
import com.id.cooperative.util.PageableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class SavingApplicationFilterService {

    private final SavingApplicationRepository savingApplicationRepository;
    private final ContactRepository contactRepository;

    public SavingApplicationFilterService(SavingApplicationRepository savingApplicationRepository,
                                          ContactRepository contactRepository) {
        this.savingApplicationRepository = savingApplicationRepository;
        this.contactRepository = contactRepository;
    }

    public Response<Map<String, Object>> filterByDate (
            RequestFilterDateDto requestFilterDateDto){
        try {
            Map<String, Object> mapResult = new HashMap<>();
            Pageable paging = PageRequest.
                    of(0, 100);
            Page<SavingApplication> savingApplications =
                    savingApplicationRepository.findBySavingDateBetween(requestFilterDateDto.getStartDate(),
                            requestFilterDateDto.getFinishDate(), paging);
            mapResult.put("data", savingApplications.getContent());
            mapResult.put("pagination", PageableUtil.pageToPagination(savingApplications));
            return Response.result(mapResult);
        }catch (Exception ex){
            log.error("Error filter saving by date {} ", ex.getMessage());
            throw ex;
        }
    }

    public Response<Map<String, Object>> filterByContactId (String contactId){
        try {
            Map<String, Object> mapResult = new HashMap<>();
            boolean contact = Optional.ofNullable(contactRepository.findById(contactId)
                            .orElseThrow(() -> new ResourceNotFoundException("Contact Not Found ")))
                    .isPresent();
            if(contact) {
                Pageable paging = PageRequest.
                        of(0, 100);
                Page<SavingApplication> savingApplications =
                        savingApplicationRepository.findByContactId(contactId,paging);
                mapResult.put("data", savingApplications.getContent());
                mapResult.put("pagination", PageableUtil.pageToPagination(savingApplications));
            }
            return Response.result(mapResult);
        }catch (Exception ex){
            log.error("Error filter saving by date {} ", ex.getMessage());
            throw ex;
        }
    }

}
