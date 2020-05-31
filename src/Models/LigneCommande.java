
package entities;

import java.io.Serializable;

/**
 *
 * @author rejeb
 */
public class LigneCommande implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
   
    private Integer quantity;

    private Float price;
    
    private Integer idProduit;
    
    private Integer idLC;
    
    public LigneCommande() {
    }

    public LigneCommande(Integer id, Integer quantity, Float price, Integer idProduit, Integer idLC) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.idProduit = idProduit;
        this.idLC = idLC;
    }

    public LigneCommande(Integer quantity, Float price, Integer idProduit) {
        this.quantity = quantity;
        this.price = price;
        this.idProduit = idProduit;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getIdLC() {
        return idLC;
    }

    public void setIdLC(Integer idLC) {
        this.idLC = idLC;
    }
  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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
        if (!(object instanceof LigneCommande)) {
            return false;
        }
        LigneCommande other = (LigneCommande) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "pidev.entities.Produit[ id=" + id + " ]";
    }

}