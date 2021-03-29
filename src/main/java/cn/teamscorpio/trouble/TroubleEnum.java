package cn.teamscorpio.trouble;

import org.springframework.http.HttpStatus;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author TeamScorpio
 * @since 2021/01/01
 */
public interface TroubleEnum {

    default Trouble causeBy(Throwable cause) {
        Trouble trouble = (Trouble) TroubleHolder.get(this).clone();
        trouble.initCause(cause);
        return trouble;
    }

    default void thrown() {
        TroubleHolder.get(this).thrown();
    }

    default Trouble getTrouble() {
        return TroubleHolder.get(this);
    }

    default String getCode() {
        return getTrouble().getCode();
    }

    default String getMessage() {
        return getTrouble().getMessage();
    }

    default HttpStatus getHttpStatus() {
        final HttpStatus status = getTrouble().getHttpStatus();
        if (status == null) {
            throw new IllegalStateException("This enumeration doesn't support HTTP status, please use @HttpTrouble to support it.");
        }
        return status;
    }

    default Throwable getCause() {
        return getTrouble().getCause();
    }

    default void printStackTrace() {
        getTrouble().printStackTrace();
    }

    default void printStackTrace(PrintStream s) {
        getTrouble().printStackTrace(s);
    }

    default void printStackTrace(PrintWriter s) {
        getTrouble().printStackTrace(s);
    }



}
