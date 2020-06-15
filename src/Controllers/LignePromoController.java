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
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
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
    private JFXTextField qte_field;
    
    ObservableList<String>  obspdtlist=FXCollections.observableArrayList();
    ObservableList<String>  obslplist=FXCollections.observableArrayList();
    @FXML
    private ListView<String> lst_lp;
    private Label idpromo_label;
     @FXML
    private AnchorPane root;
    @FXML
    private Label date;
     @FXML
    private Label name;
    @FXML
    private Label startdate;
    @FXML
    private Label rate;
    @FXML
    private Label enddate;
    LgPromotionDao srvlp=new LgPromotionDao();
    LignePromotion lp=new LignePromotion();
    PromotionDao p=new PromotionDao();
    Promotion prom=new Promotion();
    Produit e=new Produit();
  
   

    
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
  
   
    
     public void load(int i) {
         Promotion promo=p.returnPromo(i);
         prom=promo;
         System.out.println(prom.toString());
         name.setText(prom.getNom());
         rate.setText(String.valueOf(prom.getTaux_reduction()));
         startdate.setText(prom.getDate_debut());
         enddate.setText(prom.getDate_fin());
         
         
         //label_datedbt.setText(promo.getDate_debut().toString());
         //label_datef.setText(promo.getDate_fin().toString());
//         idpromo_label.setText(Integer.toString(promo.getId()));
         for(String s:srvlp.returnLgPromo(prom.getId())){
         obslplist.add(s);
         lst_lp.setItems(obslplist);
         }
           
        
     }
    
    
    @FXML
    private void add_lp(MouseEvent event) {
        
        if(qte_field.getText().isEmpty()){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("fields empty!!");
        alert.showAndWait();}
        else{
       lst_lp.getItems().clear();
       Produit p=returnId(srvlp.returnProducts());
       lp.setP(p);
       lp.setPromo(prom);
       lp.setQuantite(Integer.parseInt(qte_field.getText()));
       srvlp.create(lp);
       for(String s:srvlp.returnLgPromo(prom.getId())){
         obslplist.add(s);
         lst_lp.setItems(obslplist);}
       qte_field.setText("");
        }
        
       
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
         qte_field.setText("");
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
         qte_field.setText("");
        
        
    }

    private void back(MouseEvent event) {
        try {
            AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/Views/Promote1.fxml"));
            root.getChildren().setAll(pane1);
        } catch (IOException ex) {
            Logger.getLogger(LignePromoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
}
