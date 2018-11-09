package org.ydq.utils.tool;

import org.springframework.util.StringUtils;

public class Validator {

    public static boolean isInteger(String value) {
        if (isEmpty(value)) {
            return false;
        }
        try {
            int integer = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isEmpty(String value) {
        if (value == null || StringUtils.isEmpty(value)) {
            return true;
        }
        return false;
    }
}
