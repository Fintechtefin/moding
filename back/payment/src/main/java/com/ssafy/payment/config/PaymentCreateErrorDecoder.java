package com.ssafy.payment.config;

import com.ssafy.common.exception.DynamicException;
import com.ssafy.common.exception.ErrorReason;
import com.ssafy.payment.exception.PaymentsCreateErrorCode;
import com.ssafy.payment.exception.PaymentsUnHandleException;
import com.ssafy.payment.exception.TossPaymentsErrorDto;
import feign.Response;
import feign.codec.ErrorDecoder;

public class PaymentCreateErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        TossPaymentsErrorDto body = TossPaymentsErrorDto.from(response);
        try {
            PaymentsCreateErrorCode paymentsCreateErrorCode =
                    PaymentsCreateErrorCode.valueOf(body.getCode());
            ErrorReason errorReason = paymentsCreateErrorCode.getErrorReason();
            throw new DynamicException(errorReason.getCode(), errorReason.getReason());
        } catch (IllegalArgumentException e) {
            throw PaymentsUnHandleException.EXCEPTION;
        }
    }
}
