package com.cg.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil extends Properties {
    private static PropertiesUtil propertiesUtil = new PropertiesUtil();

    private PropertiesUtil() {
    }

    public static PropertiesUtil getMySqlProperties(String path) throws Exception {
        propertiesUtil.load(PropertiesUtil.getInputStreamReader(path + "/src/main/resources/config/cg_config.properties"));
        return propertiesUtil;
    }

    public static PropertiesUtil getRedisProperties(String path) throws Exception {
        propertiesUtil.load(PropertiesUtil.getInputStreamReader(path + "/src/main/resources/templates/config/redis.properties"));
        return propertiesUtil;
    }

    public static BufferedReader getInputStreamReader(String path) throws FileNotFoundException {
        // 使用BufferedReader流读取properties文件
        return new BufferedReader(new FileReader(path));
    }

}

