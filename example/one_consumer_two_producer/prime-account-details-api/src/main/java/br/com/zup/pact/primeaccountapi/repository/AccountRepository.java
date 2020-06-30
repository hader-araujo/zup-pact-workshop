package br.com.zup.pact.primeaccountapi.repository;

import br.com.zup.pact.primeaccountapi.dto.AccountDetailsDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountRepository {

    public Optional<AccountDetailsDTO> getPrimeAccountDetailsByClientId(Integer clientId) {
        Random random = new Random();

        if (clientId == null) {
            return Optional.empty();
        }

        if (clientId % 2 == 0) {
            return Optional.of(AccountDetailsDTO.builder()
                    .isPrime(true)
                    .accountId(random.nextInt())
                    .discountPercentageFee(random.nextInt(25) + 1).build()
            );
        }

        return Optional.of(AccountDetailsDTO.builder()
                .isPrime(false)
                .accountId(random.nextInt())
                .discountPercentageFee(0).build()
        );
    }
}
