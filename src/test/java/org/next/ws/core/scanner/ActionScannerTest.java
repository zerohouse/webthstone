package org.next.ws.core.scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.next.ws.util.Util;

public class ActionScannerTest {


    @Test
    public void test() throws JsonProcessingException {
        System.out.println(Util.OBJECT_MAPPER.writeValueAsString(new ComponentScanner().getActionInfo()));
    }
    @Test
    public void tests() throws JsonProcessingException {
    }

}