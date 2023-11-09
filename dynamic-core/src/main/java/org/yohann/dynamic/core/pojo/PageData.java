package org.yohann.dynamic.core.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午5:51
 */
@Getter
@Setter
public class PageData<T> extends Pageable {
    private Long total;
    private List<T> content;
}
