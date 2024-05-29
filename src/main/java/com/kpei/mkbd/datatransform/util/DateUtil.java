package com.kpei.mkbd.datatransform.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getTodayDate(String format) {
        if (format == null || format.trim().isEmpty()) {
            format = "yyyyMMdd";
        }

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public static String getTodayDateTime(String format) {
        if (format == null || format.trim().isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return now.format(formatter);
    }
}
