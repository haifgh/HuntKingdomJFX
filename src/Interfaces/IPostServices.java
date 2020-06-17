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
import entities.Post;
import java.util.List;
public interface IPostServices {
    public void ajouterPost (Post p);
    public void modifierpost(int id , String contenu);
    public void supprimerpost(int id);
    public List<Post> afficherpost(int user_id);
  
    
    
}
