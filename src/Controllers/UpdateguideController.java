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
public class UpdateguideController implements Initializable {

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
private boolean validate() {
         if( ( description.getText().length() < 2 )|(categorie.getText().length() < 2) ){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Avertissement");
                alert.setHeaderText("Avertissement");
                alert.setContentText("Please complete more than 2 letters");
                alert.showAndWait();
    
            return false;
        }
        
        {
            
            return true;
            
        }
     }
    @FXML
    private void SaveGuide(ActionEvent event) throws SQLException{
        
        if ( !titre.getText().isEmpty()|!categorie.getText().isEmpty()
                  |!description.getText().isEmpty()|!lien.getText().isEmpty() && validate()   ){

        g.setTitre(titre.getText());

          g.setCategorie(categorie.getText());
    
            g.setDescription(description.getText());
            g.setLien(lien.getText());
           g.setPhoto(selectedFile.getName());
g.setId(g.getId());
        System.out.println(g.getId());       


     
        System.out.println(this.g.getId());
   sg.modifierguide(g);
   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText("Guide Updated");
            alert.setContentText("Guide successfully updated");
            alert.showAndWait();
                    }
              else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Dialog");
       
   alert.setContentText("Please fill all the fields !");
             alert.showAndWait();

}
        
}  

    void setData(String titre, String categorie,String description,String lien,String photo, int id) {
     g.setId(id);
       this.titre.setText(titre);
       this.categorie.setText(categorie);
        this.description.setText((description));
        this.lien.setText(lien);
       
    }
    

    @FXML
    private void Backtolist(ActionEvent event) {
        try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/Views/Showguide.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    }

    @FXML
    private void choiceFileAction(ActionEvent event) throws IOException{
        
          Fc.setTitle("Open fil Dialog");
        Fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        Fc.setInitialDirectory(new File("C:\\wamp64\\www\\pidev"));
        selectedFile = Fc.showOpenDialog(null);
    
try {
               Image imge = new Image(selectedFile.toURI().toURL().toString());
               System.out.println(selectedFile.toURI().toURL().toString());
                this.img.setImage(imge);
           } catch (MalformedURLException ex) {
               Logger.getLogger(AddguideController.class.getName()).log(Level.SEVERE, null, ex);
           }
      
    
    }
    
}
