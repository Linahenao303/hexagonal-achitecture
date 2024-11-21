package com.example.dtos.account;

import com.example.dtos.transaction.TransactionSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountTransactionSummaryDTO extends AccountBalanceDTO{
    private List<TransactionSummaryDTO> transactions;

}
