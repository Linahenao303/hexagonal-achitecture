package com.example.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountTransactionsDTO {
    private Integer id;
    private Integer number;
    private String type;
    private Integer userId;
    private Map<Integer, Double> transactions;
}
