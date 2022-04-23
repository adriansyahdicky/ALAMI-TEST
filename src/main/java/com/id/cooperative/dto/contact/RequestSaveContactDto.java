package com.id.cooperative.dto.contact;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSaveContactDto {

    @NotBlank(message = "Name Cannot Required")
    @Size(min = 3, message = "Name Minimum 3 Character")
    private String name;
    @NotBlank(message = "Birth Place Required")
    private String birthPlace;
    @NotNull(message = "Birth Date Required")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private LocalDate birthDate;
    @NotBlank(message = "Address Cannot Required")
    private String address;
    private Boolean enabled;

}
