package com.example.handlers.transaction;

import com.example.dtos.transaction.TransferDTO;
import com.example.logic.transaction.TransferUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TransferHandler {

    private final TransferUseCase transferUseCase;

    @Autowired
    public TransferHandler(TransferUseCase transferUseCase) {
        this.transferUseCase = transferUseCase;
    }

    public Mono<Void> handleTransfer(TransferDTO transferDTO) {
        return transferUseCase.transfer(transferDTO);
    }
}
