
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Controllers;

import java.io.IOException;

import java.net.URL;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.util.Callback;

import com.jfoenix.controls.JFXButton;

import Models.Produit;

import Services.PanierServices;
import Services.ProductServices;

import Utilities.UserSession;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class CartController implements Initializable {
    Integer                                                  prix = 0;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> items;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> quantity;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> price;
    @FXML
    private Label                                            total;
    @FXML
    private TableView<Map.Entry<Integer, Integer>>           table;
    ProductServices                                          ps;
    @FXML
    private JFXButton                                        valider;
    @FXML
    private AnchorPane                                       cart;
    private HBox                                             main;
    PanierServices                                           pas;
    @FXML
    private TableColumn                                      actions;

    @FXML
    private void buyAction(ActionEvent event) throws IOException {
        main = (HBox) cart.getParent().lookup("#content");

        AnchorPane p = FXMLLoader.load(getClass().getResource("/Views/Checkout.fxml"));

        main.getChildren().setAll(p);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map m = UserSession.getInstance().getPanier();

        if (m.isEmpty()) {
            valider.setDisable(true);
        }

        ps   = new ProductServices();
        pas  = new PanierServices();
        prix = pas.getPrixPanier();
        items.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>,
                                               ObservableValue<String>>() {
                                      @Override
                                      public ObservableValue<String> call(
                                              TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> p) {

                                          // this callback returns property for just one cell, you can't use a loop here
                                          // for first column we use key
                                          Produit prod = ps.getP(p.getValue().getKey());

                                          return new SimpleStringProperty(prod.getNom());
                                      }
                                  });
        quantity.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>,
                                                  ObservableValue<String>>() {
                                         @Override
                                         public ObservableValue<String> call(
                                                 TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> p) {

                                             // this callback returns property for just one cell, you can't use a loop here
                                             // for first column we use key
                                             return new SimpleStringProperty(p.getValue().getValue().toString());
                                         }
                                     });
        price.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>,
                                               ObservableValue<String>>() {
                                      @Override
                                      public ObservableValue<String> call(
                                              TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> p) {
                                          Produit prod = ps.getP(p.getValue().getKey());
                                          Integer t    = prod.getPrix() * p.getValue().getValue();

                                          return new SimpleStringProperty(t.toString());
                                      }
                                  });

        Callback<TableColumn<Map.Entry<Integer, Integer>, String>, TableCell<Map.Entry<Integer, Integer>, String>> cellFactory;

        cellFactory = (TableColumn<Map.Entry<Integer, Integer>, String> param) -> {
                          final TableCell<Map.Entry<Integer, Integer>, String> cell =
                              new TableCell<Map.Entry<Integer, Integer>, String>() {
                              @Override
                              public void updateItem(String Item, boolean empty) {
                                  super.updateItem(Item, empty);

                                  if (empty) {
                                      setGraphic(null);
                                      setText(null);
                                  } else {

                                      // action button here
                                      final JFXButton rmButton = new JFXButton("remove");

                                      rmButton.setStyle("-fx-text-fill:white;-fx-background-color:red;");
                                      rmButton.setOnAction(
                                          event -> {
                                              pas.removeItem(getTableView().getItems().get(getIndex()).getKey());
                                              main = (HBox) cart.getParent().lookup("#main");

                                              AnchorPane p;

                                              try {
                                                  p =
                                                  FXMLLoader.load(getClass().getResource("/pidevjfx/GUI/Cart.fxml"));
                                                  main.getChildren().setAll(p);
                                              } catch (IOException ex) {
                                                  Logger.getLogger(CartController.class.getName())
                                                        .log(Level.SEVERE, null, ex);
                                              }
                                          });

                                      HBox pane = new HBox(rmButton);

                                      setGraphic(pane);
                                  }
                              }
                          };

                          return cell;
                      };
        actions.setCellFactory(cellFactory);

        ObservableList<Map.Entry<Integer, Integer>> oblist = FXCollections.observableArrayList(m.entrySet());

        System.out.println(prix);
        total.setText(prix.toString());
        table.setItems(oblist);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
