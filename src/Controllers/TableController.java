/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import Models.Promotion;
import Services.PromotionDao;


/**
 *
 * @author Mariem
 */

public class TableController implements Initializable {

    @FXML
    private TableView<Promotion> table;
    @FXML
    private TableColumn<Promotion, Integer> col_id;
    @FXML
    private TableColumn<Promotion, String> col_nom;
    @FXML
    private TableColumn<Promotion, String> col_datedebut;
    @FXML
    private TableColumn<Promotion, String> col_datefin;
    @FXML
    private TableColumn<Promotion, String> col_taux;
    @FXML
    private TableColumn<Promotion, String> col_action;
    @FXML
    private TextField search_field;

    ObservableList<Promotion> obspromolist = FXCollections.observableArrayList();

    @FXML
    private AnchorPane root;

    /* private void handleButtonAction(ActionEvent event){
        try {
            AnchorPane pane1 = FXMLLoader.load(getClass().getResource("LignePromo.fxml"));
            root.getChildren().setAll(pane1);
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load_promo();
        search_promo();

    }

    @FXML
    private void affiche_like(MouseEvent event) {

    }

    private void load_promo() {
        PromotionDao srvP = new PromotionDao();
        List<Promotion> ls = srvP.listPromo();
        srvP.listPromo().stream().forEach((p) -> {
            obspromolist.add(p);
        });
        obspromolist.addAll(ls);

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_datedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        col_datefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        col_taux.setCellValueFactory(new PropertyValueFactory<>("taux_reduction"));

        Callback<TableColumn<Promotion, String>, TableCell<Promotion, String>> cellFactory;

        cellFactory = (TableColumn<Promotion, String> param) -> {
            final TableCell<Promotion, String> cell;
            cell = new TableCell<Promotion, String>() {
                @Override
                public void updateItem(String Item, boolean empty) {
                    super.updateItem(Item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Promotion p = getTableView().getItems().get(getIndex());//return p

                        final Button detailsButton = new Button("Add Product");

                        detailsButton.setOnAction(
                                event -> {
                                    Promotion pr = getTableView().getItems().get(getIndex());
                                    FXMLLoader loader = new FXMLLoader(
                                            getClass().getResource(
                                                    "/Views/LignePromo.fxml"));
                                    AnchorPane root1 = null;
                                    try {
                                        root1 = loader.load();
                                        root.getChildren().setAll(root1);

                                    } catch (IOException ex) {
                                        Logger.getLogger(TableController.class.getName())
                                        .log(Level.SEVERE, null, ex);
                                    }

                                    LignePromoController con = loader.getController();

                                    con.load(p.getId());
                                });
                        HBox pane = new HBox(detailsButton);
                        setGraphic(pane);
                    }
                }
            };

            return cell;
        };
        col_action.setCellFactory(cellFactory);

        table.setItems(obspromolist);
    }

    private void search_promo() {

        search_field.setOnKeyPressed(e -> {
            if (search_field.equals("")) {
                load_promo();
            } else {
                obspromolist.clear();
                PromotionDao srvP = new PromotionDao();
                srvP.findLikePromo(search_field.getText()).stream().forEach((p) -> {
                    obspromolist.add(p);
                });
                col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                col_datedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
                col_datefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
                col_taux.setCellValueFactory(new PropertyValueFactory<>("taux_reduction"));
                table.setItems(obspromolist);
            }
        });

    }

    @FXML
    private void add_promo(MouseEvent event) throws IOException {
        AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/Views/addPromo.fxml"));
        root.getChildren().setAll(pane1);

    }

}
