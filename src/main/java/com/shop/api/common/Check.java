package com.shop.api.common;

import com.shop.api.common.response.Const;
import com.shop.api.common.response.ResponseRow;

import java.lang.reflect.Field;

public class Check {

    public static <T> ResponseRow<T> CheckStringNotNullable(T object, int maxLen, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(object);
            if (value == null) {
                return new ResponseRow(Const.STATUS_CODE_FAIL_PARAM_NULL);
            }

            String str = (String) value;

            String strTrim = str.trim();
            if (strTrim.length() == 0) {
                return new ResponseRow(Const.STATUS_CODE_FAIL_PARAM_EMPTY);
            }

            if (strTrim.length() > Const.maxLen) {
                return new ResponseRow(Const.STATUS_CODE_FAIL_PARAM_TOO_LONG);
            }

            field.set(object, strTrim);
        } catch (Exception ex) {
            return new ResponseRow(Const.STATUS_CODE_FAIL);
        }

        return new ResponseRow();
    }
}
