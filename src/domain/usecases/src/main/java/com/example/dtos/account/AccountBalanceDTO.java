package com.example.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountBalanceDTO {
    private Integer id;
    private Integer number;
    private String type;
    private Double balance;

}
