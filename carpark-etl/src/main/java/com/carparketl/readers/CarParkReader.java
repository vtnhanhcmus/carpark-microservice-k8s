package com.carparketl.readers;

import com.carparketl.dtos.CarParkDto;
import lombok.SneakyThrows;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CarParkReader {

    @Value("classpath:hdb-carpark-information.csv")
    Resource carParkResource;

    public FlatFileItemReader<CarParkDto> reader(){
        FlatFileItemReader<CarParkDto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(carParkResource);
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(getCarParkMapper());
        return flatFileItemReader;
    }

    public DefaultLineMapper<CarParkDto> getCarParkMapper(){
        String header = getHeaderLine();
        return new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(header.split(","));
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(CarParkDto.class);
            }});
        }};
    }

    @SneakyThrows
    public String getHeaderLine(){
        Scanner scanner = new Scanner(carParkResource.getInputStream());
        String line = scanner.nextLine();
        scanner.close();
        return line;
    }

}
