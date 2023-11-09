package org.yohann.dynamic.modle.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.Map;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午12:25
 */
@Getter
@Setter
@FieldNameConstants
public class DynamicRowData {
    private String id;
    private String tableName;
    private Map<String, Object> data;
}
