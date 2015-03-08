package com.tdt4240.RawHeroes.event.listener;

import com.tdt4240.RawHeroes.event.events.BoardEvent;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IBoardListener {
    void boardChanged(BoardEvent event);
}
