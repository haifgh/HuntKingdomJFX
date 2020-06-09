
package entities;

import java.io.Serializable;

/**
 *
 * @author rejeb
 */
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
  
    private String nom;
 
    private Integer qte;

    private Float prix;
 
    public Produit() {
    }
    public Produit(Integer id,String nom,Integer qte,Float prix) {
        this.id=id;
        this.nom=nom;
        this.qte=qte;
        this.prix=prix; 
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }
       
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "pidev.entities.Produit[ id=" + id + " ]";
    }

}