
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Services;

import java.sql.Timestamp;

import java.util.Map;

import Models.Commande;
import Models.LigneCommande;
import Models.Produit;

import Utilities.UserSession;

/**
 *
 * @author rejeb
 */
public class PanierServices {
    CommandeServices              cs  = new CommandeServices();
    LigneCommandeServices         lcs = new LigneCommandeServices();
    ProductServices1               ps  = new ProductServices1();
    private Map<Integer, Integer> p;
    private Commande              c;
    private LigneCommande         lc;
    private int                   cid;
    private Produit               produit;

    public void removeItem(Integer id) {
        p = UserSession.getInstance().getPanier();
        p.remove(id);
    }

    public void showPanier() {
        p = UserSession.getInstance().getPanier();
        System.out.println(p);
    }

    public Commande validerPanier(String adr, String tel) {
        p = UserSession.getInstance().getPanier();

        if (p.isEmpty()) {
            return null;
        }

        c = new Commande(null,
                         null,
                         0d,
                         new Timestamp(System.currentTimeMillis()),
                         null,
                         "Pending",
                         "charge//todo",
                         adr,
                         tel);
        cid = cs.create(c);
        c   = cs.FindOne(cid);

        for (Integer key : p.keySet()) {
            produit = ps.getP(key);
            lc      = new LigneCommande(null,
                                        p.get(key),
                                        (double) produit.getPrix() * p.get(key),
                                        produit.getId(),
                                        cid);
            lcs.create(lc);
            c.setPrixTotal(c.getPrixTotal() + (double) produit.getPrix() * p.get(key));
        }

        cs.update(c);
        p.clear();

        return c;
    }

    public Integer getPrixPanier() {
        Integer prix;

        prix = 0;
        p    = UserSession.getInstance().getPanier();
        prix = p.keySet().stream().map((key) -> {
                produit = ps.getP(key);

                return key;
            })  .map((key) -> {
                if(produit.getPrix_promo()==0)
                return produit.getPrix() * p.get(key);
                else
                return  produit.getPrix_promo() * p.get(key);
            })
                .reduce(prix, Integer::sum);

        return prix;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
