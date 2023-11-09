package org.yohann.dynamic.web.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午2:16
 */
@Getter
@Setter
public class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> success(String message) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failed(String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

}
