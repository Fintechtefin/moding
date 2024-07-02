package com.ssafy.payment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"payment-local"})
class PaymentApplicationTests {
    @Test
    void contextLoads() {}
}
