package com.example.handlers.transaction;

import com.example.dtos.transaction.TransactionDTO;
import com.example.logic.transaction.CreateTransactionUserCase;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateTransactionHandler {

    private final CreateTransactionUserCase createTransactionUseCase;

    public CreateTransactionHandler(CreateTransactionUserCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    public Mono<TransactionDTO> execute(TransactionDTO transactionDTO) {
        return createTransactionUseCase.apply(transactionDTO);
    }
}
