package org.next.ws.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Timer;
import java.util.TimerTask;

public class Util {
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static Timer timer = new Timer();


    public static void setTimeout(Runnable runnable, int timeout) {
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        runnable.run();
                    }
                }, timeout
        );
    }
}
