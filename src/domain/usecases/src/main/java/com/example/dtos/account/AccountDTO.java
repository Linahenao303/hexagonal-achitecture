package com.example.dtos.account;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountDTO {
    @NotEmpty
    private Integer number;
    @NotEmpty
    private String type;
    @NotEmpty
    private Integer userId;
}
