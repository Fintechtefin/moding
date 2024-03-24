package com.ssafy.payment.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface PaymentEventStream {
    //    String INPUT = "createPaymentEvent";
    //
    //    @Input(INPUT)
    //    MessageChannel createPaymentEvent();

    String OUTPUT = "createPaymentEvent";

    @Input(OUTPUT)
    MessageChannel createPaymentEvent();
}
