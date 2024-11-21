package com.example.handlers.account;

import com.example.dtos.account.AccountBalanceDTO;
import com.example.logic.account.GetAccountBalanceUseCase;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetAccountBalanceHandler {

    private final GetAccountBalanceUseCase getAccountBalanceUseCase;

    public GetAccountBalanceHandler(GetAccountBalanceUseCase getAccountBalanceUseCase) {
        this.getAccountBalanceUseCase = getAccountBalanceUseCase;
    }

    public Mono<AccountBalanceDTO>getAccountBalance(Integer accountId) {
        return getAccountBalanceUseCase.apply(accountId);
    }
}
