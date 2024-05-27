package com.kpei.mkbd.datatransform;

import com.kpei.mkbd.datatransform.dto.MkbTransformDto;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Map<String, String> key = new HashMap<>();
        File file = new File("/Users/andri/Documents/solecode/datatransform/XA231006.mkb");
        System.out.println(LocalDateTime.now());

        key.put("VD51.10", "VD51");
        key.put("VD52.129", "VD52");
        key.put("VD53.16", "VD53");
        key.put("VD54.10", "VD54");
        key.put("VD55.T", "VD55");
        key.put("VD56.10", "VD56A");
        key.put("VD56.20", "VD56B");
        key.put("VD56.24", "VD56C");
        key.put("VD56.P", "VD56C");
        key.put("VD57.11", "VD57A");
        key.put("VD57.33", "VD57B");
        key.put("VD57.63", "VD57C");
        key.put("VD58.20", "VD58");
        key.put("VD59.20", "VD59");
        key.put("VD59.28", "VD59");
        MkbTransformDto mkbTransformDto = new MkbTransformDto();
        mkbTransformDto.constructVd5Dto(file, key);

        System.out.println(LocalDateTime.now());
    }
}
