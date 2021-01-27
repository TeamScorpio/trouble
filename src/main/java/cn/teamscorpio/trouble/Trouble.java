package cn.teamscorpio.trouble;

/**
 * @author TeamScorpio
 * @since 2021/01/01
 */
public class Trouble extends RuntimeException {

    private String code;

    public Trouble(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.initCause(cause);
    }

    public Trouble(String code, String message) {
        super(message);
        this.code = code;
    }

    public Trouble(String message, Throwable cause) {
        super(message, cause);
    }

    public Trouble(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }

}
