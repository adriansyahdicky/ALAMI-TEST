package com.id.cooperative.service.saving;

import com.id.cooperative.dto.response.Response;
import com.id.cooperative.dto.saving.RequestSavingDto;
import com.id.cooperative.entity.SavingApplication;
import com.id.cooperative.repository.SavingApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SavingApplicationService {

    private final SavingApplicationRepository savingApplicationRepository;

    public static final String SUCCESS = "SUCCESS";

    public SavingApplicationService(SavingApplicationRepository savingApplicationRepository) {
        this.savingApplicationRepository = savingApplicationRepository;
    }

    public Response<SavingApplication> saving(RequestSavingDto requestSavingDto){
        try {
            SavingApplication savingApplication =
                    SavingApplication.builder().savingDate(requestSavingDto.getSavingDate())
                            .amount(requestSavingDto.getAmount())
                            .contactId(requestSavingDto.getContactId())
                            .status(SUCCESS)
                            .build();
            savingApplicationRepository.save(savingApplication);
            return Response.result(savingApplication);
        }catch (Exception ex){
            log.error("Error saving request {} result {} ", requestSavingDto, ex.getMessage());
            throw ex;
        }
    }

}
