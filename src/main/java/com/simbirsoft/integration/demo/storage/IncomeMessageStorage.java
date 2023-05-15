package com.simbirsoft.integration.demo.storage;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class IncomeMessageStorage {

    @Getter
    private final Map<LocalDateTime, Object> messages = new ConcurrentSkipListMap<>(Comparator.reverseOrder());

}
