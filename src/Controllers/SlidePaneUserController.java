
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class SlidePaneUserController implements Initializable {
    @FXML
    private VBox userPane;

    @FXML
    private void goToCart(ActionEvent event) throws IOException {
        Parent p       = FXMLLoader.load(getClass().getResource("/Views/Cart.fxml"));
        HBox   content = (HBox) userPane.getParent().getParent().getParent().getParent().getScene().lookup("#content");

        content.getChildren().setAll(p);
    }

    @FXML
    private void goToOrders(ActionEvent event) throws IOException {
        Parent p       = FXMLLoader.load(getClass().getResource("/Views/OrdersForUser.fxml"));
        HBox   content = (HBox) userPane.getParent().getParent().getParent().getParent().getScene().lookup("#content");

        content.getChildren().setAll(p);
    }

    @FXML
    private void goToShop(ActionEvent event) throws IOException {
        Parent p       = FXMLLoader.load(getClass().getResource("/Views/Shop.fxml"));
        HBox   content = (HBox) userPane.getParent().getParent().getParent().getParent().getScene().lookup("#content");

        content.getChildren().setAll(p);
    }
@FXML
    private void goToGuide(ActionEvent event) throws IOException {
        Parent p       = FXMLLoader.load(getClass().getResource("/Views/Showguideuser.fxml"));
        HBox   content = (HBox) userPane.getParent().getParent().getParent().getParent().getScene().lookup("#content");

        content.getChildren().setAll(p);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
