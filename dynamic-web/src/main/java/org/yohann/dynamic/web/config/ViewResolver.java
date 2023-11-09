package org.yohann.dynamic.web.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yohann.dynamic.web.pojo.Result;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午2:17
 */
@RestControllerAdvice
public class ViewResolver {
    @ExceptionHandler(Throwable.class)
    public Result<String> handleException(Throwable e) {
        return Result.failed(e.getMessage());
    }
}
