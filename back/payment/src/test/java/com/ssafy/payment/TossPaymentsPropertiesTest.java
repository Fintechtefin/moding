package com.ssafy.payment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ssafy.payment.properties.TossPaymentsProperties;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

// @SpringBootTest
@ActiveProfiles({"payment-local"})
public class TossPaymentsPropertiesTest {
    @Autowired TossPaymentsProperties tossPaymentsProperties;

    @Test
    @Disabled
    void 토스페이먼츠_환경변수_값_확인() {
        String secretKey = tossPaymentsProperties.getSecretKey();
        String mid = tossPaymentsProperties.getMid();
        assertNotNull(secretKey);
        assertNotNull(mid);
    }
}
