package org.next.ws.core.event.impl;

import org.next.ws.core.event.BroadCaster;
import org.next.ws.core.event.standard.Communicate;
import org.next.ws.core.event.standard.EventType;

public class ConsoleBroadCaster implements BroadCaster {
    @Override
    public void broadCast(Communicate communicate) {
        System.out.println(communicate);
    }
}
