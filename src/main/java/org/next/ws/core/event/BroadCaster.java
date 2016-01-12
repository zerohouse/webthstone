package org.next.ws.core.event;

import org.next.ws.core.event.standard.Communicate;

public interface BroadCaster {

    void broadCast(Communicate communicate);
}
