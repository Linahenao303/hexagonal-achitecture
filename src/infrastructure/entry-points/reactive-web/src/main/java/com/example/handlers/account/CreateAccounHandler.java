package com.example.handlers.account;

import com.example.dtos.account.AccountDTO;
import com.example.logic.account.CreateAccountUseCase;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateAccounHandler {
    private final CreateAccountUseCase createAccountUseCase;

    public CreateAccounHandler(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    public Mono<AccountDTO> execute(AccountDTO accountDTO) {
        return createAccountUseCase.apply(accountDTO);
    }
}
