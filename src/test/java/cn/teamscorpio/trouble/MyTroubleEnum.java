package cn.teamscorpio.trouble;

import cn.teamscorpio.trouble.annotation.Trouble;

/**
 * @author TeamScorpio
 * @since 2021/01/27
 */
public enum MyTroubleEnum implements TroubleEnum {

    @Trouble(code = "1", message = "trouble 1")
    TROUBLE_ONE,

    @Trouble(code = "2", message = "trouble 2")
    TROUBLE_TWO;

}
