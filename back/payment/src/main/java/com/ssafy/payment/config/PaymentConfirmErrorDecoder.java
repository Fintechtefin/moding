package com.ssafy.payment.config;

import com.ssafy.payment.dto.ErrorReason;
import com.ssafy.payment.exception.DynamicException;
import com.ssafy.payment.exception.PaymentsConfirmErrorCode;
import com.ssafy.payment.exception.PaymentsUnHandleException;
import com.ssafy.payment.exception.TossPaymentsErrorDto;
import feign.Response;
import feign.codec.ErrorDecoder;

public class PaymentConfirmErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        TossPaymentsErrorDto body = TossPaymentsErrorDto.from(response);
        try {
            PaymentsConfirmErrorCode paymentsConfirmErrorCode =
                    PaymentsConfirmErrorCode.valueOf(body.getCode());
            ErrorReason errorReason = paymentsConfirmErrorCode.getErrorReason();
            throw new DynamicException(errorReason.getCode(), errorReason.getReason());
        } catch (IllegalArgumentException e) {
            throw PaymentsUnHandleException.EXCEPTION;
        }
    }
}
