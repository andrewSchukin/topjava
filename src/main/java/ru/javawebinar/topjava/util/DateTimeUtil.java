package ru.javawebinar.topjava.util;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T> boolean isBetween(T lt, T start, T end) {
        if (lt instanceof LocalDate && start instanceof LocalDate && end instanceof LocalDate) {
            LocalDate ltWithType = (LocalDate) lt;
            return ltWithType.compareTo((LocalDate) start) >= 0 && ltWithType.compareTo((LocalDate) end) <= 0;
        }
        if (lt instanceof LocalTime && start instanceof LocalTime && end instanceof LocalTime) {
            LocalTime ltWithType = (LocalTime) lt;
            return ltWithType.compareTo((LocalTime) start) >= 0 && ltWithType.compareTo((LocalTime) end) <= 0;
        }
        return false;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate getLocalDate (String localDate) {
        if (localDate != null && !localDate.equals("")) {
            return LocalDate.parse(localDate);
        }
        return null;
    }

    public static LocalTime getLocalTime (String localTime) {
        if (localTime != null && !localTime.equals("")) {
            return LocalTime.parse(localTime);
        }
        return null;
    }
}
