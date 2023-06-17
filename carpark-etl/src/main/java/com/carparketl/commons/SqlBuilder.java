package com.carparketl.commons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class SqlBuilder {

    public static final String INSERT_CAR_PARK = "insert.car.park";
    public static final String UPSERT_CAR_PARK_AVAILABILITY = "upsert.car.park.availability";

    private final Map<String, String> query;

    public SqlBuilder() {
        query = new HashMap<>();
    }

    @Value("classpath:car-park-batch-queries.xml")
    Resource carParkBatchQueries;

    @PostConstruct
    private void init() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document queryDocument = db.parse(carParkBatchQueries.getInputStream());
            query.put(INSERT_CAR_PARK, queryDocument.getElementsByTagName(INSERT_CAR_PARK).item(0).getTextContent());
            query.put(UPSERT_CAR_PARK_AVAILABILITY, queryDocument.getElementsByTagName(UPSERT_CAR_PARK_AVAILABILITY).item(0).getTextContent());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.error("Car park batch queries parsing error: {}", e.getMessage(), e);
        }
    }

    public String getCarParkInsertQuery() {
        return query.get(INSERT_CAR_PARK);
    }

    public String getCarParkAvailabilityUpsertQuery() {
        return query.get(UPSERT_CAR_PARK_AVAILABILITY);
    }
}
