package br.com.zup.pact.client.integration.account.service.impl;

import br.com.zup.pact.client.dto.PrimeAccountDetailsDTO;
import br.com.zup.pact.client.integration.account.service.PrimeAccountDetailsIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PrimeAccountDetailsIntegrationServiceImpl implements PrimeAccountDetailsIntegrationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${integration.prime.account.details.url}")
    private String accountBalanceUrl;

    @Override
    public Optional<PrimeAccountDetailsDTO> getPrimeAccountDetails(Integer accountId) {
        final ResponseEntity<PrimeAccountDetailsDTO> responseEntity = restTemplate
                .getForEntity(accountBalanceUrl, PrimeAccountDetailsDTO.class, accountId);
        return Optional.ofNullable(responseEntity.getBody());
    }
}
