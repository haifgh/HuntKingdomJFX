/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utilities.Connexion;
import Models.Categorie;
import Models.Produit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arij
 */
public class CategoryUPDATEController implements Initializable {

    @FXML
    private JFXTextField nom_tf;

    
    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;
    private Connexion conn;
    
    private Categorie selectedS;

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
        
        // TODO
    }    


    public void TranslatingData(Categorie m) {
        selectedS = m;
       
        nom_tf.setText(selectedS.getNom());
 }
    

     
       
     
    
 
    public boolean UpdateCat(ActionEvent t) throws SQLException {
           conn = Connexion.getInstance();
        try {

            String sql = "update categorie set nom=? where id=?";

            pst = conn.getCnx().prepareStatement(sql);
            
            pst.setString(1, nom_tf.getText());
            pst.setInt(2, selectedS.getId() );
            
            
            pst.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Category UPDATED");
            alert.showAndWait();

            pst.close();
            CloseStageAutomaticly(t);

        } catch (SQLException el) {

            System.err.println(el);
        } catch (IOException ex) {
            Logger.getLogger(CategoryUPDATEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }
    

 
    

    
     private void CloseStageAutomaticly(Event e) throws IOException{
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
         stage.close();
    }
     
    
}
