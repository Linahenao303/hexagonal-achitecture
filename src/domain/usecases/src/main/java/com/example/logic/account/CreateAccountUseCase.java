package com.example.logic.account;

import com.example.dtos.account.AccountDTO;
import com.example.dtos.account.CreateAccountDTO;
import com.example.entities.Account;
import com.example.ports.IAccountRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class CreateAccountUseCase implements Function<AccountDTO, Mono<AccountDTO>> {

    private final IAccountRepositoryPort accountRepository;

    public CreateAccountUseCase(IAccountRepositoryPort accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Mono<AccountDTO> apply(AccountDTO accountDTO) {
        return accountRepository.save(new Account.Builder()
                        .number(accountDTO.getNumber())
                        .type(accountDTO.getType())
                        .userId(accountDTO.getUserId())
                        .build())
                .map(account -> {
                    return CreateAccountDTO.builder()
                            .id(account.getId())
                            .number(account.getNumber())
                            .type(account.getType())
                            .userId(account.getUserId())
                            .build();
                });
    }
}
