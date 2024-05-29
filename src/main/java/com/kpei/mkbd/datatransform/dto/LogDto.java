package com.kpei.mkbd.datatransform.dto;

import com.kpei.mkbd.datatransform.util.DateUtil;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogDto {
    private LocalDateTime time = LocalDateTime.now();
    private String username;
    private String filename;
    private String message;
    private String functionName;

    public String processLogToString() {
        return DateUtil.getTodayDateTime("yyyy-MM-dd HH:mm:ss")
                + "|"
                + username
                + "|"
                + filename
                + "|"
                + functionName + "\n";
    }

    public String errorLogToString() {
        return DateUtil.getTodayDateTime("yyyy-MM-dd HH:mm:ss")
                + "|"
                + username
                + "|"
                + filename
                + "|"
                + message
                + "|"
                + functionName + "\n";
    }
}
