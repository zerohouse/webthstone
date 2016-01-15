package org.next.ws.server.room;

import org.next.ws.server.websocket.jeo.JeoController;
import org.next.ws.server.websocket.jeo.JeoEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@JeoController
public class RoomController {

    @Autowired
    RoomService roomService;

    @JeoEvent("get.rooms")
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @JeoEvent("make.room")
    public Room makeRoom(Map params) {
        return roomService.makeRoom(params);
    }

}
