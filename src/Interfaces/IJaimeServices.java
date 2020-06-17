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
import entities.Jaime;
import java.util.List;
import javafx.scene.control.Button;
public interface IJaimeServices {
     public void ajouterjaime(Jaime j);
     public void supprimerjaime(int id);
     public int nbrejaime(int id);
      public List<Jaime> reloadjaime(int user , int post);
}
