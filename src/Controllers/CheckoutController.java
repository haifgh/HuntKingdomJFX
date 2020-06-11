
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Controllers;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Utilities.UserSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class CheckoutController implements Initializable {
    @FXML
    private AnchorPane   pane;
    @FXML
    private JFXTextField addresse;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXButton    Confirm;
    Parent               root;
    @FXML
    private Label        addresseErr;
    @FXML
    private Label        telErr;

    @FXML
    private void confirmAction(ActionEvent event) throws IOException {
        if (this.validateAddresse() && this.validateTel()) {
            Confirm.setDisable(true);

            FXMLLoader f = new FXMLLoader(getClass().getResource("/Views/FXMLDocument.fxml"));

            root = f.load();

            FXMLDocumentController fc = f.getController();

            fc.setAddress(addresse.getText());
            fc.setTel(tel.getText());

            Stage stage = new Stage();

            stage.setTitle("Pay");
            stage.setScene(new Scene(root, 1200, 400));
            stage.show();
            stage.setOnHiding(e->{
                HBox h = (HBox)pane.getParent().getParent().getParent().lookup("#content");
            System.out.println(h);
            FXMLLoader loader =
            new FXMLLoader(getClass().getResource("/Views/OrdersForUser.fxml"));
            AnchorPane r   = null;

            try {
            r = loader.load();
            } catch (IOException ex) {
            Logger.getLogger(OrdersForAdminController.class.getName())
                  .log(Level.SEVERE, null, ex);
            }

            h.getChildren().setAll(r);
            });
            
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(UserSession.getInstance().getUser().getUsername());
        name.setDisable(true);
        tel.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("([1-9][0-9]*)?"))
                                                           ? change
                                                           : null));
        addresse.textProperty().addListener((o, oldVal, newVal) -> {
                if (!oldVal.equals(newVal)) {
                    this.validateAddresse();
                }
            });
        tel.textProperty().addListener((o, oldVal, newVal) -> {
                if (!oldVal.equals(newVal)) {
                    this.validateTel();
                }
            });
    }

    private boolean validateAddresse() {
        if (addresse.getText().length() < 8) {
            addresseErr.setText("Address must be 8 characters or more");

            return false;
        } else {
            addresseErr.setText("");

            return true;
        }
    }

    private boolean validateTel() {
        if ((tel.getText().length() != 8)) {
            telErr.setText("Phone number must be 8 digits");

            return false;
        } else {
            telErr.setText("");

            return true;
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
