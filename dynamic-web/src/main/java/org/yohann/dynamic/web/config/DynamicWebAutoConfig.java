package org.yohann.dynamic.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.yohann.dynamic.core.annotation.EnableDynamicCore;
import org.yohann.dynamic.web.controller.DynamicFieldController;
import org.yohann.dynamic.web.controller.DynamicRowDataController;
import org.yohann.dynamic.web.controller.DynamicTableController;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午4:37
 */
@EnableDynamicCore
public class DynamicWebAutoConfig {
    @Bean
    @ConditionalOnMissingBean
    public DynamicTableController dynamicTableController() {
        return new DynamicTableController();
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicFieldController dynamicFieldController() {
        return new DynamicFieldController();
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicRowDataController dynamicRowDataController() {
        return new DynamicRowDataController();
    }

    @Bean
    @ConditionalOnMissingBean
    public ViewResolver viewResolver() {
        return new ViewResolver();
    }
}
