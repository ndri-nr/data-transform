package com.kpei.mkbd.datatransform;

import com.kpei.mkbd.datatransform.dto.MkbTransformDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class App 
{
    public static void main( String[] args )
    {
        File file = new File("/Users/andri/Documents/solecode/datatransform/XA231006.mkb");
        File mappingKey = new File("/Users/andri/Documents/solecode/datatransform/mapping-key-list.txt");
        System.out.println(LocalDateTime.now());
        Map<String, String> key = generateMappingKey(mappingKey);

        MkbTransformDto mkbTransformDto = new MkbTransformDto();
        mkbTransformDto.constructVd5Dto(file, key);

        System.out.println(LocalDateTime.now());
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
