package co.example.repositories;

import co.example.entities.AccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IAccountRepository extends ReactiveCrudRepository<AccountEntity, Integer> {
    Flux<AccountEntity> findByUserId(Integer userId);
}