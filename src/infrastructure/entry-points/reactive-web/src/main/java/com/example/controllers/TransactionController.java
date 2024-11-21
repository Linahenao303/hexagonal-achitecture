package com.example.controllers;

import com.example.dtos.transaction.TransactionDTO;
import com.example.dtos.transaction.TransferDTO;
import com.example.handlers.transaction.CreateTransactionHandler;
import com.example.handlers.transaction.TransferHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("api/transaction")
@RestController
public class TransactionController {

    private final CreateTransactionHandler createTransactionHandler;
    private final TransferHandler transferHandler;

    @PostMapping("/create")
    public Mono<ResponseEntity<TransactionDTO>> createTransaction(@Valid @RequestBody TransactionDTO transaction) {
        return createTransactionHandler.execute(transaction).map(ResponseEntity::ok);
    }

    @PostMapping("/transfer")
    public Mono<ResponseEntity<Void>> transfer(@Valid @RequestBody TransferDTO transferDTO) {
        return transferHandler.handleTransfer(transferDTO)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .onErrorResume(e -> Mono.just(new ResponseEntity<Void>(HttpStatus.BAD_REQUEST)));
    }
}