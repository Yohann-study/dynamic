package org.yohann.dynamic.core.annotation;

import org.springframework.context.annotation.Import;
import org.yohann.dynamic.core.config.DynamicCoreAutoConfig;

import java.lang.annotation.*;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午4:40
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(DynamicCoreAutoConfig.class)
public @interface EnableDynamicCore {
}
