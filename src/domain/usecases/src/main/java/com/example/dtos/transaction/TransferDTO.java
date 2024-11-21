package com.example.dtos.transaction;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TransferDTO {
    @NotEmpty
    private Integer sourceAccountId;
    @NotEmpty
    private Integer destinationAccountId;
    @NotEmpty
    private Double amount;
}
