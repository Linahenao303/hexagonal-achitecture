package com.example.logic.account;

import com.example.dtos.account.AccountBalanceDTO;
import com.example.entities.constant.TransactionType;
import com.example.ports.IAccountRepositoryPort;
import com.example.ports.ITransactionRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class GetAccountBalanceUseCase implements Function<Integer, Mono<AccountBalanceDTO>> {

    private final IAccountRepositoryPort accountRepository;
    private final ITransactionRepositoryPort transactionRepository;

    public GetAccountBalanceUseCase(IAccountRepositoryPort accountRepository, ITransactionRepositoryPort transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Mono<AccountBalanceDTO> apply(Integer accountId) {
        return accountRepository.findById(accountId)
                .flatMap(account -> transactionRepository.findByAccountId(accountId)
                        .map(transaction -> transaction.getType().equals(TransactionType.WITHDRAW) ? -transaction.getAmount() : transaction.getAmount())
                        .reduce(Double::sum)
                        .map(balance -> AccountBalanceDTO.builder()
                                .id(account.getId())
                                .number(account.getNumber())
                                .type(account.getType().toString())
                                .balance(balance)
                                .build()));
    }
}