package com.id.cooperative.controller;

import com.id.cooperative.dto.loan.RequestFilterDateDto;
import com.id.cooperative.dto.loan.RequestLoanDto;
import com.id.cooperative.service.loan.LoanApplicationFilterService;
import com.id.cooperative.service.loan.LoanApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/loan")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;
    private final LoanApplicationFilterService loanApplicationFilterService;

    public LoanApplicationController(LoanApplicationService loanApplicationService, LoanApplicationFilterService loanApplicationFilterService) {
        this.loanApplicationService = loanApplicationService;
        this.loanApplicationFilterService = loanApplicationFilterService;
    }

    @PostMapping
    public ResponseEntity<Object> saving(@Valid @RequestBody
                                                 RequestLoanDto requestLoanDto){
        return ResponseEntity.ok(loanApplicationService.loan(requestLoanDto));
    }

    @PostMapping(value = "/filter-by-date")
    public ResponseEntity<Object> filterByDate(@Valid @RequestBody RequestFilterDateDto requestFilterDateDto){
        return ResponseEntity.ok(loanApplicationFilterService.filterByDate(requestFilterDateDto));
    }

    @GetMapping(value = "/filter-by-contact/{contactId}")
    public ResponseEntity<Object> filterByContact(@PathVariable("contactId") String contactId){
        return ResponseEntity.ok(loanApplicationFilterService.filterByContactId(contactId));
    }

}
