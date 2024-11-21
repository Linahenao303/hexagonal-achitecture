package com.example.logic.users;

import com.example.dtos.users.UserAccountSummaryDTO;
import com.example.dtos.account.AccountTransactionSummaryDTO;
import com.example.ports.IUserRepositoryPort;
import com.example.ports.IAccountRepositoryPort;
import com.example.logic.account.GetAccountTransactionSummaryUseCase;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetUserAccountSummaryUseCase {

    private final IUserRepositoryPort userRepository;
    private final IAccountRepositoryPort accountRepository;
    private final GetAccountTransactionSummaryUseCase getAccountTransactionSummaryUseCase;

    public GetUserAccountSummaryUseCase(IUserRepositoryPort userRepository, IAccountRepositoryPort accountRepository, GetAccountTransactionSummaryUseCase getAccountTransactionSummaryUseCase) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.getAccountTransactionSummaryUseCase = getAccountTransactionSummaryUseCase;
    }

    public Mono<UserAccountSummaryDTO> getUserAccountSummary(Integer userId) {
        return userRepository.findById(userId)
                .zipWith(accountRepository.findByUserId(userId)
                        .flatMap(account -> getAccountTransactionSummaryUseCase.apply(account.getId()))
                        .collectList())
                .map(tuple -> {
                    Double totalBalance = tuple.getT2().stream()
                            .mapToDouble(AccountTransactionSummaryDTO::getBalance)
                            .sum();
                    return UserAccountSummaryDTO.builder()
                            .name(tuple.getT1().getName())
                            .email(tuple.getT1().getEmail())
                            .address(tuple.getT1().getAddress())
                            .accounts(tuple.getT2())
                            .totalBalance(totalBalance)
                            .build();
                });
    }
}