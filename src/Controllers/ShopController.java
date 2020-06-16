/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Categorie;
import Models.Produit;
import Services.CategorieServices;
import Services.PanierServices;
import Services.ProductServices;
import Services.ProductServices1;
import Utilities.Connexion;
import Utilities.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author arij
 */
public class ShopController implements Initializable {

    @FXML
    private VBox VB;
    @FXML
    private TextField searchtf;
    @FXML
    private Button btnsearch;
    @FXML
    private AnchorPane all;
    @FXML
    private ComboBox cbox;
    @FXML
    private Pane pnllist;
    @FXML
    private Label total;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       
            // TODO
            Shop();
            shopcat();
     
    }    
     private void Shop() 
        {  
         
        try {
            PanierServices pas=new PanierServices();
            total.setText(pas.getPrixPanier().toString()+" TND");
            ProductServices sp = new ProductServices();
            
            List<Produit> lp = new ArrayList<>();
            lp= sp.getAllP();
            for (Produit p : lp) {
                VBox h1 = new VBox();
                HBox h2 = new HBox();
                ScrollPane sl = new ScrollPane ();
                sl.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                sl.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                sl.setContent(VB);
                String stylep ="-fx-background-color:#fff;" ;
                sl.setStyle(stylep);
                pnllist.getChildren().add(sl);
                VB.setSpacing(5);
                h1.setSpacing(5);
                String sql = "SELECT * FROM produit";
                String lien = p.getPhoto();
                String lien2 = lien.replaceAll("//", "/");
                File file = new File(lien2);
                String localUrl = file.toURI().toURL().toString();
                ImageView ImageView = new ImageView();
                Image image = new Image(localUrl);
                Rectangle rekt = new Rectangle(250,250);
                ImagePattern imagePattern =new ImagePattern(image);
                rekt.setFill(imagePattern);
                ImageView.setImage(image);
                Text name = new Text(p.getNom());
                Text qte = new Text(""+p.getQte()+" Pieces Left");
                Text price = new Text("Price : "+p.getPrix());
                Text promo = new Text("Promo Price : "+p.getPrix_promo());
                Text description = new Text("Description : "+p.getDescription());
                final JFXButton addButton = new JFXButton("add");
                                      addButton.getStyleClass().add("custom-button");
                                      addButton.getStyleClass().add(".jfx-button");
                                      addButton.setOnAction(
                                          event -> {
                                              
                                              UserSession.getInstance().addToPanier(p.getId(), 1);
                                              total.setText(pas.getPrixPanier().toString()+" TND");
                                          });
                String stylebt = "-fx-background-color: #FFFFFF;";
                String style = "-fx-background-color: #FFFFFF; -fx-padding: 10 ;";
                String stylU = "-fx-font-weight: bold; -fx-font-size: 20px;";
                String stylc = "-fx-font-weight: bold; -fx-font-size: 12px;";
                h1.setStyle(style);
                VB.setStyle(style);
                name.setStyle(stylU);
                price.setStyle(stylc);
                promo.setStyle(stylc);
                description.setStyle(stylc);
                qte.setStyle(stylc);
                h2.getChildren().add(rekt);
                h2.getChildren().add(h1);
                h1.getChildren().add(name);
                h1.getChildren().add(price);
                h1.getChildren().add(promo);
                h1.getChildren().add(qte);
                h1.getChildren().add(description);
                h1.getChildren().add(addButton);
                VB.getChildren().add(h2);
                
            }      
        } catch (SQLException ex) {
            
        } catch (IOException ex) {
           
        }
        
        }
     @FXML
     private void search ()
     {
        try {
            Button btnall = new Button("all");
            all.getChildren().add(btnall);
            btnall.setOnAction(event ->{
            VB.getChildren().clear();
             Shop();});
            ProductServices su = new ProductServices ();
            Produit p = new Produit();
            p=su.findPByName(searchtf.getText());
            VB.getChildren().clear();
            VBox h1 = new VBox();
            HBox h2 = new HBox();
            ScrollPane sl = new ScrollPane ();
            sl.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            sl.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            sl.setContent(VB);
            String stylep ="-fx-background-color:none;" ;
            sl.setStyle(stylep);
            pnllist.getChildren().add(sl);
            VB.setSpacing(5);
             h1.setSpacing(5);
            String lien = p.getPhoto();
            String lien2 = lien.replaceAll("//", "/");
            File file = new File(lien2);
            String localUrl = file.toURI().toURL().toString();
            ImageView ImageView = new ImageView();
            Image image = new Image(localUrl);
            Rectangle rekt = new Rectangle(250,250);
            ImagePattern imagePattern =new ImagePattern(image);
            rekt.setFill(imagePattern);
            Text name = new Text(p.getNom());
            Text qte = new Text(""+p.getQte()+" Pieces Left");
            Text price = new Text("Price : "+p.getPrix());
            Text promo = new Text("Promo Price : "+p.getPrix_promo());
            Text description = new Text("Description : "+p.getDescription());
            String stylebt = "-fx-background-color: #FFFFFF;";
            String style = "-fx-background-color: #FFFFFF; -fx-padding: 5 ;";
            String stylU = "-fx-font-weight: bold; -fx-font-size: 20px;";
                String stylc = "-fx-font-weight: bold; -fx-font-size: 12px;";
                h1.setStyle(style);
                VB.setStyle(style);
                name.setStyle(stylU);
                price.setStyle(stylc);
                promo.setStyle(stylc);
                description.setStyle(stylc);
                qte.setStyle(stylc);
                h2.getChildren().add(rekt);
                h2.getChildren().add(h1);
                h1.getChildren().add(name);
                h1.getChildren().add(price);
                h1.getChildren().add(promo);
                h1.getChildren().add(qte);
                h1.getChildren().add(description);
                VB.getChildren().add(h2);
        } catch (MalformedURLException ex) {
            
        }
                
         
     }
    private void shopcat ()
     {
       CategorieServices sc = new CategorieServices();
       List<Categorie> lc = new ArrayList<>();
       lc= sc.readC();
       //System.out.println(lc);
      List<String> lcn = new ArrayList<>();
    for (Categorie c:lc){
        lcn.add(c.getNom());
    }
       cbox.setItems(FXCollections.observableArrayList(lcn)); 
       
    } 
     
     
    @FXML
      private void shopch () { 
         

         try {
         Categorie c =new Categorie();
         CategorieServices sc = new CategorieServices();
         c = sc.findCByName(cbox.getValue().toString());
         ProductServices sp = new ProductServices();
         List<Produit> lp = new ArrayList<>();
         lp = sp.ProdByCat(c.getId());
         System.out.println(lp);
            VB.getChildren().clear();
         for (Produit p:lp){
            System.out.println(p.getNom());
            Button btnall = new Button("all");
            all.getChildren().add(btnall);
            btnall.setOnAction(event ->{
            VB.getChildren().clear();
            Shop();});
         
            VBox h1 = new VBox();
            HBox h2 = new HBox();
                ScrollPane sl = new ScrollPane ();
                sl.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                sl.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                sl.setContent(VB);
            String lien = p.getPhoto();
            String lien2 = lien.replaceAll("//", "/");
            File file = new File(lien2);
            String localUrl = file.toURI().toURL().toString();
            Image image = new Image(localUrl);
            Rectangle rekt = new Rectangle(250,250);
            ImagePattern imagePattern =new ImagePattern(image);
            rekt.setFill(imagePattern);
            Text name = new Text(p.getNom());
            Text qte = new Text(""+p.getQte()+" Pieces Left");
            Text price = new Text("Price : "+p.getPrix());
            Text promo = new Text("Promo Price : "+p.getPrix_promo());
            Text description = new Text("Description : "+p.getDescription());
            String stylebt = "-fx-background-color: #FFFFFF;";
            String style = "-fx-background-color: #FFFFFF; -fx-padding: 5 ;";
            String stylU = "-fx-font-weight: bold; -fx-font-size: 20px;";
                String stylc = "-fx-font-weight: bold; -fx-font-size: 12px;";
                h1.setStyle(style);
                VB.setStyle(style);
                name.setStyle(stylU);
                price.setStyle(stylc);
                promo.setStyle(stylc);
                description.setStyle(stylc);
                qte.setStyle(stylc);
                h2.getChildren().add(rekt);
                h2.getChildren().add(h1);
                h1.getChildren().add(name);
                h1.getChildren().add(price);
                h1.getChildren().add(promo);
                h1.getChildren().add(qte);
                h1.getChildren().add(description);
                VB.getChildren().add(h2);
        }} catch (MalformedURLException ex) {
                
             }
         } 
     }   
        
    
  

       
            
        
      
     
      
     
     
     
     

