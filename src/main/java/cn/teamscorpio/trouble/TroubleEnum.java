package cn.teamscorpio.trouble;

/**
 * @author TeamScorpio
 * @since 2021/01/01
 */
public interface TroubleEnum {

    default String getCode() {
        return TroubleHolder.get(this).getCode();
    }

    default String getMessage() {
        return TroubleHolder.get(this).getMessage();
    }


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
}
