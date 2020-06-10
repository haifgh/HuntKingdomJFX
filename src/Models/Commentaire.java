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
public class Commentaire {
    private int id;
    private int guide_id;
    private int user_id;
    private String contenu;
    private String date;

    public Commentaire() {
    }

   /* public Commentaire(int id, int guide_id, int user_id, String contenu) {
        this.id = id;
        this.guide_id = guide_id;
        this.user_id = user_id;
        this.contenu = contenu;
    }*/

    public Commentaire(int guide_id, int user_id, String contenu) {
        this.guide_id = guide_id;
        this.user_id = user_id;
        this.contenu = contenu;
    }

    public Commentaire(int guide_id, int user_id, String contenu, String date) {
        this.guide_id = guide_id;
        this.user_id = user_id;
        this.contenu = contenu;
        this.date = date;
    }

    public Commentaire(int id, int guide_id, int user_id, String contenu, String date) {
        this.id = id;
        this.guide_id = guide_id;
        this.user_id = user_id;
        this.contenu = contenu;
        this.date = date;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuide_id() {
        return guide_id;
    }

    public void setGuide_id(int guide_id) {
        this.guide_id = guide_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  "contenu:"+ contenu+"date:" + date ;
    }
    
}
