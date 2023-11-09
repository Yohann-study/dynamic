package org.yohann.dynamic.common.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午3:14
 */
public class DateConverter implements Converter<Date> {

    public SimpleDateFormat getFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public Date convertToObj(String str) {
        if (str == null) {
            return null;
        }
        try {
            SimpleDateFormat formatter = getFormatter();
            return formatter.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException("failed to format date: " + str, e);
        }
    }

    @Override
    public String convertToString(Date obj) {
        if (obj == null) {
            return null;
        }

        SimpleDateFormat formatter = getFormatter();
        return formatter.format(obj);
    }
}
