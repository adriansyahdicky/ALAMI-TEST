package com.id.cooperative.service.loan;

import com.id.cooperative.dto.loan.RequestFilterDateDto;
import com.id.cooperative.dto.response.Response;
import com.id.cooperative.entity.LoanApplication;
import com.id.cooperative.exception.ResourceNotFoundException;
import com.id.cooperative.repository.ContactRepository;
import com.id.cooperative.repository.LoanApplicationRepository;
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
public class LoanApplicationFilterService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final ContactRepository contactRepository;

    public LoanApplicationFilterService(LoanApplicationRepository loanApplicationRepository,
                                        ContactRepository contactRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.contactRepository = contactRepository;
    }

    public Response<Map<String, Object>> filterByDate (
            RequestFilterDateDto requestFilterDateDto){
        try {
            Map<String, Object> mapResult = new HashMap<>();
            Pageable paging = PageRequest.
                    of(0, 100);
            Page<LoanApplication> loanApplications =
                    loanApplicationRepository.findByLoanDateBetween(requestFilterDateDto.getStartDate(),
                            requestFilterDateDto.getFinishDate(), paging);
            mapResult.put("data", loanApplications.getContent());
            mapResult.put("pagination", PageableUtil.pageToPagination(loanApplications));
            return Response.result(mapResult);
        }catch (Exception ex){
            log.error("Error filter loan by date {} ", ex.getMessage());
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
                Page<LoanApplication> loanApplications =
                        loanApplicationRepository.findByContactId(contactId,paging);
                mapResult.put("data", loanApplications.getContent());
                mapResult.put("pagination", PageableUtil.pageToPagination(loanApplications));
            }
            return Response.result(mapResult);
        }catch (Exception ex){
            log.error("Error filter loan by date {} ", ex.getMessage());
            throw ex;
        }
    }

}
