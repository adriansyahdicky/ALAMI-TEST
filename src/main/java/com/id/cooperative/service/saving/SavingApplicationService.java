package com.id.cooperative.service.saving;

import com.id.cooperative.dto.response.Response;
import com.id.cooperative.dto.saving.RequestSavingDto;
import com.id.cooperative.entity.Contact;
import com.id.cooperative.entity.SavingApplication;
import com.id.cooperative.exception.BusinessException;
import com.id.cooperative.repository.ContactRepository;
import com.id.cooperative.repository.SavingApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
public class SavingApplicationService {

    private final SavingApplicationRepository savingApplicationRepository;

    private final ContactRepository contactRepository;

    public static final String SUCCESS = "SUCCESS";

    public SavingApplicationService(SavingApplicationRepository savingApplicationRepository, ContactRepository contactRepository) {
        this.savingApplicationRepository = savingApplicationRepository;
        this.contactRepository = contactRepository;
    }

    public Response<SavingApplication> saving(RequestSavingDto requestSavingDto){
        try {
            Optional<Contact> contact =
                    contactRepository.findById(requestSavingDto.getContactId());

            //check transfer melebihi gaji
            if(contact.isPresent()){
                if(requestSavingDto.getAmount().doubleValue() >
                        contact.get().getGaji().doubleValue()){
                    throw new BusinessException("Transfer Anda Melebihi Gaji");
                }
            }

            //check total transfer tidak boleh melebihi gaji dia
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
            String month = requestSavingDto.getSavingDate().format(formatter);
            BigDecimal checkAmount = savingApplicationRepository.checkSumAmount(
                    requestSavingDto.getContactId(),
                    month);

            if(checkAmount!=null) {
                 BigDecimal sumAmount = checkAmount.add(requestSavingDto.getAmount());
                if (sumAmount.doubleValue() > contact.get().getGaji().doubleValue()) {
                    throw new BusinessException("Total Akumulasi Anda Melebihi Gaji");
                }
            }

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
