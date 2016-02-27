package com.bap.erp.commons.managers;

import com.bap.erp.commons.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigManager {

    private static Properties config = new Properties();

    public static void loadConfig(String propertyFile) {

        try {
            config.load(ConfigManager.class.getClassLoader().getResourceAsStream(propertyFile));
            LogUtil.log("Loaded: " + propertyFile);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.log("No config file " + propertyFile);
        }
    }

    public static String getValue(String key) {

        return config.getProperty(key);
    }

    public static List<String> getAllKeys() {

        List<String> keys = new ArrayList<String>();
        for (Object key : config.keySet()) {
            keys.add(key.toString());
        }
        return keys;
    }

}
