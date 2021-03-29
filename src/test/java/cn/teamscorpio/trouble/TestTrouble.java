package cn.teamscorpio.trouble;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * @author TeamScorpio
 * @since 2021/01/27
 */
public class TestTrouble {

    @Test
    public void testThrown() {
        Assertions.assertThrows(Trouble.class, MyTroubleEnum.TROUBLE_ONE::thrown);
    }

    @Test
    public void testCauseBy() {
        final Throwable io_failed = MyTroubleEnum.TROUBLE_ONE.causeBy(new IOException("IO Failed")).getCause();
        Assertions.assertTrue(io_failed instanceof IOException);
    }

    @Test
    public void testGetCode() {
        Assertions.assertEquals("1", MyTroubleEnum.TROUBLE_ONE.getCode());
    }

    @Test
    public void testTroublePrefix() {
        Assertions.assertEquals("100001", MyPrefixedTroubleEnum.PREFIXED_TROUBLE_ONE.getCode());
    }

    @Test
    public void testTroubleMessage() {
        Assertions.assertEquals("trouble 1", MyTroubleEnum.TROUBLE_ONE.getMessage());
    }

    @Test
    public void testPrintStackTrace() {
        MyTroubleEnum.TROUBLE_ONE.causeBy(new IOException("IO Failed")).printStackTrace();
    }

    @Test
    public void testNoCode() {
        Assertions.assertEquals("0", NoCodeTroubleEnum.NO_CODE_TROUBLE.getCode());
        Assertions.assertEquals("NO_CODE_TROUBLE", NoCodeTroubleEnum.NO_CODE_TROUBLE.getMessage());
    }

    @Test
    public void testHttpTrouble() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, HttpTroubleEnum.BAD_REQUEST_TROUBLE.getHttpStatus());
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY, HttpTroubleEnum.BAD_GATEWAY_TROUBLE.getHttpStatus());
    }

}
