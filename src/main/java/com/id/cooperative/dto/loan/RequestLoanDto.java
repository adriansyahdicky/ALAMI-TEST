package com.id.cooperative.dto.loan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.id.cooperative.validator.ContactCannotExists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestLoanDto {
    @NotBlank(message = "Contact Id Cannot Required")
    @ContactCannotExists(message = "Contact Cannot Found")
    private String contactId;
    @NotNull(message = "Amount Cannot Required")
    @DecimalMin(value = "100000", message = "Amount Minimum 100.000")
    private BigDecimal amount;
    @NotNull(message = "Loan Date Cannot Required")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private LocalDate loanDate;
}
