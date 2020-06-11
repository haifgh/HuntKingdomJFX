
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Controllers;

import java.io.IOException;

import java.net.URL;

import java.sql.Timestamp;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.util.Callback;

import com.jfoenix.controls.JFXButton;

import Models.Commande;

import Services.CommandeServices;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class OrdersForAdminController implements Initializable {
    @FXML
    private TableView<Commande>              orderstable;
    @FXML
    private TableColumn<Commande, Timestamp> date;
    @FXML
    private TableColumn<Commande, Timestamp> deliveredAt;
    @FXML
    private TableColumn<Commande, String>    status;
    @FXML
    private TableColumn<Commande, String>    price;
    @FXML
    private TableColumn                      actions;
    private CommandeServices                 cs;
    @FXML
    private AnchorPane                       parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cs = new CommandeServices();
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        deliveredAt.setCellValueFactory(new PropertyValueFactory<>("DateLivraison"));
        status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        

        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactory;
        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactory1;
        cellFactory1 = (TableColumn<Commande, String> param) -> {
                           final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
                               @Override
                               public void updateItem(String Item, boolean empty) {
                                   super.updateItem(Item, empty);

                                   if (empty) {
                                       setGraphic(null);
                                       setText(null);
                                   } else {
                                       Commande p = getTableView().getItems().get(getIndex());

                                       setText(p.getPrixTotal().toString()+" TND");
                                   }
                               }
                           };

                           return cell;
                       };
        cellFactory = (TableColumn<Commande, String> param) -> {
                          final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
                              @Override
                              public void updateItem(String Item, boolean empty) {
                                  super.updateItem(Item, empty);

                                  if (empty) {
                                      setGraphic(null);
                                      setText(null);
                                  } else {
                                      Commande c = getTableView().getItems().get(getIndex());

                                      // action button here
                                      final JFXButton setDeliveredButton = new JFXButton("Delivered");
                                      final JFXButton detailsButton      = new JFXButton("Details");

                                      if (c.getStatus().equals("Delivered")) {
                                          setDeliveredButton.setDisable(true);
                                      }

                                      detailsButton.getStyleClass().add("custom-button");
                                      detailsButton.setStyle("-fx-background-color:blue");
                                      setDeliveredButton.getStyleClass().add("custom-button");
                                      setDeliveredButton.getStyleClass().add(".jfx-button");
                                      setDeliveredButton.setOnAction(
                                          event -> {
                                              Commande ct = getTableView().getItems().get(getIndex());

                                              setDeliveredButton.setDisable(true);
                                              ct.setDateLivraison(new Timestamp(System.currentTimeMillis()));
                                              ct.setStatus("Delivered");
                                              cs.update(ct);

                                              HBox       h = (HBox) orderstable.getParent()
                                                                               .getParent()
                                                                               .lookup("#content");
                                              AnchorPane p;

                                              try {
                                                  p =
                                                  FXMLLoader.load(getClass().getResource("/Views/OrdersForAdmin.fxml"));
                                                  h.getChildren().setAll(p);
                                              } catch (IOException ex) {
                                                  Logger.getLogger(OrdersForAdminController.class.getName())
                                                        .log(Level.SEVERE, null, ex);
                                              }
                                          });
                                      detailsButton.setOnAction(
                                          event -> {
                                              Commande   ct     = getTableView().getItems().get(getIndex());
                                              FXMLLoader loader =
                                                  new FXMLLoader(getClass().getResource("/Views/OrderDetails.fxml"));
                                              AnchorPane root   = null;

                                              try {
                                                  root = loader.load();
                                              } catch (IOException ex) {
                                                  Logger.getLogger(OrdersForAdminController.class.getName())
                                                        .log(Level.SEVERE, null, ex);
                                              }

                                              OrderDetailsController con = loader.getController();

                                              con.load(ct.getId());

                                              HBox h = (HBox) orderstable.getParent().getParent().lookup("#content");

                                              h.getChildren().setAll(root);
                                          });

                                      HBox pane = new HBox(setDeliveredButton, detailsButton);

                                      setGraphic(pane);
                                  }
                              }
                          };

                          return cell;
                      };
        actions.setCellFactory(cellFactory);
        price.setCellFactory(cellFactory1);
        orderstable.setItems(cs.findAll());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
