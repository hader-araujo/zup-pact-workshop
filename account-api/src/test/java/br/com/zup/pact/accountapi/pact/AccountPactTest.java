package br.com.zup.pact.accountapi.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.core.model.RequestResponsePact;
import br.com.zup.pact.accountapi.dto.BalanceDTO;
import com.google.gson.Gson;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "AccountBalanceProvider", port = "1234")
public class AccountPactTest {

    private static final String BALANCE_URL_WORKING = "/v1/accounts/balance/1";
    private static final String BALANCE_URL_NOT_WORKING = "/v1/accounts/balance/1000";
    private Map<String, String> headers = MapUtils.putAll(new HashMap<>(), new String[] {
            "Content-Type", "application/json"
    });

    @Pact(provider = "AccountBalanceProvider", consumer = "AccountBalanceConsumer")
    public RequestResponsePact balanceEndpointTest(PactDslWithProvider builder) {

        PactDslJsonBody bodyResponse = new PactDslJsonBody()
                .integerType("accountId", 1)
                .integerType("clientId", 1)
                .decimalType("balance", 100.00);

        return builder
                .given("get balance by account id")
                .uponReceiving("A request to " +BALANCE_URL_WORKING)
                .path(BALANCE_URL_WORKING)
                .method("GET")
                .willRespondWith()
                .headers(headers)
                .status(200)
                .body(bodyResponse)
                .toPact();
    }

    @Pact(provider = "AccountBalanceProvider", consumer = "AccountBalanceConsumerNotWorking")
    public RequestResponsePact balanceEndpointNotWorkingTest(PactDslWithProvider builder) {
        return builder
                .given("No accounts exist")
                .uponReceiving("retrieving balance data")
                .path(BALANCE_URL_NOT_WORKING)
                .method("GET")
                .willRespondWith()
                .headers(headers)
                .status(404)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "balanceEndpointTest", providerName = "AccountBalanceProvider")
    void testBalanceWorking(MockServer mockServer) throws IOException {
        HttpResponse httpResponse = Request.Get(mockServer.getUrl() + BALANCE_URL_WORKING).execute().returnResponse();
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(200)));
        assertThat(IOUtils.toString(httpResponse.getEntity().getContent()),
                is(equalTo("{\"accountId\":1,\"clientId\":1,\"balance\":100}")));
    }

    @Test
    @PactTestFor(pactMethod = "balanceEndpointNotWorkingTest", providerName = "AccountBalanceProvider")
    void testBalanceNotWorking(MockServer mockServer) throws IOException {
        HttpResponse httpResponse = Request.Get(mockServer.getUrl() + BALANCE_URL_NOT_WORKING).execute().returnResponse();
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(404)));
        assertThat(IOUtils.toString(httpResponse.getEntity().getContent()), is(equalTo("")));
    }

}