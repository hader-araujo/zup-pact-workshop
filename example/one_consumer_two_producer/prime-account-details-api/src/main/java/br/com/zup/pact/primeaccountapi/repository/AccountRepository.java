package br.com.zup.pact.primeaccountapi.repository;

import br.com.zup.pact.primeaccountapi.dto.PrimeAccountDetailsDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountRepository {

    public Optional<PrimeAccountDetailsDTO> getPrimeAccountDetailsByClientId(Integer clientId) {
        Random random = new Random();

        if (clientId == null) {
            return Optional.empty();
        }

        if (clientId % 2 == 0) {
            return Optional.of(PrimeAccountDetailsDTO.builder()
                    .isPrime(true)
                    .accountId(random.nextInt())
                    .discountPercentageFee(random.nextInt(25) + 1).build()
            );
        }

        return Optional.of(PrimeAccountDetailsDTO.builder()
                .isPrime(false)
                .accountId(random.nextInt())
                .discountPercentageFee(0).build()
        );
    }
}
