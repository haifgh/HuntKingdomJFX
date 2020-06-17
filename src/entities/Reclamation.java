/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Timestamp;

/**
 *
 * @author ghofrane
 */
public class Reclamation {
    private String date, contenu , object ;
    private int id , reclamer, user_id;
    private Timestamp ts;
    public Reclamation(Timestamp ts, String contenu, String object, int id, int reclamer, int user_id) {
        this.ts=ts;
        this.contenu = contenu;
        this.object = object;
        this.id = id;
        this.reclamer = reclamer;
        this.user_id = user_id;
    }

    public Reclamation(String contenu, String object, int reclamer) {
        this.contenu = contenu;
        this.object = object;
        this.reclamer = reclamer;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }


  
    


    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReclamer() {
        return reclamer;
    }

    public void setReclamer(int reclamer) {
        this.reclamer = reclamer;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "date=" + date + ", contenu=" + contenu + ", object=" + object + ", id=" + id + ", reclamer=" + reclamer + ", user_id=" + user_id + '}';
    }


    
    
    
}
