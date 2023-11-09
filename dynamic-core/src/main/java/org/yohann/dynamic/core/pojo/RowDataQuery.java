package org.yohann.dynamic.core.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * description:
 *
 * @author Yohann
 * @date 9/11/2023 上午9:58
 */
@Getter
@Setter
public class RowDataQuery extends Pageable {
    private String tableName;
    private Map<String, Object> example;
}
