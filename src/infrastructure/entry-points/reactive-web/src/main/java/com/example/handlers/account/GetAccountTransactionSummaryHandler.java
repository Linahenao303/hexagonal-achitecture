package com.example.handlers.account;

import com.example.dtos.account.AccountTransactionSummaryDTO;
import com.example.logic.account.GetAccountTransactionSummaryUseCase;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetAccountTransactionSummaryHandler {

    private final GetAccountTransactionSummaryUseCase getAccountTransactionSummaryUseCase;

    public GetAccountTransactionSummaryHandler(GetAccountTransactionSummaryUseCase getAccountTransactionSummaryUseCase) {
        this.getAccountTransactionSummaryUseCase = getAccountTransactionSummaryUseCase;
    }

    public Mono<AccountTransactionSummaryDTO> getAccountTransactionSummary(Integer accountId) {
        return getAccountTransactionSummaryUseCase.apply(accountId);
    }
}