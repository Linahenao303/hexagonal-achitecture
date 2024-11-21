package com.example.handlers.user;

import com.example.dtos.users.UserAccountSummaryDTO;
import com.example.logic.users.GetUserAccountSummaryUseCase;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetUserAccountSummaryHandler {

    private final GetUserAccountSummaryUseCase getUserAccountSummaryUseCase;

    public GetUserAccountSummaryHandler(GetUserAccountSummaryUseCase getUserAccountSummaryUseCase) {
        this.getUserAccountSummaryUseCase = getUserAccountSummaryUseCase;
    }

    public Mono<UserAccountSummaryDTO> getUserAccountSummary(Integer userId) {
        return getUserAccountSummaryUseCase.getUserAccountSummary(userId);
    }
}