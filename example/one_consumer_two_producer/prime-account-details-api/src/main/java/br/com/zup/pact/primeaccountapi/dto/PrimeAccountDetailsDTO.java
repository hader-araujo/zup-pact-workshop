package br.com.zup.pact.primeaccountapi.dto;

import br.com.zup.pact.primeaccountapi.entity.PrimeAccount;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class PrimeAccountDetailsDTO {

    private Integer accountId;
    private Boolean isPrime;
    private Integer discountPercentageFee;


    public static PrimeAccount fromDtoToEntity(PrimeAccountDetailsDTO accountDetailsDTO) {
        if (Objects.nonNull(accountDetailsDTO)){
            return PrimeAccount.builder()
                    .isPrime(accountDetailsDTO.getIsPrime())
                    .discountPercentageFee(accountDetailsDTO.getDiscountPercentageFee())
                    .build();

        }
        return null;
    }
}
