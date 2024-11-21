package co.example.repositories;

import co.example.entities.TransactionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ITransactionRepository extends ReactiveCrudRepository<TransactionEntity, Integer> {
    Flux<TransactionEntity> findByAccountId(Integer accountId);
}
