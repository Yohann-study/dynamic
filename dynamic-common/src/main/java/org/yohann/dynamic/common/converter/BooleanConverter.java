package org.yohann.dynamic.common.converter;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午3:11
 */
public class BooleanConverter implements Converter<Boolean> {
    @Override
    public Boolean convertToObj(String str) {
        if ("true".equals(str)) {
            return Boolean.TRUE;
        } else if ("false".equals(str)) {
            return Boolean.FALSE;
        }
        return null;
    }

    @Override
    public String convertToString(Boolean obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }
}
