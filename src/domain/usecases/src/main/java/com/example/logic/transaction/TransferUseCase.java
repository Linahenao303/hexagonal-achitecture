package com.example.logic.transaction;

import com.example.dtos.transaction.CreateTransactionDTO;
import com.example.dtos.transaction.TransferDTO;
import com.example.entities.Transaction;
import com.example.entities.constant.TransactionType;
import com.example.logic.account.GetAccountBalanceUseCase;
import com.example.ports.IMessageBrokerPort;
import com.example.ports.ITransactionRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TransferUseCase  {

    private final ITransactionRepositoryPort transactionRepository;
    private final IMessageBrokerPort messageBroker;
    private final GetAccountBalanceUseCase getAccountBalanceUseCase;

    public TransferUseCase(ITransactionRepositoryPort transactionRepository,
                           IMessageBrokerPort messageBroker, GetAccountBalanceUseCase getAccountBalanceUseCase) {

        this.transactionRepository = transactionRepository;
        this.messageBroker = messageBroker;
        this.getAccountBalanceUseCase = getAccountBalanceUseCase;
    }

    public Mono<Void> transfer(TransferDTO transferDTO) {
        return getAccountBalanceUseCase.apply(transferDTO.getSourceAccountId())
                .flatMap(accountBalanceDTO -> {
                    if (accountBalanceDTO.getBalance() >= transferDTO.getAmount()) {
                        return transactionRepository.save(
                                new Transaction.Builder()
                                        .type(TransactionType.fromString("WITHDRAW").toString())
                                        .amount(transferDTO.getAmount())
                                        .accountId(transferDTO.getSourceAccountId())
                                        .build()
                        ).then(Mono.defer(() -> {
                            CreateTransactionDTO depositTransaction = CreateTransactionDTO.builder()
                                    .amount(transferDTO.getAmount())
                                    .accountId(transferDTO.getDestinationAccountId())
                                    .build();
                            messageBroker.send(depositTransaction);
                            Transaction transaction = new Transaction.Builder()
                                    .type(TransactionType.valueOf("DEPOSIT").toString())
                                    .amount(depositTransaction.getAmount())
                                    .accountId(depositTransaction.getAccountId())
                                    .build();
                            return transactionRepository.save(transaction).then();
                        }));
                    } else {
                        return Mono.error(new RuntimeException("Insufficient funds"));
                    }
                });
    }

}