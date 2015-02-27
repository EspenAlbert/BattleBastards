package com.tdt4240.RawHeroes.logic.modelListener;

import com.tdt4240.RawHeroes.logic.events.BoardEvent;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IBoardListener {
    void BoardChanged(BoardEvent event);
}
