/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Models.Promotion;
import Services.PromotionDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mariem
 */
public class AddformController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField name;
    @FXML
    private RequiredFieldValidator validator;
    @FXML
    private JFXButton Confirm;
    @FXML
    private JFXTextField rate;
    @FXML
    private JFXDatePicker startdate;
    @FXML
    private JFXDatePicker enddate;

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
    }
 public void clearField(){
        name.setText("");
        rate.setText("");
        startdate.setValue(null);
        enddate.setValue(null); 
         
        }     

    @FXML
    private void confirmAction(MouseEvent event) {
        if(name.getText().isEmpty()||rate.getText().isEmpty()){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("fields empty!!");
        alert.showAndWait();}
        else if (Integer.parseInt(rate.getText()) >100 ||Integer.parseInt(rate.getText())<0){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("Taux must be between 0 and 100!!");
        alert.showAndWait();
       }
     
        else if (java.sql.Date.valueOf(startdate.getValue()).after(java.sql.Date.valueOf(enddate.getValue()))) {   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialogue");
            alert.setHeaderText(null);
            alert.setContentText("dates mismatch");
            alert.showAndWait(); 
          }
      else{
     
        PromotionDao srvP=new PromotionDao();
        Timestamp ts = new  Timestamp (java.sql.Date.valueOf(startdate.getValue()).getTime ());  
        Timestamp ts2= new  Timestamp (java.sql.Date.valueOf(enddate.getValue()).getTime ());  
        Promotion p=new Promotion (name.getText(),ts.toString(),ts2.toString(),Integer.parseInt(rate.getText()));
        srvP.create(p);
        //filllist();
//        BoxBlur blur= new BoxBlur(3,3,3);
//        JFXDialogLayout dialoglayout=new JFXDialogLayout();
//        JFXButton btn=new JFXButton("OK,I'll Check");
//        JFXDialog dialog=new JFXDialog(stackpane, pane, JFXDialog.DialogTransition.NONE);
//        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{
//        dialog.close();
//        });
//        dialoglayout.setBody(new Text("Date Mismatch!!"));
//        dialoglayout.setActions(btn);
//        dialog.show();
//        pane.setEffect(blur);
       
        clearField();
    
     }       
    }

    
}
