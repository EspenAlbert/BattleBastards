package com.tdt4240.RawHeroes.network.server.database;

import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SaveObject {

    public Object javaObject=null;

    public Object getJavaObject() {
        return javaObject;
    }


    public void setJavaObject(Object javaObject) {
        this.javaObject = javaObject;
    }


    public void updateObject() throws Exception
    {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = null;
        String sql = null;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(javaObject);
        oos.flush();
        oos.close();
        bos.close();


        byte[] data = bos.toByteArray();
        sql="update javaobject set javaObject = ? where id = 7";
        stmt = conn.prepareStatement(sql);
        stmt.setObject(1, data);
        stmt.executeUpdate();

    }

    public  void saveObject() throws Exception
    {
        try{
            Connection conn= DatabaseConnection.getConnection();
            PreparedStatement ps=null;
            String sql=null;

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(javaObject);
            oos.flush();
            oos.close();
            bos.close();

            byte[] data = bos.toByteArray();


            sql="insert into javaobject (javaObject) values(?)";
            ps=conn.prepareStatement(sql);
            ps.setObject(1, data);
            ps.executeUpdate();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }


    public Object getObject() throws Exception
    {
        Object rmObj=null;
        Connection conn=  DatabaseConnection.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql=null;

        sql="select * from javaobject where id=7";

        ps=conn.prepareStatement(sql);

        rs=ps.executeQuery();

        if(rs.next())
        {
            ByteArrayInputStream bais;

            ObjectInputStream ins;

            try {

                bais = new ByteArrayInputStream(rs.getBytes("javaObject"));

                ins = new ObjectInputStream(bais);

                Player mc =(Player)ins.readObject();

                System.out.println("Object in value ::"+mc.getUsername());
                ins.close();
                return mc;

            }
            catch (Exception e) {

                e.printStackTrace();
            }
        }

        return rmObj;
    }
}
