package org.yohann.dynamic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yohann.dynamic.core.pojo.PageData;
import org.yohann.dynamic.core.pojo.Pageable;
import org.yohann.dynamic.core.service.DynamicTableService;
import org.yohann.dynamic.modle.entity.DynamicTable;
import org.yohann.dynamic.web.pojo.Result;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午2:14
 */
@ResponseBody
@RequestMapping("dynamic/table")
public class DynamicTableController {

    @Autowired
    private DynamicTableService service;

    @GetMapping("{id}")
    public Result<DynamicTable> getOne(@PathVariable String id) {
        DynamicTable table = service.getOne(id);
        return Result.success(table);
    }

    @GetMapping("list")
    public Result<PageData<DynamicTable>> list(Pageable pageable) {
        PageData<DynamicTable> pageData = service.list(pageable);
        return Result.success(pageData);
    }

    @PutMapping
    public Result<Object> save(@RequestBody DynamicTable table) {
        service.save(table);
        return Result.success();
    }

    @DeleteMapping("{id}")
    public Result<String> delete(@PathVariable String id) {
        service.delete(id);
        return Result.success();
    }
}
