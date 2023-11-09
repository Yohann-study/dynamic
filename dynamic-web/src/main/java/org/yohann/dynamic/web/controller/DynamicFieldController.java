package org.yohann.dynamic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yohann.dynamic.core.service.DynamicFieldService;
import org.yohann.dynamic.modle.entity.DynamicField;
import org.yohann.dynamic.web.pojo.Result;

import java.util.List;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午2:14
 */
@ResponseBody
@RequestMapping("dynamic/filed")
public class DynamicFieldController {

    @Autowired
    private DynamicFieldService service;

    @GetMapping("{tableName}")
    public Result<List<DynamicField>> getAllFields(@PathVariable String tableName) {
        List<DynamicField> fields = service.getAllFields(tableName);
        return Result.success(fields);
    }

    @PutMapping
    public Result<Object> saveAllFields(@RequestParam String tableName, @RequestBody List<DynamicField> fields) {
        service.saveAllFields(tableName, fields);
        return Result.success();
    }

}
