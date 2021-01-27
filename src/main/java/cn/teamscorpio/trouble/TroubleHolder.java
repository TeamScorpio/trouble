package cn.teamscorpio.trouble;

import cn.teamscorpio.trouble.annotation.TroubleCodePrefix;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TeamScorpio
 * @since 2021/01/01
 */
public class TroubleHolder {


    private static final Map<Object, Trouble> CODE_MAP = new ConcurrentHashMap<>();

    public static Trouble get(Object object) {
        if (CODE_MAP.containsKey(object)) {
            return CODE_MAP.get(object);
        }
        try {
            Enum<?> codeEnum = (Enum<?>) object;
            TroubleCodePrefix troubleCodePrefix = object.getClass().getAnnotation(TroubleCodePrefix.class);

            cn.teamscorpio.trouble.annotation.Trouble trouble = object.getClass().getField(codeEnum.name())
                    .getAnnotation(cn.teamscorpio.trouble.annotation.Trouble.class);
            String code = trouble != null ? trouble.code() : String.valueOf(codeEnum.ordinal());
            String codePrefix = troubleCodePrefix != null ? troubleCodePrefix.value() : "";
            String completeCode = codePrefix + code;
            String completeMessage = codeEnum.name();
            if (trouble != null) {
                completeMessage = trouble.message();
            }
            Trouble troubleException = new Trouble(completeCode, completeMessage);
            CODE_MAP.put(object, troubleException);
            return troubleException;
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }
}
