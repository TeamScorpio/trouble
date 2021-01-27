package cn.teamscorpio.trouble;

import org.springframework.http.HttpStatus;

/**
 * @author TeamScorpio
 * @since 2021/01/01
 */
public class Trouble extends RuntimeException {

    private String code;

    private HttpStatus httpStatus;

    public Trouble(String code, String message) {
        super(message);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
