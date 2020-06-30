package br.com.zup.pact.primeaccountapi.dto;

import br.com.zup.pact.primeaccountapi.entity.Account;
import br.com.zup.pact.primeaccountapi.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
public class AccountDetailsDTO {

    private Integer accountId;
    private Boolean isPrime;
    private Integer discountPercentageFee;


    public static Account fromDtoToEntity(AccountDetailsDTO accountDetailsDTO) {
        if (Objects.nonNull(accountDetailsDTO)){
            return Account.builder()
                    .isPrime(accountDetailsDTO.getIsPrime())
                    .discountPercentageFee(accountDetailsDTO.getDiscountPercentageFee())
                    .build();

        }
        return null;
    }
}
