package xyz.douzhan.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Description:
 * date: 2023/12/14 11:22
 *
 * @author 斗战圣洋
 * @since JDK 17
 */
@Data
@Accessors(fluent = true,chain = true)
@Schema(description = "统一返回结果")
public class ResponseResult<T> {
    private Integer code;
    private String message;
    private T data;
    public static <T> ResponseResult<T> success(T data){
        ResponseResult<T> result = new ResponseResult<>();
        result.code=200;
        result.data=data;
        return result;
    }
    public static <T> ResponseResult<T> success(){
        ResponseResult<T> result = new ResponseResult<>();
        result.code=200;
        return result;
    }

    public static <T> ResponseResult<T> error(T data){
        ResponseResult<T> result = new ResponseResult<>();
        result.code=400;
        result.data=data;
        return result;
    }

    public static <T> ResponseResult<T> error(){
        ResponseResult<T> result = new ResponseResult<>();
        result.code=400;
        return result;
    }
 }
