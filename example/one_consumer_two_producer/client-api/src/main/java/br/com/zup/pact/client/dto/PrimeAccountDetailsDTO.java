package br.com.zup.pact.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrimeAccountDetailsDTO {

    private Integer accountId;
    private Boolean isPrime;
    private Integer discountPercentageFee;

}
