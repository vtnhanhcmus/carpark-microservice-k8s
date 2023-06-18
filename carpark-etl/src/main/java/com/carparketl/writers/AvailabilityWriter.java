package com.carparketl.writers;

import com.carparketl.entities.Availability;
import com.carparketl.repositories.AvailabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AvailabilityWriter implements ItemWriter<Availability> {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    public void write(List<? extends Availability> list){
        log.info("processing write availability info");
        availabilityRepository.saveAll(list.stream().filter(i -> i != null).filter(distinctByKey(Availability::getCarPark)).collect(Collectors.toList()));
    }

    public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
