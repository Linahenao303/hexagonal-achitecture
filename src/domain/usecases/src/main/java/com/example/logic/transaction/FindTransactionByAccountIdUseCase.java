package com.example.logic.transaction;

import com.example.dtos.transaction.CreateTransactionDTO;
import com.example.ports.ITransactionRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Component
public class FindTransactionByAccountIdUseCase implements Function<Integer, Flux<CreateTransactionDTO>> {

    private final ITransactionRepositoryPort transactionRepository;

    public FindTransactionByAccountIdUseCase(ITransactionRepositoryPort transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Flux<CreateTransactionDTO> apply(Integer accountId) {
        return transactionRepository.findByAccountId(accountId)
                .map(transaction -> CreateTransactionDTO.builder()
                        .id(transaction.getId())
                        .amount(transaction.getAmount())
                        .accountId(transaction.getAccountId())
                        .build());
    }
}