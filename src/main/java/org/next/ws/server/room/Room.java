package org.next.ws.server.room;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {

    private String name;

    public Room(String name) {
        this.name = name;
    }
}
