package utils;

import constants.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertFileReader {

    private PropertFileReader() {
    }

    private static Properties properties = new Properties();

    private static Map<String, String> MAP = new HashMap<>();

    static {

        try(FileInputStream fileInputStream = new FileInputStream(Constants.getInstance().getGLOBAL_PROPERTIES())) {

            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.entrySet().forEach(e -> MAP.put(String.valueOf(e.getKey()), String.valueOf(e.getValue())));

    }

    public static String getValue(String key){
        return MAP.get(key);

    }
}
