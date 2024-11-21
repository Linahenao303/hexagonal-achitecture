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
public class TransactionDTO {
    @NotEmpty
    private String type;
    @NotEmpty
    private Double amount;
    @NotEmpty
    private Integer accountId;

}
