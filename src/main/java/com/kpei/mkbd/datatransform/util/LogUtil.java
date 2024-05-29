package com.kpei.mkbd.datatransform.util;

import com.kpei.mkbd.datatransform.dto.LogDto;

public class LogUtil {
    private String processLogPath;
    private String errorLogPath;

    public LogUtil(String baseDirectoryLog) {
        this.processLogPath = FileUtil.getProcessLogFilePath(baseDirectoryLog);
        this.errorLogPath = FileUtil.getErrorLogFilePath(baseDirectoryLog);
    }

    public void process(String username, String filename, String functionName) {
        FileUtil.appendContentToFIle(this.processLogPath,
                LogDto.builder()
                        .username(username)
                        .filename(filename)
                        .functionName(functionName)
                        .build().processLogToString());
    }

    public void error(String username, String filename, String functionName, String message) {
        FileUtil.appendContentToFIle(this.errorLogPath,
                LogDto.builder()
                        .username(username)
                        .filename(filename)
                        .functionName(functionName)
                        .message(message)
                        .build().errorLogToString());
    }
}
