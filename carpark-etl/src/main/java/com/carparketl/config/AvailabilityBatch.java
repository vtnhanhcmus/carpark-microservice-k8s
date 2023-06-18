package com.carparketl.config;

import com.carparketl.dtos.AvailabilityDto;
import com.carparketl.dtos.CarParkDto;
import com.carparketl.entities.Availability;
import com.carparketl.entities.CarPark;
import com.carparketl.processers.AvailabilityProcessor;

import com.carparketl.readers.AvailabilityReader;
import com.carparketl.writers.AvailabilityWriter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;

import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Slf4j
public class AvailabilityBatch {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

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

    @Bean
    public Job jobGetAvailabilityInfo(){
        return jobBuilderFactory.get("getAvailabilityInfo").start(stepAvailabilityInfo()).build();
    }

    @Bean
    public Step stepAvailabilityInfo(){
        return stepBuilderFactory.get("stepAvailabilityInfo")
                .<AvailabilityDto, Availability>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
