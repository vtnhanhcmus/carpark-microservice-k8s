package com.carparketl.launchers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class AvailabilityLauncher {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job jobGetAvailabilityInfo;

    public void launcher() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
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
