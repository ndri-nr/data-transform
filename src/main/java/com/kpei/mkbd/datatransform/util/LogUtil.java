package com.kpei.mkbd.datatransform.util;

import com.kpei.mkbd.datatransform.dto.LogDto;

public class LogUtil {
    private String processLogPath;
    private String errorLogPath;
    private String hashCheckLogPath;

    public LogUtil(String baseDirectoryLog) {
        this.processLogPath = FileUtil.getProcessLogFilePath(baseDirectoryLog);
        this.errorLogPath = FileUtil.getErrorLogFilePath(baseDirectoryLog);
        this.hashCheckLogPath = FileUtil.getHashCheckLogFilePath(baseDirectoryLog);
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

    public void hashCheck(String filename, String message) {
        FileUtil.appendContentToFIle(this.errorLogPath,
                LogDto.builder()
                        .filename(filename)
                        .message(message)
                        .build().hashCheckLogToString());
    }

    public void hashCheck(String message) {
        FileUtil.appendContentToFIle(this.errorLogPath,
                LogDto.builder()
                        .message(message)
                        .build().hashCheckLogToString2());
    }
}
