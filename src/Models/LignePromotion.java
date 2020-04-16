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
public class LignePromotion {
    private int id;
    private  Produit p;
    private Promotion promo;
    private int quantite;
    

    public LignePromotion() {
        
    }

    public LignePromotion(int id, Produit p, Promotion promo, int quantite) {
        this.id = id;
        this.p = p;
        this.promo = promo;
        this.quantite = quantite;
    }

    public LignePromotion(Produit p, Promotion promo, int quantite) {
        this.p = p;
        this.promo = promo;
        this.quantite = quantite;
    }

    public Produit getP() {
        return p;
    }

    public Promotion getPromo() {
        return promo;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    

    public int getId() {
        return id;
    }

   

    

    public int getQuantite() {
        return quantite;
    }

    public void setId(int id) {
        this.id = id;
    }

   
   
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "LignePromotion{" + "id=" + id + ", p=" + p + ", promo=" + promo + ", quantite=" + quantite + '}';
    }

    
 
    
}
