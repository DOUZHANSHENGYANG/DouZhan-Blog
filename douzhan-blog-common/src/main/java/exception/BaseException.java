package exception;

/**
 * Description:
 * date: 2023/12/14 16:28
 *
 * @author 斗战圣洋
 * @since JDK 17
 */
public class BaseException extends RuntimeException{

    public BaseException(String message) {
        super(message);
    }


    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
