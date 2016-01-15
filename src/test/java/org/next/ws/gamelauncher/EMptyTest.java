package org.next.ws.gamelauncher;

import org.junit.Before;
import org.junit.Test;
import org.next.ws.core.event.standard.GameEventType;

public class EMptyTest {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test(){
        System.out.println(GameEventType.HAND_UPDATE.toString());
    }

}