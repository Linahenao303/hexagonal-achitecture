package com.example.logic.transaction;

import com.example.dtos.transaction.CreateTransactionDTO;
import com.example.dtos.transaction.TransactionDTO;
import com.example.entities.Transaction;
import com.example.ports.ITransactionRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class CreateTransactionUserCase implements Function<TransactionDTO, Mono<TransactionDTO>> {

    private final ITransactionRepositoryPort transactionRepository;

    public CreateTransactionUserCase(ITransactionRepositoryPort transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Mono<TransactionDTO> apply(TransactionDTO transactionDTO) {
        return transactionRepository.save(new Transaction.Builder()
                .type(transactionDTO.getType())
                .amount(transactionDTO.getAmount())
                .accountId(transactionDTO.getAccountId())
                .build())
                .map(transaction -> {
                    return CreateTransactionDTO.builder()
                            .id(transaction.getId())
                            .amount(transaction.getAmount())
                            .accountId(transaction.getAccountId())
                            .build();
                });
    }
}