package com.tdt4240.RawHeroes.network.message;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class Message {

    private final MessageType type;
    private final Object content;

    public Message(MessageType type, Object content) {
        this.type = type;
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public Object getContent() {
        return content;
    }
}
