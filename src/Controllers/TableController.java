/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import static Models.SMSsender.ACCOUNT_SID;
import static Models.SMSsender.AUTH_TOKEN;
import Services.PromotionDao;
import com.jfoenix.controls.JFXButton;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.scene.image.ImageView;


/**
 *
 * @author Mariem
 */

public class TableController implements Initializable {

    @FXML
    private TableView<Promotion> table;
    //@FXML
   // private TableColumn<Promotion, Integer> col_id;
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

    private void load_promo() {
        table.getItems().clear();
        PromotionDao srvP = new PromotionDao();
        srvP.listPromo().stream().forEach((p) -> {
            obspromolist.add(p);
        });
        

      //  col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
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
                        Promotion p = getTableView().getItems().get(getIndex());

                        final JFXButton details = new JFXButton();
                        details.setGraphic(new ImageView("icons/zoom.png"));
                                      details.getStyleClass().add("custom-button");
                                      details.setStyle("-fx-background-color:white");
                                      details.setOnAction(
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
                        
                         final Button edit = new Button("");
                         edit.getStyleClass().add("custom-button");
                                      edit.setStyle("-fx-background-color:white");
                         edit.setGraphic(new ImageView("icons/15586770221582004499-16.png"));
                         edit.setOnAction(e->{
                             Promotion pr = getTableView().getItems().get(getIndex());
                                    FXMLLoader loader = new FXMLLoader(
                                            getClass().getResource(
                                                    "/Views/editform.fxml"));
                                    AnchorPane root1 = null;
                                    try {
                                        root1 = loader.load();
                                        root.getChildren().setAll(root1);

                                    } catch (IOException ex) {
                                        Logger.getLogger(TableController.class.getName())
                                        .log(Level.SEVERE, null, ex);
                                    }

                                    EditformController con = loader.getController();

                                    con.load(p.getId());
                             
                             
                             
                         
                         
                         });
                           final Button delete = new Button("");
                           delete.getStyleClass().add("custom-button");
                                      delete.setStyle("-fx-background-color:white");
                           delete.setGraphic(new ImageView("icons/14458160321582004493-16.png"));
                           
                           delete.setOnAction(e->{
                           PromotionDao srvP=new PromotionDao();
                           srvP.deletePromo(p.getNom());
                           table.getItems().clear();
                           srvP.listPromo().stream().forEach((f) -> {
                            obspromolist.add(f);
                                  });
                           
                         });
       
                            final Button share = new Button("");
                            share.getStyleClass().add("custom-button");
                            share.setStyle("-fx-background-color:white");
                            share.setGraphic(new ImageView("icons/15307804281582004498-16.png"));
                             
                            share.setOnAction(e->{
                             Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                   Message message;
                       message = Message
                     .creator(new PhoneNumber("+21625351700"),
                             new PhoneNumber("+12014654580"),
                             "Visit our online boutique Hunt Kingdom and explore the new discounts!!")
                     .create();
                            });
                         
                        HBox pane = new HBox(details,edit,share,delete);
                        
                        
                        setGraphic(pane);
                        
                    }
                }
            };

            return cell;
        };
        col_action.setCellFactory(cellFactory);

        table.setItems(obspromolist);
       
       
//        tc_customer.setCellFactory(new Callback<TableColumn<TvAccounting, String>, TableCell<TvAccounting, String>>() {
//                   @Override
//                   public TableCell<TvAccounting, String> call(TableColumn<TvAccounting, String> p) {
//                       TableCell<TvAccounting, String> tc = new TableCell<TvAccounting, String>(){
//                           @Override
//                           public void updateItem(String item, boolean empty) {
//                               if (item != null){
//                                   setText(item);
//                               }
//                           }
//                       };
//                       tc.setAlignment(Pos.CENTER);
//                       return tc;
//                   }
//               });
//
//               tc_customer.setCellValueFactory(new PropertyValueFactory<TvAccounting, String>("Customer"));
//        
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
                //col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
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
      
// AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/Views/addform.fxml"));
//        root.getChildren().setAll(pane1);
    }
    @FXML
    private void affiche_like(MouseEvent event) {

    }

    @FXML
    private void plusAction(MouseEvent event) throws IOException{
          AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/Views/Addform.fxml"));
        root.getChildren().setAll(pane1);
        
    }

}
