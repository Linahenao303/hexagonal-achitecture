package com.example.dtos.users;

import com.example.dtos.account.AccountTransactionSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserAccountSummaryDTO {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private Double totalBalance;
    private List<AccountTransactionSummaryDTO> accounts;
}
