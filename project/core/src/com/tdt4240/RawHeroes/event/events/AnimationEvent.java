package com.tdt4240.RawHeroes.event.events;

/**
 * Created by Endre on 20.04.2015.
 */
public class AnimationEvent {
    private int activeAnimation;
    private int activeFrame;


    public AnimationEvent(int activeAnimation, int activeFrame){
        this.activeAnimation = activeAnimation;
        this.activeFrame = activeFrame;
    }
    public int getActiveAnimation(){
        return activeAnimation;
    }
    public int getActiveFrame(){
        return activeFrame;
    }
}
