package com.carparketl.config;

import com.carparketl.dtos.AvailabilityDto;
import com.carparketl.entities.Availability;
import com.carparketl.processers.AvailabilityProcessor;

import com.carparketl.readers.AvailabilityReader;
import com.carparketl.writers.AvailabilityWriter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;

import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;

@Configuration
@Slf4j
public class AvailabilityBatch {
    @Autowired
    AvailabilityReader availabilityReader;

    @Autowired
    AvailabilityWriter availabilityWriter;

    @Autowired
    AvailabilityProcessor availabilityProcessor;


    @Bean(name = "readerAvailability")
    public AvailabilityReader reader(){
        return availabilityReader;
    }
    @Bean(name = "processorAvailability")
    public ItemProcessor<AvailabilityDto, Availability> processor(){
        final CompositeItemProcessor<AvailabilityDto, Availability> processors = new CompositeItemProcessor<>();
        processors.setDelegates(Arrays.asList(availabilityProcessor));
        return processors;
    }

    @Bean(name = "writerAvailability")
    public AvailabilityWriter writer(){
        return availabilityWriter;
    }

    @Bean("jobGetAvailabilityInfo")
    public Job job(JobRepository jobRepository, Step stepAvailabilityInfo){
        return new JobBuilder("getAvailabilityInfo", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(stepAvailabilityInfo).end().build();
    }

    @Bean("stepAvailabilityInfo")
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("stepAvailabilityInfo", jobRepository)
                .<AvailabilityDto, Availability> chunk(100, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
