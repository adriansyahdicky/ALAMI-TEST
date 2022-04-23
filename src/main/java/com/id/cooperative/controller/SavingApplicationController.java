package com.id.cooperative.controller;

import com.id.cooperative.dto.saving.RequestFilterDateDto;
import com.id.cooperative.dto.saving.RequestSavingDto;
import com.id.cooperative.service.saving.SavingApplicationFilterService;
import com.id.cooperative.service.saving.SavingApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/saving")
public class SavingApplicationController {

    private final SavingApplicationService savingApplicationService;
    private final SavingApplicationFilterService savingApplicationFilterService;

    public SavingApplicationController(SavingApplicationService savingApplicationService,
                                       SavingApplicationFilterService savingApplicationFilterService) {
        this.savingApplicationService = savingApplicationService;
        this.savingApplicationFilterService = savingApplicationFilterService;
    }

    @PostMapping
    public ResponseEntity<Object> saving(@Valid @RequestBody
                                                 RequestSavingDto requestSavingDto){
        return ResponseEntity.ok(savingApplicationService.saving(requestSavingDto));
    }

    @PostMapping(value = "/filter-by-date")
    public ResponseEntity<Object> filterByDate(@Valid @RequestBody RequestFilterDateDto requestFilterDateDto){
        return ResponseEntity.ok(savingApplicationFilterService.filterByDate(requestFilterDateDto));
    }

    @GetMapping(value = "/filter-by-contact/{contactId}")
    public ResponseEntity<Object> filterByContact(@PathVariable("contactId") String contactId){
        return ResponseEntity.ok(savingApplicationFilterService.filterByContactId(contactId));
    }
}
