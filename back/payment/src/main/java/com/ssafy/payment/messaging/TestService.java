package com.ssafy.payment.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.common.dto.request.CreatePaymentsRequest;
import com.ssafy.payment.controller.PaymentsCreateClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
    private final PaymentsCreateClient paymentsCreateClient;

    public void callPaymentCreateClient(String createPaymentsRequest)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CreatePaymentsRequest createPaymentsRequest1 =
                objectMapper.readValue(
                        createPaymentsRequest, new TypeReference<CreatePaymentsRequest>() {});
        paymentsCreateClient.execute(new CreatePaymentsRequest());
    }
}
