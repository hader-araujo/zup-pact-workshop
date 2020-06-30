package br.com.zup.pact.primeaccountapi.service;

import br.com.zup.pact.primeaccountapi.dto.AccountDetailsDTO;

import java.util.Optional;

public interface PrimeAccountService {
    Optional<AccountDetailsDTO> getPrimeAccountDetailsByClientId(Integer clientId);
}
