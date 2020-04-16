/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.LignePromotion;
import Models.Produit;
import Models.Promotion;
import Services.LgPromotionDao;
import Services.PromotionDao;
import com.gluonhq.charm.glisten.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Mariem
 */
public class LignePromoController implements Initializable {

    @FXML
    private ComboBox<String> lst_product;
    @FXML
    private TextField qte_field;
    
    ObservableList<String>  obspdtlist=FXCollections.observableArrayList();
    ObservableList<String>  obslplist=FXCollections.observableArrayList();
    @FXML
    private ListView<String> lst_lp;
    @FXML
    private Label nom_label;
    @FXML
    private Label label_datedbt;
    @FXML
    private Label label_datef;
    @FXML
    private Label idpromo_label;
    
   
    LgPromotionDao srvlp=new LgPromotionDao();
    LignePromotion lp=new LignePromotion();
    PromotionDao p=new PromotionDao();
    Promotion prom=new Promotion();
    Produit e=new Produit();
    @FXML
    private AnchorPane root;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        qte_field.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
      public void handle(KeyEvent keyEvent) {
          if (!"0123456789".contains(keyEvent.getCharacter())) {
           keyEvent.consume();
          }
        }
      });
      fillComobox();
    
     
    }    
    private void fillComobox(){for (Produit p:srvlp.returnProducts()){
      obspdtlist.add(p.getNom());}
      lst_product.setItems(obspdtlist);}
    
    
     private Produit returnId(List<Produit> ls){
     String s=lst_product.getSelectionModel().getSelectedItem();
     for (Produit p:srvlp.returnProducts()){
     if(p.getNom().equals(s))
     e=p;}
     return e;}
  
   
    
     public void load(Integer i) {
         Promotion promo=p.returnPromo(i);
         prom=promo;
         nom_label.setText(promo.getNom());
         label_datedbt.setText(promo.getDate_debut().toString());
         label_datef.setText(promo.getDate_fin().toString());
         idpromo_label.setText(Integer.toString(promo.getId()));
         for(String s:srvlp.returnLgPromo(promo.getId())){
         obslplist.add(s);
         lst_lp.setItems(obslplist);}
           
        
     }
    
    
    @FXML
    private void add_lp(MouseEvent event) {
       lst_lp.getItems().clear();
       Produit p=returnId(srvlp.returnProducts());
       lp.setP(p);
       lp.setPromo(prom);
       lp.setQuantite(Integer.parseInt(qte_field.getText()));
       srvlp.create(lp);
       for(String s:srvlp.returnLgPromo(prom.getId())){
         obslplist.add(s);
         lst_lp.setItems(obslplist);}
       
       
    }
   

    @FXML
    private void update_lp(MouseEvent event) {
         lst_lp.getItems().clear();
         Produit p=returnId(srvlp.returnProducts());
         lp.setP(p);
         lp.setPromo(prom);
         lp.setQuantite(Integer.parseInt(qte_field.getText()));
         srvlp.Updatelp(lp);
         for(String s:srvlp.returnLgPromo(prom.getId())){
         obslplist.add(s);
         lst_lp.setItems(obslplist);}
    }
     @FXML
    private void delete_lp(MouseEvent event) {
        lst_lp.getItems().clear();
        Produit p=returnId(srvlp.returnProducts());
        lp.setP(p);
        lp.setPromo(prom);
        srvlp.deleteLgPromo(lp);
         for(String s:srvlp.returnLgPromo(prom.getId())){
         obslplist.add(s);
         lst_lp.setItems(obslplist);}
        
        
    }

    @FXML
    private void back(MouseEvent event) {
        try {
            AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/Views/Promote1.fxml"));
            root.getChildren().setAll(pane1);
        } catch (IOException ex) {
            Logger.getLogger(LignePromoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
}
