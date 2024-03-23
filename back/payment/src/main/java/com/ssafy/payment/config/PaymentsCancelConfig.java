package com.ssafy.payment.config;

import org.springframework.context.annotation.Import;

@Import({TossHeaderConfig.class, PaymentCancelErrorDecoder.class})
public class PaymentsCancelConfig {}
