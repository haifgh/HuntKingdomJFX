/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Promotion;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Mariem
 */
public interface IServicePromoDao {
    
    public void create(Promotion p);
    public List<Promotion> listPromo();
    public void deletePromo(String nom);
    public void updatePromo(int id,String nom, int tx_red,String d1,String d2);
    public boolean findPromobyname(String nom);
    public Promotion InfoPromo(String nom);
    public List<Promotion> findLikePromo(String s);
    
    
}
