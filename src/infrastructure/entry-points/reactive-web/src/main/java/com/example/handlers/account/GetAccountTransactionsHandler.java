package com.example.handlers.account;

import com.example.dtos.account.AccountTransactionsDTO;
import com.example.logic.account.GetAccountTransactionsUseCase;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetAccountTransactionsHandler {

    private final GetAccountTransactionsUseCase getAccountTransactionsUseCase;

    public GetAccountTransactionsHandler(GetAccountTransactionsUseCase getAccountTransactionsUseCase) {
        this.getAccountTransactionsUseCase = getAccountTransactionsUseCase;
    }

    public Mono<AccountTransactionsDTO> getAccountTransactions(Integer accountId, String type) {
        return getAccountTransactionsUseCase.apply(accountId, type);
    }
}