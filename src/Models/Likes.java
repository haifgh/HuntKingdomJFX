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
public class Likes {
    private int id;
    private int id_user;
    private int id_guide;

    public Likes() {
    }

    public Likes(int id, int id_user, int id_guide) {
        this.id = id;
        this.id_user = id_user;
        this.id_guide = id_guide;
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

    @Override
    public String toString() {
        return "Likes{" + "id=" + id + ", id_user=" + id_user + ", id_guide=" + id_guide + '}';
    }
    
    
}
