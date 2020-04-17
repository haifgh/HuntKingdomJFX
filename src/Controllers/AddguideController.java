/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.Guide;
import Services.ServiceGuide;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author haifa 
 */
public class AddguideController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField categorie;
    @FXML
    private TextArea description;
    @FXML
    private TextArea lien;
    @FXML
    private ImageView img;
    FileChooser Fc = new FileChooser();
     Guide g=new Guide();
        ServiceGuide sg =new ServiceGuide();
    File selectedFile ;
    @FXML
    private AnchorPane rootpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void choiceFileAction(ActionEvent event) throws IOException {
        Fc.setTitle("Open fil Dialog");
        Fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        Fc.setInitialDirectory(new File("C:\\wamp\\www\\pidev"));
        selectedFile = Fc.showOpenDialog(null);
    
try {
               Image imge = new Image(selectedFile.toURI().toURL().toString());
               System.out.println(selectedFile.toURI().toURL().toString());
                this.img.setImage(imge);
           } catch (MalformedURLException ex) {
               Logger.getLogger(AddguideController.class.getName()).log(Level.SEVERE, null, ex);
           }
      
    
        
    }

    @FXML
    private void SaveGuide(ActionEvent event) throws SQLException{
        /*  String titre=this.titre.getText();
        
        String categorie=this.categorie.getText();
        
        
        String description=this.description.getText();
       
        String lien=this.lien.getText();
   
//sg.ajouterguide(titre, categorie, description, lien, g, selectedFile);*/
       

        Guide g=new Guide();

           // Groupe group = new Groupe(u,, String description, String photo,categorie);
            g.setPhoto(selectedFile.getName());
            g.setTitre(titre.getText());
            g.setCategorie(categorie.getText());
            g.setDescription(description.getText());
            g.setLien(lien.getText());
            sg.ajouterguide(g);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText("Annonce Added");
            alert.setContentText("Annonce successfully added");
            alert.showAndWait();
        
    }

    @FXML
    private void Backtolist(ActionEvent event) {
        try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("Showguide.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
        
    }
    
}
