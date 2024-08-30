package com.ssafy.payment;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.ssafy.common.dto.request.CancelPaymentsRequest;
import com.ssafy.payment.controller.PaymentsCancelClient;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"payment-local"})
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = {"feign.toss.url=http://localhost:${wiremock.server.port}"})
public class PaymentsCancelClientTest {
    private static final String IDEMPOTENCY_KEY = "idempotency-key";
    private static final String PAYMENT_KEY = "1234";
    private static final CancelPaymentsRequest REQUEST = new CancelPaymentsRequest("test");
    @Autowired private PaymentsCancelClient paymentsCancelClient;

    // @Test
    public void 주문취소_실패시_멱등성_테스트() {
        final String URL = "/v1/payments/" + PAYMENT_KEY + "/cancel";
        // given
        stubFor(
                post(urlEqualTo(URL))
                        .willReturn(aResponse().withStatus(HttpStatus.SERVICE_UNAVAILABLE)));

        // when
        Throwable exception =
                catchThrowable(
                        () -> paymentsCancelClient.execute(IDEMPOTENCY_KEY, PAYMENT_KEY, REQUEST));

        // then
        assertThat(exception).isInstanceOf(RetryableException.class);
        verify(3, postRequestedFor(urlEqualTo(URL)));
    }
}
