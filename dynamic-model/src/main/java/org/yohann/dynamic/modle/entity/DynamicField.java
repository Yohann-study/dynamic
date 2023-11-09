package org.yohann.dynamic.modle.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.yohann.dynamic.common.constants.FieldType;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午12:25
 */
@Getter
@Setter
@FieldNameConstants
public class DynamicField {
    private String id;
    private String name;
    private String description;
    private String tableName;
    private FieldType type;
}
