package com.ramonmoncao.tasks_api.utils;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DataUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneOffset.UTC);

    public static String getFormatterDateTimeNow(){
        return DATE_TIME_FORMATTER.format(Instant.now());
    }
}
