package com.ssafy.payment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ssafy.payment.properties.TossPaymentsProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
public class TossPaymentsPropertiesTest {
    @Autowired TossPaymentsProperties tossPaymentsProperties;

    @Test
    void 토스페이먼츠_환경변수_값_확인() {
        String secretKey = tossPaymentsProperties.getSecretKey();
        String mid = tossPaymentsProperties.getMid();
        assertNotNull(secretKey);
        assertNotNull(mid);
    }
}
