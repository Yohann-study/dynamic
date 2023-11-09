package org.yohann.dynamic.core.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午5:49
 */
@Getter
@Setter
public class Pageable {
    private Integer pageNum = 0;
    private Integer pageSize = 10;

    public <T> PageData<T> pageData(List<T> content, Long total) {
        PageData<T> pageData = new PageData<>();
        pageData.setTotal(total);
        pageData.setContent(content);
        pageData.setPageNum(this.pageNum);
        pageData.setPageSize(this.pageSize);

        return pageData;
    }
}
