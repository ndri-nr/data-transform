package com.kpei.mkbd.datatransform;

import com.kpei.mkbd.datatransform.dto.*;
import com.kpei.mkbd.datatransform.service.ProcessService;
import com.kpei.mkbd.datatransform.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App 
{
    public static void main( String[] args )
    {
        File file = new File("/Users/andri/Documents/solecode/datatransform/A0240620.mkb");
        File mappingKey = new File("/Users/andri/Documents/solecode/datatransform/mapping-key-list.txt");
        String baseDirectoryLog = "/Users/andri/Documents/solecode/datatransform/documents/logs";
        LogUtil logUtil = new LogUtil(baseDirectoryLog);
        Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("logger already set");

        Map<String, String> key = generateMappingKey(mappingKey);

        MkbTransformDto mkbTransformDto = new MkbTransformDto();
        mkbTransformDto.setUserId("fb68b928-f8f9-46f0-8cd1-2081f27483e7");
        mkbTransformDto.setUsername("admin");
        mkbTransformDto.setFilename("A0240620");
        mkbTransformDto.setKodePe("A0");
        mkbTransformDto.constructVd5Dto(file, key);

        String url = "jdbc:postgresql://pgsql15-dev.solecode.tech:5432/kpei_mkbd";
        String username = "kpei_mkbd";
        String password = "a3BlaV9ta2JkXzI4MDIq";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ExecutorService service = Executors.newSingleThreadExecutor();
        Connection finalConn = conn;
        service.submit(new Runnable() {
            public void run() {
                try {
                    LocalDateTime start = LocalDateTime.now();
                    logger.info("Background service started at " + LocalDateTime.now());
                    Integer status = ProcessService.processDataVD(finalConn, mkbTransformDto, logUtil, logger);
                    ProcessService.processSaveLog(finalConn, mkbTransformDto, logUtil, logger, status);
                    logger.info("Background service finished at " + LocalDateTime.now());
                    LocalDateTime end = LocalDateTime.now();
                    Duration duration = Duration.between(start, end);
                    logger.info("Background service finished in " + duration.getSeconds() + "s");
                } finally {
                    if (finalConn != null) {
                        try {
                            finalConn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    service.shutdown();
                }
            }
        });
    }

    public static Map<String, String> generateMappingKey(File file) {
        Map<String, String> key = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArr = line.split("\\|");
                key.put(lineArr[0], lineArr[1]);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return key;
    }
}
