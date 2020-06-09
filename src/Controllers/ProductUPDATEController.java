/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utilities.Connexion;
import Models.Categorie;
import Models.Produit;
import Services.CategorieServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Interfaces.IserviceProduct;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author Etudiant
 */
public class ProductUPDATEController implements Initializable, IserviceProduct<ActionEvent> {
    @FXML
    private JFXButton Browse_btn;
    @FXML
    private JFXTextField pic_tf;
    @FXML
    private JFXTextField nom_tf;
    @FXML
    private JFXTextField qte_tf;
    @FXML
    private JFXTextField prix_tf;
    @FXML
    private JFXTextField description_tf;
    
    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;
    private Connexion conn;
    
    private Produit selectedS;
    private JFXTextField id_tf;
    @FXML
    private ImageView imgview;
     @FXML
    private ChoiceBox cbox;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private JFXButton confirm_btn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         conn = Connexion.getInstance();
          loadCat();
        // TODO
    }    

    @Override
    @FXML
    public void AddProduct(ActionEvent t) throws SQLException {
       
    }

    @Override
    public boolean DeleteProduct(ActionEvent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void TranslatingData(Produit m) {
        selectedS = m;
       // id_tf.setText(String.valueOf(selectedS.getId()));
        nom_tf.setText(selectedS.getNom());
        prix_tf.setText(String.valueOf(selectedS.getPrix()));
        //promo_tf.setText(String.valueOf(selectedS.getPrix_promo()));
        qte_tf.setText(String.valueOf(selectedS.getQte()));
        description_tf.setText(selectedS.getDescription());
        pic_tf.setText(selectedS.getPhoto());
        
        
        

    }
    
   private void loadCat() {
      
       CategorieServices sc = new CategorieServices();
       List<Categorie> lc = new ArrayList<>();
       lc= sc.readC();
         System.out.println(lc);
      List<String> lcn = new ArrayList<>();
    for (Categorie c:lc){
        lcn.add(c.getNom());
    }
       cbox.setItems(FXCollections.observableArrayList(lcn)); 
       
    } 
    
    @Override
    public boolean UpdateProduct(ActionEvent t) throws SQLException {
           conn = Connexion.getInstance();
        try {
            Categorie c = new Categorie();
            CategorieServices cs = new CategorieServices();
           
            String cat = cbox.getValue().toString();
            c=cs.findByName(cat);
            String sql = "update produit set categorie_id=?,nom=?,qte=?,prix=?,description=?,photo=? where id=?";

            pst = conn.getCnx().prepareStatement(sql);
            pst.setInt(1,c.getId());
            pst.setString(2, nom_tf.getText());
            pst.setInt(3, Integer.parseInt(qte_tf.getText()));
            pst.setInt(4, Integer.parseInt(prix_tf.getText()));
           // pst.setInt(4, Integer.parseInt(promo_tf.getText()));
            pst.setString(5, description_tf.getText());
            pst.setString(6, pic_tf.getText() );
            pst.setInt(7, selectedS.getId() );
            
            
            pst.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Product UPDATED");
            alert.showAndWait();

            pst.close();
            CloseStageAutomaticly(t);

        } catch (SQLException el) {

            System.err.println(el);
        } catch (IOException ex) {
            Logger.getLogger(ProductUPDATEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }
    

    @Override
    public void InitProduct() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void search(ActionEvent t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
              
    
    
    @FXML
    private void browse(ActionEvent event) {
        
        
               FileChooser filechooser = new FileChooser();
                
                filechooser.getExtensionFilters().addAll(
               // new ExtensionFilter("Text Files","*.txt"),
                new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
         //new ExtensionFilter("All Files","*."));
             
        
         File file = filechooser.showOpenDialog(Browse_btn.getScene().getWindow());
         if(file!=null){
            
            pic_tf.setText(file.getAbsolutePath());
            Image img=new Image(file.toURI().toString(),172,172,true,true);
             imgview.setImage(img);
         }

         
         
         }
    
     private void CloseStageAutomaticly(Event e) throws IOException{
        final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }
     
    
}
