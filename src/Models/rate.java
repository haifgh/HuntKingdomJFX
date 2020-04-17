/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author haifa
 */
public class rate {
       private int id ;
    private int id_user;
    private int id_guide;
    private int note;

    public rate() {
    }

    public rate(int id, int id_user, int id_guide, int note) {
        this.id = id;
        this.id_user = id_user;
        this.id_guide = id_guide;
        this.note = note;
    }

    public rate(int id_user, int id_guide, int note) {
        this.id_user = id_user;
        this.id_guide = id_guide;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_guide() {
        return id_guide;
    }

    public void setId_guide(int id_guide) {
        this.id_guide = id_guide;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "rate{" + "id=" + id + ", id_user=" + id_user + ", id_guide=" + id_guide + ", note=" + note + '}';
    }
    
    
}
