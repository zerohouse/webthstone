package org.next.ws.web.room;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    private final List<Room> rooms;

    public RoomService() {
        this.rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room makeRoom(Map params) {
        Room room = new Room(params.get("name").toString());
        rooms.add(room);
        return room;
    }
}
