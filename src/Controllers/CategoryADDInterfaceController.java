/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Categorie;
import Services.CategorieServices;
import Utilities.Connexion;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Etudiant
 */
public class CategoryADDInterfaceController implements Initializable {

    @FXML
    private JFXTextField nom_tf;


    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;
    private Connexion conn;
    @FXML
    private JFXButton confirm_btn;
    @FXML
    private JFXButton cancel_btn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }

  
    @FXML
    public void AddCat(ActionEvent t) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        List<Categorie> lp= new ArrayList<>();
        CategorieServices cs = new CategorieServices();
        lp=cs.readC();
        List<String> lnc = new ArrayList<>();
        for (Categorie cc:lp){lnc.add(cc.getNom());}
        if (nom_tf.getText().length()==0){
   
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);
                    alert.setContentText("Plese fill the field");
                    alert.showAndWait();}
                else if (lnc.contains(nom_tf.getText())) {
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);
                    alert.setContentText("Category already exists");
                    alert.showAndWait();}
                
                else
        try {
            conn = Connexion.getInstance();
            
            String sql = "insert into categorie (nom) values (?)";
            
            
            String name = nom_tf.getText();

            
            try {
                pst = conn.getCnx().prepareStatement(sql);
         
                pst.setString(1, name);

                int i = pst.executeUpdate();
                if (i == 1) {
                    
                   // Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);
                    alert.setContentText("Category INSERTED");
                    alert.showAndWait();
                    
                }
                
            } catch (SQLException ex) {
                System.out.println(ex);
                
            }
            pst.close();
            CloseStageAutomaticly(t);
        } catch (IOException ex) {
            Logger.getLogger(CategoryADDInterfaceController.class.getName()).log(Level.SEVERE, null,ex);

        }
    }
     private void CloseStageAutomaticly(Event e) throws IOException{
        final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }
}
