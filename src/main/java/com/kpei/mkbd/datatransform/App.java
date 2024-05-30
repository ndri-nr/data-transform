package com.kpei.mkbd.datatransform;

import com.kpei.mkbd.datatransform.dto.*;
import com.kpei.mkbd.datatransform.service.ProcessService;
import com.kpei.mkbd.datatransform.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App 
{
    public static void main( String[] args )
    {
        File file = new File("/Users/andri/Documents/solecode/datatransform/XA231006.mkb");
        File mappingKey = new File("/Users/andri/Documents/solecode/datatransform/mapping-key-list.txt");
//        File file = new File("C:\\Users\\andri.rochman\\projects\\Custom\\data-transform\\XA231006.mkb");
//        File mappingKey = new File("C:\\Users\\andri.rochman\\projects\\Custom\\data-transform\\mapping-key-list.txt");
        System.out.println(LocalDateTime.now());
        Map<String, String> key = generateMappingKey(mappingKey);

        MkbTransformDto mkbTransformDto = new MkbTransformDto();
        mkbTransformDto.setUserId("fb68b928-f8f9-46f0-8cd1-2081f27483e7");
        mkbTransformDto.setUsername("admin");
        mkbTransformDto.setFilename("XA231006");
        mkbTransformDto.setKodePe("XA");
        mkbTransformDto.constructVd5Dto(file, key);

        System.out.println("total vd51 " + mkbTransformDto.getVd51().size());
        System.out.println("total vd52 " + mkbTransformDto.getVd52().size());
        System.out.println("total vd53 " + mkbTransformDto.getVd53().size());
        System.out.println("total vd54 " + mkbTransformDto.getVd54().size());
        System.out.println("total vd55 " + mkbTransformDto.getVd55().size());
        System.out.println("total vd56a " + mkbTransformDto.getVd56a().size());
        System.out.println("total vd56b " + mkbTransformDto.getVd56b().size());
        System.out.println("total vd56c " + mkbTransformDto.getVd56c().size());
        System.out.println("total vd57a " + mkbTransformDto.getVd57a().size());
        System.out.println("total vd57b " + mkbTransformDto.getVd57b().size());
        System.out.println("total vd57c " + mkbTransformDto.getVd57c().size());
        System.out.println("total vd58 " + mkbTransformDto.getVd58().size());
        System.out.println("total vd59 " + mkbTransformDto.getVd59().size());
        System.out.println("total vd510A " + mkbTransformDto.getVd510a().size());
        System.out.println("total vd510B " + mkbTransformDto.getVd510b().size());
        System.out.println("total vd510C " + mkbTransformDto.getVd510c().size());
        System.out.println("total vd510D " + mkbTransformDto.getVd510d().size());
        System.out.println("total vd510E " + mkbTransformDto.getVd510e().size());
        System.out.println("total vd510F " + mkbTransformDto.getVd510f().size());
        System.out.println("total vd510G " + mkbTransformDto.getVd510g().size());
        System.out.println("total vd510H " + mkbTransformDto.getVd510h().size());
        System.out.println("total vd510I " + mkbTransformDto.getVd510i().size());

        System.out.println(LocalDateTime.now());

        String baseDirectoryLog = "/Users/andri/Documents/solecode/datatransform/documents/logs";
        LogUtil logUtil = new LogUtil(baseDirectoryLog);

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
                ProcessService.processDataVD(finalConn, mkbTransformDto, logUtil);
                service.shutdown();
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
