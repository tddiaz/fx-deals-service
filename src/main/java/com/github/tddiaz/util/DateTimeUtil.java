package com.github.tddiaz.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by tristandiaz on 11/2/17.
 */
public final class DateTimeUtil {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String FRIENDLY_DATE_TIME_PATTERN = "MMM/dd/yyyy HH:mm:ss";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);



    private DateTimeUtil() {

    }

    public static String formatDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(FRIENDLY_DATE_TIME_PATTERN));
    }
}
