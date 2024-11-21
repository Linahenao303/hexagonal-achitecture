package co.example.adapters;

import co.example.entities.AccountEntity;
import co.example.repositories.IAccountRepository;
import com.example.dtos.account.CreateAccountDTO;
import com.example.entities.Account;
import com.example.ports.IAccountRepositoryPort;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AccountRepositoryAdapter implements IAccountRepositoryPort {

    private final IAccountRepository accountRepository;

    public AccountRepositoryAdapter(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Mono<CreateAccountDTO> save(Account account) {
        return accountRepository.save(AccountEntity.builder()
                        .number(account.getNumber())
                        .type(account.getType())
                        .userId(account.getUserId())
                        .build())
                .map(accountEntity -> (CreateAccountDTO) CreateAccountDTO.builder()
                        .id(accountEntity.getId())
                        .number(accountEntity.getNumber())
                        .type(accountEntity.getType())
                        .userId(accountEntity.getUserId())
                        .build());
    }

    @Override
    public Mono<CreateAccountDTO> findById(Integer id) {
        return Mono.just(new CreateAccountDTO());
    }

    @Override
    public Flux<CreateAccountDTO> findByUserId(Integer userId) {
        return accountRepository.findByUserId(userId)
                .map(accountEntity -> (CreateAccountDTO) CreateAccountDTO.builder()
                        .id(accountEntity.getId())
                        .number(accountEntity.getNumber())
                        .type(accountEntity.getType())
                        .userId(accountEntity.getUserId())
                        .build());
    }

}
