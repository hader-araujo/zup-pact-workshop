package br.com.zup.pact.primeaccountapi.service.impl;

import br.com.zup.pact.primeaccountapi.dto.AccountDetailsDTO;
import br.com.zup.pact.primeaccountapi.repository.AccountRepository;
import br.com.zup.pact.primeaccountapi.service.PrimeAccountService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PrimeAccountServiceImpl implements PrimeAccountService {

    private final AccountRepository accountRepository;

    @Override
    public Optional<AccountDetailsDTO> getPrimeAccountDetailsByClientId(Integer clientId) {
        return accountRepository.getPrimeAccountDetailsByClientId(clientId);
    }
}