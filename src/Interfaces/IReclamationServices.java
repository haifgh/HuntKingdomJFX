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
import entities.Reclamation;
import java.util.List;
public interface IReclamationServices {
    public void ajouterreclamation(Reclamation r);
    public void modifierreclamation(int id , String contenu , String object );
    public void supprimerreclamation(int id);
    public List<Reclamation> afficherReclamation(int user);
    public void showReclamation(int id );
}
