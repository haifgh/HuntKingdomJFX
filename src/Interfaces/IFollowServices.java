/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author ghofrane
 */
import entities.Follow;
import entities.Post;
import java.util.List;
public interface IFollowServices {
     public void follow(Follow f);
     public void unfollow(int id);
     public List<Follow> reloadfollow(int user , int followed);
     public List<Post> afficherpostfollow(int follower);
}
