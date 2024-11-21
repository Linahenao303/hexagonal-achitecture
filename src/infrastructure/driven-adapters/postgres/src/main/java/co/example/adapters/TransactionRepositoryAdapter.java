package co.example.adapters;

import co.example.entities.TransactionEntity;
import co.example.repositories.ITransactionRepository;
import com.example.dtos.transaction.CreateTransactionDTO;
import com.example.entities.Transaction;
import com.example.ports.ITransactionRepositoryPort;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TransactionRepositoryAdapter implements ITransactionRepositoryPort {

    private final ITransactionRepository transactionRepository;

    public TransactionRepositoryAdapter(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Mono<CreateTransactionDTO> save(Transaction transaction) {
        return transactionRepository.save(TransactionEntity.builder()
                        .type(transaction.getType())
                        .amount(transaction.getAmount())
                        .accountId(transaction.getAccountId())
                        .build())
                .map(transactionEntity -> (CreateTransactionDTO) CreateTransactionDTO.builder()
                        .id(transactionEntity.getId())
                        .amount(transactionEntity.getAmount())
                        .accountId(transactionEntity.getAccountId())
                        .build());
    }

    @Override
    public Mono<CreateTransactionDTO> findById(Integer id) {
        return Mono.just(new CreateTransactionDTO());
    }

    @Override
    public Flux<CreateTransactionDTO> findByAccountId(Integer accountId) {
        return transactionRepository.findByAccountId(accountId)
                .map(transaction -> CreateTransactionDTO.builder()
                        .id(transaction.getId())
                        .amount(transaction.getAmount())
                        .accountId(transaction.getAccountId())
                        .build());
    }
}