package com.tdt4240.RawHeroes.network.communication.Response;

import java.io.Serializable;

/**
 * Created by espen1 on 27.02.2015.
 */


public class ResponseMessage implements Serializable{

    private final ResponseType type;
    private final Object content;

    public ResponseMessage(ResponseType type, Object content) {
        this.type = type;
        this.content = content;
    }

    public ResponseType getType() {
        return type;
    }

    public Object getContent() {
        return content;
    }
}
