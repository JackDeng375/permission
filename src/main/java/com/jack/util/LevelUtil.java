package com.jack.util;

import org.apache.commons.lang3.StringUtils;

public class LevelUtil {
    public final static String SEPARTOR = ".";

    public final static String ROOT = "0";

    //0
    //0.1
    //0.1.2
    //0.1.3
    //0.4
    public static String calculateLevel(String parentLevel, int parentId) {
        //如果为空说明是首词
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARTOR, parentId);
        }
    }
}
