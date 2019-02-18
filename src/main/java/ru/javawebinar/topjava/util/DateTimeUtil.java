package ru.javawebinar.topjava.util;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetween(T lt, T start, T end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate getLocalDate(String localDate) {
        if (localDate != null && !localDate.equals("")) {
            return LocalDate.parse(localDate);
        }
        return null;
    }

    public static LocalTime getLocalTime(String localTime) {
        if (localTime != null && !localTime.equals("")) {
            return LocalTime.parse(localTime);
        }
        return null;
    }
}
