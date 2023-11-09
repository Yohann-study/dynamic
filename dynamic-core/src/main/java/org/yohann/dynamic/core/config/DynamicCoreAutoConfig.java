package org.yohann.dynamic.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.yohann.dynamic.core.service.DynamicFieldService;
import org.yohann.dynamic.core.service.DynamicRowDataService;
import org.yohann.dynamic.core.service.DynamicTableService;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午4:37
 */
public class DynamicCoreAutoConfig {
    @Bean
    @ConditionalOnMissingBean
    public DynamicTableService dynamicTableService() {
        return new DynamicTableService();
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicFieldService dynamicFieldService() {
        return new DynamicFieldService();
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicRowDataService dynamicRowDataService() {
        return new DynamicRowDataService();
    }
}
