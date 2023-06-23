package com.carparketl.config;

import com.carparketl.dtos.CarParkDto;
import com.carparketl.entities.CarPark;
import com.carparketl.listeners.CarParkListener;
import com.carparketl.processers.CarParkProcessor;
import com.carparketl.readers.CarParkReader;
import com.carparketl.writers.CarParkInfoWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@Slf4j
public class CarParkBatch {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    CarParkReader carParkReader;

    @Autowired
    CarParkInfoWriter carParkInfoWriter;

    @Autowired
    CarParkProcessor carParkProcessor;

    @Autowired
    CarParkListener carParkListener;


    @Bean(name = "jobImportCarParkInfo")
    public Job job(JobRepository jobRepository, Step stepProcessCarParkInfo){

        return new JobBuilder("jobImportCarParkInfo", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(carParkListener)
                .flow(stepProcessCarParkInfo).end().build();
    }

    @Bean("stepProcessCarParkInfo")
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager){

        return new StepBuilder("stepProcessCarParkInfo", jobRepository)
                .<CarParkDto, CarPark> chunk(100, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean(name = "readerCarPark")
    public ItemReader<CarParkDto> reader(){
        return carParkReader.reader();
    }

    @Bean(name = "processorCarPark")
    public ItemProcessor<CarParkDto, CarPark> processor(){
        final CompositeItemProcessor<CarParkDto, CarPark> processors = new CompositeItemProcessor<>();
        processors.setDelegates(Arrays.asList(carParkProcessor));
        return processors;
    }

    @Bean(name = "writerCarPark")
    public ItemWriter<CarPark> writer() {
        return carParkInfoWriter;
    }

}
