package com.carparketl;

import com.carparketl.repositories.CarParkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

@SpringBootApplication
@EnableBatchProcessing
@Slf4j
public class CarParkEtlApplication implements CommandLineRunner {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job jobImportCarParkInfo;

	@Autowired
	CarParkRepository carParkRepository;

	public static void main(String[] args) {
		SpringApplication.run(CarParkEtlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Long count = carParkRepository.count();
		if (count == 0){
			JobExecution execution = jobLauncher.run(
					jobImportCarParkInfo,
					new JobParametersBuilder().addDate("start_time", new Date())
							.addString("name job", jobImportCarParkInfo.getName())
							.toJobParameters()
			);
			log.info("Exit status: {}", execution.getStatus());
		}
	}
}
