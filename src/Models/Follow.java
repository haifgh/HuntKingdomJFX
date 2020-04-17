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
public class Follow {
    private int id,follower,followed;

    public Follow(int id, int follower, int followed) {
        this.id = id;
        this.follower = follower;
        this.followed = followed;
    }

    public Follow(int followed) {
        this.followed = followed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "follow{" + "id=" + id + ", follower=" + follower + ", followed=" + followed + '}';
    }
    
    
    
}
