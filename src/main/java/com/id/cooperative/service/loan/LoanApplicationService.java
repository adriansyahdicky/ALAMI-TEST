package com.id.cooperative.service.loan;

import com.id.cooperative.dto.loan.RequestLoanDto;
import com.id.cooperative.dto.response.Response;
import com.id.cooperative.entity.LoanApplication;
import com.id.cooperative.repository.LoanApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    public static final String SUCCESS = "SUCCESS";

    public LoanApplicationService(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }

    public Response<LoanApplication> loan(RequestLoanDto requestLoanDto){
        try {
            LoanApplication loanApplication =
                    LoanApplication.builder().loanDate(requestLoanDto.getLoanDate())
                            .amount(requestLoanDto.getAmount())
                            .contactId(requestLoanDto.getContactId())
                            .status(SUCCESS)
                            .build();
            loanApplicationRepository.save(loanApplication);
            return Response.result(loanApplication);
        }catch (Exception ex){
            log.error("Error loan request {} result {} ", requestLoanDto, ex.getMessage());
            throw ex;
        }
    }

}
