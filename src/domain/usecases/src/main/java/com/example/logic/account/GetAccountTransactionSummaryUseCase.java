package com.example.logic.account;

import com.example.dtos.account.AccountBalanceDTO;
import com.example.dtos.account.AccountTransactionSummaryDTO;
import com.example.dtos.transaction.TransactionSummaryDTO;
import com.example.entities.constant.TransactionType;
import com.example.ports.IAccountRepositoryPort;
import com.example.ports.ITransactionRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@Component
public class GetAccountTransactionSummaryUseCase implements Function<Integer, Mono<AccountTransactionSummaryDTO>> {

    private final IAccountRepositoryPort accountRepository;
    private final ITransactionRepositoryPort transactionRepository;

    public GetAccountTransactionSummaryUseCase(IAccountRepositoryPort accountRepository, ITransactionRepositoryPort transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Mono<AccountTransactionSummaryDTO> apply(Integer accountId) {
        return this.getAccountBalance(accountId)
                .zipWith(transactionRepository.findByAccountId(accountId)
                        .map(transaction -> TransactionSummaryDTO.builder()
                                .id(transaction.getId())
                                .type(transaction.getType().toString())
                                .amount(transaction.getAmount())
                                .build())
                        .collectList())
                .map(tuple -> AccountTransactionSummaryDTO.builder()
                        .id(tuple.getT1().getId())
                        .number(tuple.getT1().getNumber())
                        .type(tuple.getT1().getType())
                        .balance(tuple.getT1().getBalance())
                        .transactions((List<TransactionSummaryDTO>) tuple.getT2())
                        .build());
    }

    private Mono<AccountBalanceDTO> getAccountBalance(Integer accountId) {
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