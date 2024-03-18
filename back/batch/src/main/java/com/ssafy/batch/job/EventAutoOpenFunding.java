package com.ssafy.batch.job;

import com.ssafy.batch.parameter.DateTimeJobParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class EventAutoOpenFunding {

    private static final String JOB_NAME = "_펀딩생성";
    private static final String BEAN_PREFIX = JOB_NAME + "_";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    //private final EventService eventService;

    @Bean(BEAN_PREFIX + "dateTimeJobParameter")
    @JobScope
    public DateTimeJobParameter dateTimeJobParameter() {
        return new DateTimeJobParameter();
    }

    @Bean(JOB_NAME)
    public Job eventCreateJob() {
        return jobBuilderFactory
                .get(JOB_NAME)
                .preventRestart()
                .start(eventCreationStep())
                .build();
    }

    @Bean(BEAN_PREFIX + "step")
    @JobScope
    public Step eventCreationStep() {
        return stepBuilderFactory
                .get(BEAN_PREFIX + "step")
                .tasklet(
                        (contribution, chunkContext) -> {
                            log.info(">>>>> 펀딩 자동 생성 작업 실행");
                            LocalDateTime time = dateTimeJobParameter().getTime();
                            //List<Event> events = eventService.closeExpiredEventsEndAtBefore(time);
                            // MattaMost와 연동하기
                            return RepeatStatus.FINISHED;
                        })
                .build();
    }
}
