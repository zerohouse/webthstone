package org.next.ws.gamelauncher;

import org.junit.Before;
import org.junit.Test;
import org.next.ws.core.event.standard.GameEventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.*;

public class EMptyTest {


    @Before
    public void setUp() throws Exception {

    }

    @Test(timeout = 6000)
    public void test() {
        System.out.println(1);
        java.util.Timer a = new Timer();

        a.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(3);
                    }
                },
                1000
        );

                System.out.println(2);
    }

}