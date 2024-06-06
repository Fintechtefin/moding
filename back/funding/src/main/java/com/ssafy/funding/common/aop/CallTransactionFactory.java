package com.ssafy.funding.common.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallTransactionFactory {
    private final RedissonCallSameTransaction redissonCallSameTransaction;
    private final RedissonCallNewTransaction redissonCallNewTransaction;

    public CallTransaction getCallTransaction(boolean needSame) {
        if (needSame) {
            return redissonCallSameTransaction;
        }
        return redissonCallNewTransaction;
    }
}
