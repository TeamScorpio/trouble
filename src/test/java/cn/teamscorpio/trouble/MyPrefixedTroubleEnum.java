package cn.teamscorpio.trouble;

import cn.teamscorpio.trouble.annotation.Trouble;
import cn.teamscorpio.trouble.annotation.TroubleCodePrefix;

/**
 * @author TeamScorpio
 * @since 2021/01/27
 */
@TroubleCodePrefix("10000")
public enum MyPrefixedTroubleEnum implements TroubleEnum {

    @Trouble(code = "1", message = "prefixed trouble 1")
    PREFIXED_TROUBLE_ONE,

}
