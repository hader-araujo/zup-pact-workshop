package br.com.zup.pact.primeaccountapi.pact;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import br.com.zup.pact.primeaccountapi.dto.AccountDetailsDTO;
import br.com.zup.pact.primeaccountapi.service.PrimeAccountService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@Provider("PrimeAccountDetailsProvider")
@PactBroker(host = "localhost", port = "80")
@VerificationReports
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeAccountProviderPactTest {

    @LocalServerPort
    private int localServerPort;

    @MockBean
    private PrimeAccountService primeAccountService;

    @BeforeEach
    void setUp(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", localServerPort, "/"));
    }

    @BeforeAll
    static void enablePublishingPact() {
        System.setProperty("pact.verifier.publishResults", "true");
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void testTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("get prime account details of clientId 1")
    public void getPrimeAccountDetailsDTO() {
        final AccountDetailsDTO balanceDTO = AccountDetailsDTO
                .builder()
                .accountId(1)
                .isPrime(true)
                .discountPercentageFee(5)
                .build();
        given(primeAccountService.getPrimeAccountDetailsByClientId(eq(1))).willReturn(Optional.of(balanceDTO));

    }

    @State("No accounts exist from accountId 1000")
    public void getPrimeAccountDetailsNotWorking() {
        given(primeAccountService.getPrimeAccountDetailsByClientId(eq(1000))).willReturn(Optional.empty());
    }
}
