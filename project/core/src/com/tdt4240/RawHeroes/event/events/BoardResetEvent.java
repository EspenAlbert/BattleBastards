package com.tdt4240.RawHeroes.event.events;

/**
 * Created by espen1 on 19.04.2015.
 */
public class BoardResetEvent extends BoardEvent{

    public BoardResetEvent() {
        super();
        System.out.println("A reset event has been created");
    }
}
