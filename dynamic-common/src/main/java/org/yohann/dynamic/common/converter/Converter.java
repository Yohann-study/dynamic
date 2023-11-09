package org.yohann.dynamic.common.converter;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午2:57
 */
public interface Converter<T> {

    T convertToObj(String str);

    String convertToString(T obj);
}
