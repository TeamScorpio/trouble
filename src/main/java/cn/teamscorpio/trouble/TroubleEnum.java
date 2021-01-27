package cn.teamscorpio.trouble;

import org.springframework.http.HttpStatus;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author TeamScorpio
 * @since 2021/01/01
 */
public interface TroubleEnum {




    default TroubleEnum causeBy(Throwable cause) {
        Trouble trouble = TroubleHolder.get(this);
        trouble.initCause(cause);
        return this;
    }

    default void thrown() {
        throw TroubleHolder.get(this);
    }

    default Trouble get() {
        return TroubleHolder.get(this);
    }

    default String getCode() {
        return get().getCode();
    }

    default String getMessage() {
        return get().getMessage();
    }

    default HttpStatus getHttpStatus() {
        final HttpStatus status = get().getHttpStatus();
        if (status == null) {
            throw new IllegalStateException("This enumeration doesn't support HTTP status, please use @HttpTrouble to support it.");
        }
        return status;
    }

    default Throwable getCause() {
        return get().getCause();
    }

    default void printStackTrace() {
        get().printStackTrace();
    }

    default void printStackTrace(PrintStream s) {
        get().printStackTrace(s);
    }

    default void printStackTrace(PrintWriter s) {
        get().printStackTrace(s);
    }



}
