package com.ssafy.payment;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ssafy.payment.controller.PaymentsCreateClient;
import com.ssafy.payment.dto.request.CreatePaymentsRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;

@ContextConfiguration
@ActiveProfiles({"payment-local"})
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@TestPropertySource(
        properties = {
            "feign.toss.url=http://localhost:${wiremock.server.port}",
            // 타임리프때문에 테스트 깨져서 넣음
            "spring.thymeleaf.enabled=false"
        })
public class TossPaymentsClientTest {

    @Autowired PaymentsCreateClient paymentsCreateClient;

    // @Test
    public void 결제_요청_테스트() throws IOException {

        CreatePaymentsRequest createPaymentsRequest =
                CreatePaymentsRequest.builder()
                        .successUrl("http://localhost:8080/return-url")
                        .failUrl(null)
                        .amount(1000L)
                        .orderId("MC4wODU4ODQwMzg4NDk0")
                        .method("카드")
                        .orderName("토스 티셔츠 외 2건")
                        .build();

        // 만들어둔 json 파일을 불러온다.
        Path file = ResourceUtils.getFile("classpath:payload/payment-response.json").toPath();
        // import static com.github.tomakehurst.wiremock.client.WireMock.*
        stubFor(
                // localhost:${wiremock.server.port}/v1/settlements 요청은 willReturn을 한다.
                post(urlPathEqualTo("/v1/payments"))
                        .willReturn(
                                aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader(
                                                "Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        // 바디 지정
                                        .withBody(Files.readAllBytes(file))));
        LocalDate now = LocalDate.now();
        // 실제 요청이 List<SettlementResponse>에 담겨서온다
        PaymentsResponse paymentsResponse = paymentsCreateClient.execute(createPaymentsRequest);

        // 파싱이 제대로 되었는지 확인.. 다 확인은 안하고 디버거로 적당히 했다.!
        assertAll(
                "payment",
                () ->
                        assertEquals(
                                paymentsResponse.getTotalAmount(),
                                createPaymentsRequest.getAmount()));
    }
}
