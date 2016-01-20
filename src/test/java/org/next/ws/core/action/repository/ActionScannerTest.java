package org.next.ws.core.action.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.next.ws.util.Util;

public class ActionScannerTest {


    @Test
    public void test() throws JsonProcessingException {
        System.out.println(Util.OBJECT_MAPPER.writeValueAsString(ActionUtil.getActionInfo()));
    }

}