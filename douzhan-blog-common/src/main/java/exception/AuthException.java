package exception;

/**
 * Description:
 * date: 2023/12/14 16:29
 *
 * @author 斗战圣洋
 * @since JDK 17
 */
public class AuthException extends BaseException{
    /**
     * @param message
     */
    public AuthException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
