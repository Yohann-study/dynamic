package org.yohann.dynamic.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.yohann.dynamic.common.constants.FieldType;
import org.yohann.dynamic.common.converter.Converter;
import org.yohann.dynamic.core.pojo.PageData;
import org.yohann.dynamic.core.pojo.Pageable;
import org.yohann.dynamic.modle.entity.DynamicField;
import org.yohann.dynamic.modle.entity.DynamicRowData;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午3:31
 */
public class DynamicRowDataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private DynamicFieldService fieldService;

    public PageData<Map<String, Object>> list(Pageable pageable, String tableName, Map<String, Object> example) {
        List<DynamicField> allFields = fieldService.getAllFields(tableName);
        Map<String, FieldType> fieldTypeMap = allFields.stream()
                .collect(Collectors.toMap(DynamicField::getName, DynamicField::getType));

        Criteria criteria = new Criteria();
        for (Map.Entry<String, Object> entry : example.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            FieldType fieldType = fieldTypeMap.get(fieldName);
            if (fieldType == null) {
                throw new IllegalArgumentException(String.format("table: %s is not exists %s", tableName, fieldName));
            }

            if (value instanceof String) {
                Converter<Object> converter = fieldType.getConverter();
                value = converter.convertToObj((String) value);
            }

            criteria.and(fieldName).is(value);
        }

        PageRequest pageRequest = PageRequest.of(pageable.getPageNum(), pageable.getPageSize());
        Query query = Query.query(criteria).with(pageRequest);
        List<DynamicRowData> list = mongoTemplate.find(query, DynamicRowData.class);
        long total = mongoTemplate.count(query, DynamicRowData.class);

        List<Map<String, Object>> content = list.stream()
                .map(row -> {
                    Map<String, Object> rowData = new HashMap<>();
                    rowData.put("_id", row.getId());
                    for (Map.Entry<String, Object> entry : row.getData().entrySet()) {
                        String fieldName = entry.getKey();
                        Object value = entry.getValue();
                        FieldType fieldType = fieldTypeMap.get(fieldName);
                        if (value instanceof String) {
                            Converter<Object> converter = fieldType.getConverter();
                            value = converter.convertToObj((String) value);
                        }
                        rowData.put(fieldName, value);
                    }

                    return rowData;
                })
                .collect(Collectors.toList());

        return pageable.pageData(content, total);
    }

    public void saveRowData(String tableName, List<Map<String, Object>> dataList) {
        List<DynamicField> allFields = fieldService.getAllFields(tableName);
        Map<String, FieldType> fieldTypeMap = allFields.stream()
                .collect(Collectors.toMap(DynamicField::getName, DynamicField::getType));

        for (Map<String, Object> data : dataList) {
            String id = (String) data.remove("_id");
            Map<String, Object> row = new HashMap<>();

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();

                FieldType fieldType = fieldTypeMap.get(fieldName);
                if (fieldType == null) {
                    throw new IllegalArgumentException(String.format("table: %s is not exists %s", tableName, fieldName));
                }

                if (fieldType == FieldType.Date || fieldType == FieldType.DateTime) {
                    Converter<Object> converter = fieldType.getConverter();
                    value = converter.convertToObj((String) value);
                }
                row.put(fieldName, value);
            }

            DynamicRowData rowData = new DynamicRowData();
            rowData.setId(id);
            rowData.setTableName(tableName);
            rowData.setData(row);

            mongoTemplate.save(rowData);
        }
    }

    public void deleteAllRowData(String tableName) {
        Criteria criteria = Criteria.where(DynamicField.Fields.tableName).is(tableName);
        Query query = Query.query(criteria);
        mongoTemplate.remove(query, DynamicRowData.class);
    }

    public void deleteRowData(List<String> ids) {
        Criteria criteria = Criteria.where(DynamicField.Fields.id).in(ids);
        Query query = Query.query(criteria);
        mongoTemplate.remove(query, DynamicRowData.class);
    }

    public void deleteFields(String tableName, Collection<DynamicField> fields) {
        if (fields.isEmpty()) {
            return;
        }

        List<String> fieldNames = fields.stream()
                .map(DynamicField::getName)
                .collect(Collectors.toList());

        Criteria criteria = Criteria.where(DynamicField.Fields.tableName).is(tableName);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (String fieldName : fieldNames) {
            update.unset(fieldName);
        }

        mongoTemplate.upsert(query, update, DynamicRowData.class);
    }

}
