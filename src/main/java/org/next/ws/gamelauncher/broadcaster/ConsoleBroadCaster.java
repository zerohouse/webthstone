package org.next.ws.gamelauncher.broadcaster;

import org.next.ws.core.game.Game;
import org.next.ws.core.game.camp.Camp;
import org.next.ws.gamelauncher.broadcaster.BroadCaster;

public class ConsoleBroadCaster implements BroadCaster {

    @Override
    public void broadCast(Game game) {
        print(PHASE_MESSAGE, game.getPhase());
        printCamp(game.getCampFirst());
    }

    private static final String PHASE_MESSAGE = "제 %d 턴";
    private static final String CAMP_MESSAGE = "[ %s의 진영 ] ";
    private static final String FIELD_MESSAGE = "필드 : %s";

    private void printCamp(Camp campFirst) {
        print(CAMP_MESSAGE, campFirst.getName());
        print(FIELD_MESSAGE, campFirst.getField().toString());
    }

    private void print(String format, Object... params) {
        System.out.println(String.format(format, params));
    }
}
