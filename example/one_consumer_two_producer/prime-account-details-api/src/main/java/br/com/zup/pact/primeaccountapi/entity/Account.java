package br.com.zup.pact.primeaccountapi.entity;

import br.com.zup.pact.primeaccountapi.dto.AccountDetailsDTO;
import br.com.zup.pact.primeaccountapi.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    private Integer id;
    private Integer clientId;
    private Boolean isPrime;
    private Integer discountPercentageFee;

    public static AccountDetailsDTO fromEntityToDto(Account account) {
        if (Objects.nonNull(account)){
            return AccountDetailsDTO.builder()
                    .isPrime(account.getIsPrime())
                    .discountPercentageFee(account.getDiscountPercentageFee())
                    .accountId(account.getId())
                    .build();
        }
        return null;
    }
}
