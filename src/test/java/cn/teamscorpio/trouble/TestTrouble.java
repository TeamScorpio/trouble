package cn.teamscorpio.trouble;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        final Throwable io_failed = MyTroubleEnum.TROUBLE_ONE.causeBy(new IOException("IO Failed")).get().getCause();
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

}
