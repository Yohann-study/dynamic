package org.yohann.dynamic.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.yohann.dynamic.core.pojo.PageData;
import org.yohann.dynamic.core.pojo.Pageable;
import org.yohann.dynamic.modle.entity.DynamicTable;

import java.util.List;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午3:31
 */
public class DynamicTableService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public DynamicFieldService fieldService;

    @Autowired
    public DynamicRowDataService dataService;

    public DynamicTable getOne(String id) {
        return mongoTemplate.findById(id, DynamicTable.class);
    }

    public PageData<DynamicTable> list(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNum(), pageable.getPageSize());
        List<DynamicTable> content = mongoTemplate.find(new Query().with(pageRequest), DynamicTable.class);
        long total = mongoTemplate.count(new Query(), DynamicTable.class);

        return pageable.pageData(content, total);
    }

    public void save(DynamicTable table) {
        String tableName = table.getName();
        boolean exists = isTableExists(tableName);
        if (table.getId() == null && exists) {
            throw new IllegalArgumentException("table is exists: " + tableName);
        }

        mongoTemplate.save(table);
    }

    public void delete(String id) {
        DynamicTable table = getOne(id);
        if (table != null) {
            mongoTemplate.remove(table);
            String tableName = table.getName();
            fieldService.deleteAllFields(tableName);
            dataService.deleteAllRowData(tableName);
        }
    }

    private boolean isTableExists(String tableName) {
        Query query = Query.query(Criteria.where(DynamicTable.Fields.name).is(tableName));
        return mongoTemplate.exists(query, DynamicTable.class);
    }
}
