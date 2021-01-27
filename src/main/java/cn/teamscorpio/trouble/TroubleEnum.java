package cn.teamscorpio.trouble;

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
