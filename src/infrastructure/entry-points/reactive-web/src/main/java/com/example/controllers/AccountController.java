package com.example.controllers;

import com.example.dtos.account.AccountBalanceDTO;
import com.example.dtos.account.AccountDTO;
import com.example.dtos.account.AccountTransactionSummaryDTO;
import com.example.dtos.account.AccountTransactionsDTO;
import com.example.handlers.account.CreateAccounHandler;
import com.example.handlers.account.GetAccountBalanceHandler;
import com.example.handlers.account.GetAccountTransactionSummaryHandler;
import com.example.handlers.account.GetAccountTransactionsHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("api/account")
@RestController
public class AccountController {

    private final CreateAccounHandler createAccounHandler;

    private final GetAccountBalanceHandler getAccountBalanceHandler;

    private final GetAccountTransactionSummaryHandler getAccountTransactionSummaryHandler;

    private final GetAccountTransactionsHandler getAccountTransactionsHandler;

    @PostMapping
    public Mono<ResponseEntity<AccountDTO>>createAccount(AccountDTO accountDTO){
        return createAccounHandler.execute(accountDTO).map(ResponseEntity::ok);
    }

    @GetMapping("/{accountId}/balance")
    public Mono<AccountBalanceDTO> getAccountBalance(@PathVariable Integer accountId) {
        return getAccountBalanceHandler.getAccountBalance(accountId);
    }

    @GetMapping("/{accountId}/summary")
    public Mono<AccountTransactionSummaryDTO> getAccountTransactionSummary(@PathVariable Integer accountId) {
        return getAccountTransactionSummaryHandler.getAccountTransactionSummary(accountId);
    }

    @GetMapping("/{accountId}/{type}")
    public Mono<AccountTransactionsDTO> getAccountTransactions(@PathVariable Integer accountId, @PathVariable String type) {
        return getAccountTransactionsHandler.getAccountTransactions(accountId, type);
    }
}

