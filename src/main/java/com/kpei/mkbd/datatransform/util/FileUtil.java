package com.kpei.mkbd.datatransform.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtil {
    public static boolean isDirectoryExist(String directoryPath, Boolean autoCreateIfNotExist) {
        Path path = Paths.get(directoryPath);

        if (Files.exists(path)) {
            return true;
        } else {
            if (autoCreateIfNotExist) createDirectory(directoryPath);
            return false;
        }
    }

    public static void createDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getProcessLogFilePath(String logBaseDirectory) {
        createDirectory(logBaseDirectory);
        String pathStr = logBaseDirectory + "/" + getFileName("PROCESS");
        Path path = Paths.get(pathStr);
        try {
            Files.write(path, "".getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pathStr;
    }

    public static String getErrorLogFilePath(String logBaseDirectory) {
        createDirectory(logBaseDirectory);
        String pathStr = logBaseDirectory + "/" + getFileName("ERROR");
        Path path = Paths.get(pathStr);
        try {
            Files.write(path, "".getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pathStr;
    }

    public static String getHashCheckLogFilePath(String logBaseDirectory) {
        createDirectory(logBaseDirectory);
        String pathStr = logBaseDirectory + "/" + getFileName("HASH_CHECK");
        Path path = Paths.get(pathStr);
        try {
            Files.write(path, "".getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pathStr;
    }

    public static void appendContentToFIle(String filePath, String content) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath, true);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFileName(String type) {
        String filename = "GENERAL-" + DateUtil.getTodayDate("yyyyMMdd") + ".log";;
        if (type.equalsIgnoreCase("ERROR")) {
            filename = "ErrorFileLog-" + DateUtil.getTodayDate("yyyyMMdd") + ".log";
        } else if (type.equalsIgnoreCase("PROCESS")) {
            filename = "ProcessFileLog-" + DateUtil.getTodayDate("yyyyMMdd") + ".log";
        } else if (type.equalsIgnoreCase("HASH_CHECK")) {
            filename = "HashCheckLog-" + DateUtil.getTodayDate("yyyyMMdd") + ".log";
        }

        return filename;
    }
}
