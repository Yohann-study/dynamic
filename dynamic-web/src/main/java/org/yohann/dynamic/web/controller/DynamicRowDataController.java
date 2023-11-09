package org.yohann.dynamic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yohann.dynamic.core.pojo.PageData;
import org.yohann.dynamic.core.pojo.RowDataQuery;
import org.yohann.dynamic.core.service.DynamicFieldService;
import org.yohann.dynamic.core.service.DynamicRowDataService;
import org.yohann.dynamic.web.pojo.Result;

import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午2:14
 */
@ResponseBody
@RequestMapping("dynamic/rowData")
public class DynamicRowDataController {

    @Autowired
    private DynamicRowDataService dataService;

    @Autowired
    private DynamicFieldService fieldService;

    @PostMapping("pageQuery")
    public Result<PageData<Map<String, Object>>> pageQuery(@RequestBody RowDataQuery query) {
        PageData<Map<String, Object>> pageData = dataService.list(query, query.getTableName(), query.getExample());
        return Result.success(pageData);
    }

    @PostMapping("saveRowData")
    public Result<Object> saveRowData(@RequestParam String tableName, @RequestBody List<Map<String, Object>> dataList) {
        dataService.saveRowData(tableName, dataList);
        return Result.success();
    }

    @DeleteMapping("deleteRowData")
    public Result<Object> deleteRowData(@RequestBody List<String> ids) {
        dataService.deleteRowData(ids);
        return Result.success();
    }

}
