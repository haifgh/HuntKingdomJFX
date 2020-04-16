/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Promotion;
import static Models.SMSsender.ACCOUNT_SID;
import static Models.SMSsender.AUTH_TOKEN;
import Services.PromotionDao;
import com.gluonhq.charm.glisten.control.TextField;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;



/**
 * FXML Controller class
 *
 * @author Mariem
 */
public class AddPromoController implements Initializable {

    @FXML
    private TextField name_field;
    @FXML
    private TextField tx_field;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    
    //private final DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    
    @FXML
    private ListView<Promotion> list_show;
    
    
    // ObservableList<Promotion> obspromolist2=FXCollections.observableArrayList();
    @FXML
    private AnchorPane root;
    
  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tx_field.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
      public void handle(KeyEvent keyEvent) {
          if (!"0123456789".contains(keyEvent.getCharacter())) {
           keyEvent.consume();
          }
        }
      });
        
          
    }
        private void clearField(){
        name_field.setText("");
        tx_field.setText("");
        date_debut.setValue(null);
        date_fin.setValue(null);   } 

    @FXML
    private void create_promo(MouseEvent event) {
     
       if(name_field.getText().isEmpty()||tx_field.getText().isEmpty()){
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("fields empty!!");
        alert.showAndWait();}
        else if (Integer.parseInt(tx_field.getText()) >100 ||Integer.parseInt(tx_field.getText())<0){
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("Taux must be between 0 and 100!!");
        alert.showAndWait();
       }
     
        else if (java.sql.Date.valueOf(date_debut.getValue()).after(java.sql.Date.valueOf(date_fin.getValue()))) {   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialogue");
            alert.setHeaderText(null);
            alert.setContentText("dates mismatch");
            alert.showAndWait(); 
          }
         
       
   
      else{
        list_show.getItems().clear();
        PromotionDao srvP=new PromotionDao();
        Timestamp ts = new  Timestamp (java.sql.Date.valueOf(date_debut.getValue()).getTime ());  
        Timestamp ts2= new  Timestamp (java.sql.Date.valueOf(date_fin.getValue()).getTime ());  
        Promotion p=new Promotion (name_field.getText(),ts.toString(),ts2.toString(),Integer.parseInt(tx_field.getText()));
        srvP.create(p);
        srvP.listPromo().stream().forEach((e) -> {list_show.getItems().add(e);});
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("promote has been created");
        alert.showAndWait();
        clearField();
        
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+21626936699"), 
                        new PhoneNumber("+19893421855"), 
                        "Visit our online boutique Hunt Kingdom and explore the new discounts!!")
                .create();

        System.out.println(message.getSid());
       }       
     }

    @FXML
    private void update_promote(MouseEvent event) {
        Timestamp ts = new  Timestamp (java.sql.Date.valueOf(date_debut.getValue()).getTime ());  
        Timestamp ts2= new  Timestamp (java.sql.Date.valueOf(date_fin.getValue()).getTime ());  
        list_show.getItems().clear();
        PromotionDao srvP=new PromotionDao();
        //Promotion p=new Promotion (name_field.getText(),Integer.parseInt(tx_field.getText()));
        srvP.updatePromo(name_field.getText(),Integer.parseInt(tx_field.getText()),ts.toString(),ts2.toString());
        srvP.listPromo().stream().forEach((e) -> {list_show.getItems().add(e);});
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("promote has been updated");
        alert.showAndWait();
        clearField();
        
        
        
    }

    @FXML
    private void delete_promo(MouseEvent event) {
         list_show.getItems().clear();
        PromotionDao srvP=new PromotionDao();
        srvP.deletePromo(name_field.getText());
        srvP.listPromo().stream().forEach((e) -> {list_show.getItems().add(e);});
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("promote has been deleted");
        alert.showAndWait();
        clearField();
        
        
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
