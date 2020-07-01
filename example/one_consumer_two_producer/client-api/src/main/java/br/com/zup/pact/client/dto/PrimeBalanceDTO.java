package br.com.zup.pact.client.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PrimeBalanceDTO implements Serializable {

    private Integer clientId;
    private Integer accountId;
    private BigDecimal balance;
    private Boolean isPrime;
    private Integer discountPercentageFee;

    public static PrimeBalanceDTO fromBalanceDTO(
            BalanceDTO balanceDTO,
            Boolean isPrime,
            Integer discountPercentageFee
    ) {
        PrimeBalanceDTO primeBalanceDTO = PrimeBalanceDTO.fromBalanceDTO(balanceDTO);

        primeBalanceDTO.setIsPrime(isPrime);
        primeBalanceDTO.setDiscountPercentageFee(discountPercentageFee);

        return primeBalanceDTO;
    }

    public static PrimeBalanceDTO fromBalanceDTO(BalanceDTO balanceDTO ) {
        PrimeBalanceDTO primeBalanceDTO = PrimeBalanceDTO.builder()
                .clientId(balanceDTO.getClientId())
                .accountId(balanceDTO.getAccountId())
                .balance(balanceDTO.getBalance())
                .build();

        return primeBalanceDTO;
    }

}