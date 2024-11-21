package com.example.ports;

import com.example.dtos.transaction.CreateTransactionDTO;
import com.example.entities.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionRepositoryPort {
    Mono<CreateTransactionDTO> save(Transaction transaction);
    Mono<CreateTransactionDTO> findById(Integer id);
    Flux<CreateTransactionDTO> findByAccountId(Integer accountId);
}
