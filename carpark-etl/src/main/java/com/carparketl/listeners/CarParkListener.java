package com.carparketl.listeners;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class CarParkListener implements JobExecutionListener {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job jobGetAvailabilityInfo;


    @Override
    public void beforeJob(JobExecution jobExecution) {
    }

    @SneakyThrows
    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("launcher job availability");
        JobExecution execution = jobLauncher.run(
                jobGetAvailabilityInfo,
                new JobParametersBuilder().addDate("start_time", new Date())
                        .addString("name job", jobGetAvailabilityInfo.getName())
                        .toJobParameters()
        );

        log.info("Exit status: {}", execution.getStatus());
    }
}
