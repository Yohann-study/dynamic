package org.yohann.dynamic.common.constants;

import org.yohann.dynamic.common.converter.*;

import java.util.function.Supplier;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午2:49
 */
public enum FieldType {
    String(StringConverter::new),
    Number(NumberConverter::new),
    Boolean(BooleanConverter::new),
    Date(DateConverter::new),
    DateTime(DateTimeConverter::new),
    ;

    @SuppressWarnings("unchecked")
    FieldType(Supplier<Converter<?>> supplier) {
        this.converter = (Converter<Object>) supplier.get();
    }

    private final Converter<Object> converter;

    public Converter<Object> getConverter() {
        return converter;
    }

}
