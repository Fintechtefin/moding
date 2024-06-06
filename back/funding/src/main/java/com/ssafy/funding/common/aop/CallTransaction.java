package com.ssafy.funding.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public interface CallTransaction {
    Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable;
}
