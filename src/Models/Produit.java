/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.util.Objects;


/**
 *
 * @author PCS
 */
public class Produit {

    public Produit() {
    }
  private int id ;
    private int categorie_id;
    private String nom;

    
    public Produit(int id, int categorie_id, String nom, int qte, int prix, int prix_promo, String description, String photo) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.nom = nom;
        this.qte = qte;
        this.prix = prix;
        this.prix_promo = prix_promo;
        this.description = description;
        this.photo = photo;
    }
    private int qte;
    private int prix;
    private int prix_promo;
    private String description ;
    private String photo;
   

    public int getPrix_promo() {
        return prix_promo;
    }

    public void setPrix_promo(int prix_promo) {
        this.prix_promo = prix_promo;
    }

    public Produit(int id, int categorie_id, String nom, int qte, int prix, String description, String photo, int prix_promo) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.nom = nom;
        this.qte = qte;
        this.prix = prix;
        this.description = description;
        this.photo = photo;
        this.prix_promo = prix_promo;
    }

    public Produit(int id, String nom, int qte, int prix, String description) {
        this.id = id;
        this.nom = nom;
        this.qte = qte;
        this.prix = prix;
        this.description = description;
    }

    public Produit(int id, int categorie_id, String nom, int qte, int prix, String description) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.nom = nom;
        this.qte = qte;
        this.prix = prix;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.id;
        hash = 31 * hash + this.categorie_id;
        hash = 31 * hash + Objects.hashCode(this.nom);
        hash = 31 * hash + this.qte;
        hash = 31 * hash + this.prix;
        hash = 31 * hash + Objects.hashCode(this.description);
        hash = 31 * hash + Objects.hashCode(this.photo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.categorie_id != other.categorie_id) {
            return false;
        }
        if (this.qte != other.qte) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", categorie_id=" + categorie_id + ", nom=" + nom + ", qte=" + qte + ", prix=" + prix + ", description=" + description + ", photo=" + photo + ", prix_promo=" + prix_promo + '}';
    }

    public Produit(int id, int categorie_id, String nom, int qte, int prix, int prix_promo, String description) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.nom = nom;
        this.qte = qte;
        this.prix = prix;
        this.prix_promo = prix_promo;
        this.description = description;
    }

 
    
  

   


   
    
}
