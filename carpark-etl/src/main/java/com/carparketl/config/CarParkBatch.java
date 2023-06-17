package com.carparketl.config;

import com.carparketl.dtos.CarParkDto;
import com.carparketl.entities.CarPark;
import com.carparketl.processers.CarParkProcessor;
import com.carparketl.readers.CarParkReader;
import com.carparketl.writers.CarParkInfoWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@Slf4j
public class CarParkBatch {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Autowired
    CarParkReader carParkReader;

    @Autowired
    CarParkInfoWriter carParkInfoWriter;

    @Autowired
    CarParkProcessor carParkProcessor;


    @Bean
    public Job jobImportCarParkInfo(){
        return jobBuilderFactory.get("importCsvCarParkInfo").start(step()).build();
    }

    @Bean
    public Step step(){
        return stepBuilderFactory.get("stepProcessCarParkInfo")
                .<CarParkDto, CarPark>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemReader<CarParkDto> reader(){
        return carParkReader.reader();
    }

    @Bean
    public ItemProcessor<CarParkDto, CarPark> processor(){
        final CompositeItemProcessor<CarParkDto, CarPark> processors = new CompositeItemProcessor<>();
        processors.setDelegates(Arrays.asList(carParkProcessor));
        return processors;
    }

    @Bean
    public ItemWriter<CarPark> writer() {
        return carParkInfoWriter;
    }

}
