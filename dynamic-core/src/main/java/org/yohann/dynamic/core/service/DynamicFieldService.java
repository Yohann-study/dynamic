package org.yohann.dynamic.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.yohann.dynamic.modle.entity.DynamicField;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午3:31
 */
public class DynamicFieldService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private DynamicRowDataService dataService;

    public List<DynamicField> getAllFields(String tableName) {
        Criteria criteria = Criteria.where(DynamicField.Fields.tableName).is(tableName);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, DynamicField.class);
    }

    public void saveAllFields(String tableName, List<DynamicField> fields) {
        Map<String, DynamicField> fieldIdMap = this.getAllFields(tableName).stream()
                .collect(Collectors.toMap(DynamicField::getId, Function.identity()));

        Map<String, DynamicField> fieldNameMap = fields.stream()
                .collect(Collectors.toMap(DynamicField::getName, Function.identity(), (v1, v2) -> v1));
        for (DynamicField field : fieldNameMap.values()) {
            if ("_id".equals(field.getName())) {
                throw new IllegalArgumentException("field name is not allow naming '_id'");
            }
            field.setTableName(tableName);
            fieldIdMap.remove(field.getId());
        }

        dataService.deleteFields(tableName, fieldIdMap.values());

        for (DynamicField dynamicField : fieldNameMap.values()) {
            mongoTemplate.save(dynamicField);
        }
    }

    public void deleteAllFields(String tableName) {
        Criteria criteria = Criteria.where(DynamicField.Fields.tableName).is(tableName);
        Query query = Query.query(criteria);
        mongoTemplate.remove(query, DynamicField.class);
    }

}
