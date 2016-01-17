package org.next.ws.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Timer;
import java.util.TimerTask;

public class Util {
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static Timer setTimeout(Runnable runnable, int timeout) {
        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        runnable.run();
                    }
                }, timeout
        );
        return timer;
    }

    public static <T> T assureNotNull(T obj) {
        if (obj == null)
            throw new WrongAccessException();
        return obj;
    }
}
