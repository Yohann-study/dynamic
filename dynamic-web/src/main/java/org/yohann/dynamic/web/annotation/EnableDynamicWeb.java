package org.yohann.dynamic.web.annotation;

import org.springframework.context.annotation.Import;
import org.yohann.dynamic.web.config.DynamicWebAutoConfig;

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
@Import(DynamicWebAutoConfig.class)
public @interface EnableDynamicWeb {
}
