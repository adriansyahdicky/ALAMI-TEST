package com.id.cooperative.dto.saving;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestFilterDateDto {

    @NotNull(message = "Start Date Cannot Required")
    private LocalDate startDate;
    @NotNull(message = "Finish Date Cannot Required")
    private LocalDate finishDate;
}
