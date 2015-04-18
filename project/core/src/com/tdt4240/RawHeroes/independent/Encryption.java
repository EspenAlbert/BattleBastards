package com.tdt4240.RawHeroes.independent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Endre on 18.04.2015.
 */
public class Encryption {
    private String str;
    public Encryption(String str)throws NoSuchAlgorithmException{
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes());
        this.str = new String(md.digest());

    }

    public String toString(){
        return this.str;
    }
}
