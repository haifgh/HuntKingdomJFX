
package entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author rejeb
 */
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
  
  
    private Timestamp date;
 
    private Timestamp dateLivraison;
    
    private String status;

    private String addresse;

    private String tel;

 
    public Commande() {
    }

    public Commande(Integer id, Timestamp date, Timestamp dateLivraison, String status, String addresse, String tel) {
        this.id = id;
        this.date = date;
        this.dateLivraison = dateLivraison;
        this.status = status;
        this.addresse = addresse;
        this.tel = tel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Timestamp dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "pidev.entities.User[ id=" + id + " ]";
    }

}