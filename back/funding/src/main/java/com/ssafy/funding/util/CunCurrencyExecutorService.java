package com.ssafy.funding.util;

import lombok.extern.slf4j.Slf4j;
// import org.junit.jupiter.api.function.Executable;

@Slf4j
public class CunCurrencyExecutorService {
    static int numberOfThreads = 10;
    static int numberOfThreadPool = 5;

    //    public static void execute(Executable executable, AtomicLong successCount)
    //            throws InterruptedException {
    //        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreadPool);
    //        CountDownLatch latch = new CountDownLatch(numberOfThreads);
    //
    //        for (long i = 1; i <= numberOfThreads; i++) {
    //            executorService.submit(
    //                    () -> {
    //                        try {
    //                            executable.execute();
    //                            // 오류없이 성공을 하면 성공횟수를 증가시킵니다.
    //                            successCount.getAndIncrement();
    //                        } catch (Throwable e) {
    //                            // 에러뜨면 여기서 확인해보셔요!
    //                            log.info(e.getClass().getName());
    //                        } finally {
    //                            latch.countDown();
    //                        }
    //                    });
    //        }
    //        latch.await();
    //    }
}
