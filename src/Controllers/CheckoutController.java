
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
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import Utilities.UserSession;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class CheckoutController implements Initializable {
    @FXML
    private AnchorPane             pane;
    @FXML
    private RequiredFieldValidator validator;
    @FXML
    private JFXTextField           addresse;
    @FXML
    private JFXTextField           name;
    @FXML
    private JFXTextField           tel;
    @FXML
    private JFXButton              Confirm;
    Parent                         root;

    @FXML
    private void confirmAction(ActionEvent event) throws IOException {
        if (addresse.validate() && tel.validate()) {
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
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(UserSession.getInstance().getUser().getUsername());
        name.setDisable(true);
        addresse.getValidators().add(validator);
        tel.getValidators().add(validator);
        addresse.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) {
                    addresse.validate();
                }
            });
        tel.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) {
                    tel.validate();
                }
            });
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
