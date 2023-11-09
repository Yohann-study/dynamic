package org.yohann.dynamic.common.converter;

import java.math.BigDecimal;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午3:08
 */
public class NumberConverter implements Converter<BigDecimal> {

    @Override
    public BigDecimal convertToObj(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return new BigDecimal(str);
    }

    @Override
    public String convertToString(BigDecimal obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }
}
