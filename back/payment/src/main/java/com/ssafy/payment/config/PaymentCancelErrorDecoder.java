package com.ssafy.payment.config;

import com.ssafy.common.exception.DynamicException;
import com.ssafy.common.exception.ErrorReason;
import com.ssafy.payment.exception.CustomExceptionStatus;
import com.ssafy.payment.exception.PaymentsUnHandleException;
import com.ssafy.payment.exception.TossPaymentsErrorDto;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

public class PaymentCancelErrorDecoder implements ErrorDecoder {
    private static final long PERIOD = 500L;
    private static final long MAX_PERIOD = TimeUnit.SECONDS.toMillis(3L);
    private static final int MAX_ATTEMPTS = 3;

    @Bean
    Retryer.Default retryer() {
        return new Retryer.Default(PERIOD, MAX_PERIOD, MAX_ATTEMPTS);
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            FeignException exception = feign.FeignException.errorStatus(methodKey, response);
            int status = response.status();
            // 500번대는 기본적으로 리트라이
            if (HttpStatus.valueOf(status).is5xxServerError()) {
                throw new RetryableException(
                        status,
                        exception.getMessage(),
                        response.request().httpMethod(),
                        exception,
                        null,
                        response.request());
            }
            // payments 에러 코드 Decode
            TossPaymentsErrorDto body = TossPaymentsErrorDto.from(response);

            final CustomExceptionStatus paymentsCancelErrorCode =
                    CustomExceptionStatus.valueOf(body.getCode());
            final ErrorReason errorReason = paymentsCancelErrorCode.getErrorReason();

            throw new DynamicException(errorReason.getCode(), errorReason.getReason());
        } catch (IllegalArgumentException e) {
            throw PaymentsUnHandleException.EXCEPTION;
        }
    }
}
