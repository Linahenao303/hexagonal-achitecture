package com.example.ports;

import com.example.dtos.account.CreateAccountDTO;
import com.example.entities.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountRepositoryPort {
    Mono<CreateAccountDTO>save(Account account);
    Mono<CreateAccountDTO>findById(Integer id);
    Flux<CreateAccountDTO>findByUserId(Integer userId);


}
