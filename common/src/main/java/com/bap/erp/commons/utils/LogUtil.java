package com.bap.erp.commons.utils;

import com.bap.erp.commons.enums.LogLevel;
import org.slf4j.*;

public class LogUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtil.class);

    public static void log(String s) {

        commonConsoleLog(s);
    }

    public static void commonConsoleLog(String log) {

        String space = StringUtil.addIndent(1);
        LOGGER.info(new StringBuilder(space).append(StringUtil.changeHtmlToPlain(log)).toString());
    }

}
