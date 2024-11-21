package com.example.logic.account;

import com.example.dtos.account.AccountTransactionsDTO;
import com.example.entities.constant.TransactionType;
import com.example.ports.IAccountRepositoryPort;
import com.example.ports.ITransactionRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.function.BiFunction;

@Component
public class GetAccountTransactionsUseCase implements BiFunction<Integer, String, Mono<AccountTransactionsDTO>> {

    private final IAccountRepositoryPort accountRepository;
    private final ITransactionRepositoryPort transactionRepository;

    public GetAccountTransactionsUseCase(IAccountRepositoryPort accountRepository, ITransactionRepositoryPort transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Mono<AccountTransactionsDTO> apply(Integer accountId, String type) {
        return accountRepository.findById(accountId)
                .zipWith(transactionRepository.findByAccountId(accountId)
                        .filter(transaction -> transaction.getType().equals(TransactionType.fromString(type)))
                        .collectList())
                .map(tuple -> AccountTransactionsDTO.builder()
                        .id(tuple.getT1().getId())
                        .number(tuple.getT1().getNumber())
                        .type(tuple.getT1().getType().toString())
                        .userId(tuple.getT1().getUserId())
                        .transactions(tuple.getT2().stream().collect(Collectors.toMap(transaction -> transaction.getId(), transaction -> transaction.getAmount())))
                        .build());
    }
}