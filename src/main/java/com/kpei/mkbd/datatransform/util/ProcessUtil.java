package com.kpei.mkbd.datatransform.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ProcessUtil {
    private static String getProcessDirectoryPath() {
        String pathStr = System.getProperty("user.home") + "/process/locked";
        Path path = Paths.get(pathStr);

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return pathStr;
    }

    public static void lockProcess(String process) {
        Path path = Paths.get(getProcessDirectoryPath() + "/" + process);
        if (!Files.exists(path)) {
            try {
                Files.write(path, "".getBytes(), StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void unlockProcess(String process) {
        Path path = Paths.get(getProcessDirectoryPath() + "/" + process);
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean isProcessLocked(String process) {
        Path path = Paths.get(getProcessDirectoryPath() + "/" + process);
        return Files.exists(path);
    }
}
