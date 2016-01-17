package org.next.ws.core.action.impl;

import org.junit.Test;
import org.next.ws.core.action.Action;

public class ActionBuilderTest {

    @Test
    public void testGetAction() throws Exception {
        System.out.println(Action.getAction("1|2"));
        ManaAddAction a = new ManaAddAction("");

    }
}