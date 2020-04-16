package Models;

import java.io.Serializable;

/**
 *
 * @author rejeb
 */
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer           id;
    private String            nom;
    private Integer           qte;
    private Integer           prix;
    private Double            prix_promo;

    public Produit() {}
    

    public Produit(Integer id, String nom, Integer qte, Integer prix) {
        this.id   = id;
        this.nom  = nom;
        this.qte  = qte;
        this.prix = prix;
    }
    public Produit(int id) {
        this.id = id;
    }

    public Produit(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Produit(int id, int qte, int prix, Double prix_promo) {
        this.id = id;
        this.qte = qte;
        this.prix = prix;
        this.prix_promo = prix_promo;
    }

    @Override
    public boolean equals(Object object) {

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }

        Produit other = (Produit) object;

        return !(((this.id == null) && (other.id != null)) || ((this.id != null) &&!this.id.equals(other.id)));
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash += ((id != null)
                 ? id.hashCode()
                 : 0);

        return hash;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", qte=" + qte + ", prix=" + prix + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrix_promo() {
        return prix_promo;
    }

    public void setPrix_promo(Double prix_promo) {
        this.prix_promo = prix_promo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
