package org.next.ws.core.game.camp;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ToString
@Getter
public class Camp {

    public Camp(Player... players){
        this.players = Arrays.asList(players);
    }

    private List<Player> players;

}
