package cn.teamscorpio.trouble;

import cn.teamscorpio.trouble.annotation.HttpTrouble;
import cn.teamscorpio.trouble.annotation.TroubleCodePrefix;

import java.lang.reflect.Field;
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
            final Enum<?> codeEnum = (Enum<?>) object;
            final Class<?> objectClass = object.getClass();
            final Field field = objectClass.getField(codeEnum.name());
            TroubleCodePrefix troubleCodePrefix = objectClass.getAnnotation(TroubleCodePrefix.class);

            cn.teamscorpio.trouble.annotation.Trouble trouble = field
                    .getAnnotation(cn.teamscorpio.trouble.annotation.Trouble.class);
            String code = trouble != null ? trouble.code() : String.valueOf(codeEnum.ordinal());
            String codePrefix = troubleCodePrefix != null ? troubleCodePrefix.value() : "";
            String completeCode = codePrefix + code;
            String completeMessage = codeEnum.name();
            if (trouble != null) {
                completeMessage = trouble.message();
            }
            Trouble troubleException = new Trouble(completeCode, completeMessage);

            // http support
            HttpTrouble httpTrouble = field.isAnnotationPresent(HttpTrouble.class) ? field.getAnnotation(HttpTrouble.class) : objectClass.getAnnotation(HttpTrouble.class);
            troubleException.setHttpStatus(httpTrouble == null ? null : httpTrouble.status());
            CODE_MAP.put(object, troubleException);
            return troubleException;
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }
}
