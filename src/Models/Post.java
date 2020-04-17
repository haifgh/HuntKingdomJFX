/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author ghofrane
 */
public class Post {
      private int id ;
      private String contenu,dateCreation;
      private int user_id;

    public Post(String contenu) {
        this.contenu = contenu;
       
    }

    public Post() {
    }

    public Post(int id, String contenu, String dateCreation, int user_id) {
        this.id = id;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.user_id = user_id;
    }

    public Post(String contenu, int user_id) {
        this.contenu = contenu;
        this.user_id = user_id;
    }
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }



    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user) {
        this.user_id = user_id;
    }



    @Override
    public String toString() {
        return "post{" + "id=" + id + ", dateCreation=" + dateCreation + ", contenu=" + contenu + ", user=" + user_id + '}';
    }

    
      
      
}
