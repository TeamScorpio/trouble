package cn.teamscorpio.trouble;

import cn.teamscorpio.trouble.annotation.HttpTrouble;
import cn.teamscorpio.trouble.annotation.Trouble;
import org.springframework.http.HttpStatus;

/**
 * @author TeamScorpio
 * @since 2021/01/27
 */
@HttpTrouble(status = HttpStatus.BAD_REQUEST)
public enum HttpTroubleEnum implements TroubleEnum {

    @Trouble(code = "0", message = "Bad request trouble")
    BAD_REQUEST_TROUBLE,

    @HttpTrouble(status = HttpStatus.BAD_GATEWAY)
    @Trouble(code = "1", message = "Bad gateway trouble")
    BAD_GATEWAY_TROUBLE;

}
