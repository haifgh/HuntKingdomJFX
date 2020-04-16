/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;





/**
 *
 * @author Mariem
 */
public class Promotion {
    private int id;
    private String nom;
    private String date_debut;
    private String date_fin;
    private int taux_reduction;

    
   

    public Promotion() {
    }

    public Promotion(int id, String nom, int taux_reduction) {
       this.id=id;
        this.nom = nom;
        this.taux_reduction = taux_reduction;
    }

   public Promotion(String nom, String date_debut, String date_fin, int taux_reduction) {
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.taux_reduction = taux_reduction;
    }   
    public Promotion(int id, String nom, String date_debut, String date_fin, int taux_reduction) {
        this.id = id;
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.taux_reduction = taux_reduction;
     
        
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public int getTaux_reduction() {
        return taux_reduction;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        
        this.date_fin = date_fin;
    }

    public void setTaux_reduction(int taux_reduction) {
        this.taux_reduction = taux_reduction;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", nom=" + nom + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", taux_reduction=" + taux_reduction +  '}';
    }

   
    
    
    
}
