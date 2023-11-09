package org.yohann.dynamic.common.converter;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午3:07
 */
public class StringConverter implements Converter<String> {

    @Override
    public String convertToObj(String str) {
        return str;
    }

    @Override
    public String convertToString(String obj) {
        return obj;
    }
}
