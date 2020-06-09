/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utilities.Connexion;
import Models.Categorie;
import Services.CategorieServices;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Interfaces.IserviceProduct;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;

/**
 * FXML Controller class
 *
 * @author Etudiant
 */
public class ProductADDInterfaceController implements Initializable, IserviceProduct<ActionEvent> {

    @FXML
    private JFXTextField nom_tf;
    @FXML
    private JFXTextField qte_tf;
    @FXML
    private JFXTextField prix_tf;
    @FXML
    private JFXTextField promo_tf;
    @FXML
    private JFXTextField description_tf;

    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;
    private Connexion conn;
    @FXML
    private JFXTextField pic_tf;
    @FXML
    private JFXButton Browse_btn;
    @FXML
    private JFXButton confirm_btn;
    @FXML
    private JFXButton cancel_btn;
     @FXML
    private ChoiceBox cbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadCat();
    }

    @Override
    @FXML
    public void AddProduct(ActionEvent t) throws SQLException {
        try {
            conn = Connexion.getInstance();
            String url2 = pic_tf.getText().replaceAll("//", "////");
            String sql = "insert into produit (categorie_id,nom,qte,prix,description,photo) values (?,?,?,?,?,?)";
            
            //Integer.valueOf(Idm_tf.getText());
            String name = nom_tf.getText();
            int qte = Integer.valueOf(qte_tf.getText());
            int prix = Integer.valueOf(prix_tf.getText());
            //int prix_promo = Integer.valueOf(promo_tf.getText());
            String description = description_tf.getText();
            String cat = cbox.getValue().toString();
            String url = pic_tf.getText();
            Categorie c = new Categorie();
            CategorieServices cs = new CategorieServices();
            c=cs.findByName(cat);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (nom_tf.getText().length()==0 ||prix_tf.getText().length()==0||qte_tf.getText().length()==0||description_tf.getText().length()==0||pic_tf.getText().length()==0 )
                   
             { alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("Please fill all the fields");
                    alert.showAndWait();
             }
                else if (Integer.parseInt(prix_tf.getText())<1) {
                    //Dialog.show("Alert", "Price can't be null", new Command("OK"));
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("Price can't be null");
                    alert.showAndWait();    
                }
                                else if (Integer.parseInt(qte_tf.getText())<1) {
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("Quantity can't be null");
                    alert.showAndWait();
                }
                else
            
            try {
                pst = conn.getCnx().prepareStatement(sql);
                // pst.setInt(1, Integer.valueOf(Idm_tf.getText()));
                pst.setInt(1, c.getId());
                pst.setString(2, name);
                pst.setInt(3, qte);
                pst.setInt(4, prix);
               // pst.setInt(5, prix_promo);
                pst.setString(5, description);
                pst.setString(6, url);
                int i = pst.executeUpdate();
                if (i == 1) {
                    
                   // Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);
                    alert.setContentText("Product INSERTED");
                    alert.showAndWait();
                    
                }
                 System.out.println( cbox.getValue().toString());
            } catch (SQLException ex) {
                System.out.println(ex);
                
            }
            pst.close();
            CloseStageAutomaticly(t);
        } catch (IOException ex) {
            Logger.getLogger(ProductADDInterfaceController.class.getName()).log(Level.SEVERE, null,ex);

        }
    }

    @Override
    public boolean DeleteProduct(ActionEvent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean UpdateProduct(ActionEvent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        //new ExtensionFilter("All Files","*."));

        File file = filechooser.showOpenDialog(Browse_btn.getScene().getWindow());
        if (file != null) {

            pic_tf.setText(file.getAbsolutePath());
            Image img = new Image(file.toURI().toString());
        }
    }

    @FXML
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

       
     private void CloseStageAutomaticly(Event e) throws IOException{
        final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }
}
