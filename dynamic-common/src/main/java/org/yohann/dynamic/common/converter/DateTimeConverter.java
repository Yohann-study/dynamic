package org.yohann.dynamic.common.converter;

import java.text.SimpleDateFormat;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午3:20
 */
public class DateTimeConverter extends DateConverter {
    @Override
    public SimpleDateFormat getFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
