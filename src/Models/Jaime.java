/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ghofrane
 */
public class Jaime {
    private int id;
     private int post,user;

    public Jaime(int id, int post, int user) {
        this.id = id;
        this.post = post;
        this.user = user;
    }

    public Jaime(int post, int user) {
        this.post = post;
        this.user = user;
    }

    public Jaime(int post) {
        this.post = post;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    

    @Override
    public String toString() {
        return "jaime{" + "id=" + id + ", post=" + post + ", user=" + user + '}';
    }
   
    
}
