/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Promotion;
import Services.LgPromotionDao;
import Services.PromotionDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mariem
 */
public class EditformController implements Initializable {

    @FXML
    private JFXButton Confirm;
    @FXML
    private JFXDatePicker startdate;
    @FXML
    private JFXDatePicker enddate;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField rate;
    PromotionDao p=new PromotionDao();
    Promotion prom=new Promotion();
     LgPromotionDao srvlp=new LgPromotionDao();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rate.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
      public void handle(KeyEvent keyEvent) {
          if (!"0123456789".contains(keyEvent.getCharacter())) {
           keyEvent.consume();
           
          }
        }
      });
        // TODO
    }    
    private void clearField(){
        name.setText("");
        rate.setText("");
        startdate.setValue(null);
        enddate.setValue(null);
         
        } 
    public void load(int i) {
         Promotion promo=p.returnPromo(i);
         prom=promo;
//         name.setText(prom.getNom());
//         rate.setText(String.valueOf(prom.getTaux_reduction()));
//         startdate.setValue(LocalDate.now());
//         enddate.setValue(LocalDate.now());
        
        }

    @FXML
    private void editAction(MouseEvent event) {
//        if(name.getText().isEmpty()||rate.getText().isEmpty()){
//        Alert alert=new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information Dialogue");
//        alert.setHeaderText(null);
//        alert.setContentText("fields empty!!");
//        alert.showAndWait();}
        if (Integer.parseInt(rate.getText()) >100 ||Integer.parseInt(rate.getText())<0){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("Taux must be between 0 and 100!!");
        alert.showAndWait();
        
       }
     
       else if (java.sql.Date.valueOf(startdate.getValue()).after(java.sql.Date.valueOf(enddate.getValue()))) 
        {   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialogue");
            alert.setHeaderText(null);
            alert.setContentText("dates mismatch!!");
            alert.showAndWait(); 
          
          }
        else {
        Timestamp ts = new  Timestamp (java.sql.Date.valueOf(startdate.getValue()).getTime ());  
        Timestamp ts2= new  Timestamp (java.sql.Date.valueOf(enddate.getValue()).getTime ());  
        PromotionDao srvP=new PromotionDao();
        srvP.updatePromo(prom.getId(),name.getText(),Integer.parseInt(rate.getText()),ts.toString(),ts2.toString());
   
        clearField();
        
       }
        
    }

        
    
        
        
    
}
