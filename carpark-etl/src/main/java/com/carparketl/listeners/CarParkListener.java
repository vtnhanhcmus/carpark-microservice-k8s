package com.carparketl.listeners;

import com.carparketl.launchers.AvailabilityLauncher;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
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
    private AvailabilityLauncher availabilityLauncher;


    @SneakyThrows
    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("launcher job availability");
        availabilityLauncher.launcher();
    }

}
