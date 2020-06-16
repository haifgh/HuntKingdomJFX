/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Categorie;
import Models.Produit;
import Services.CategorieServices;
import Services.ProductServices;
import Services.ProductServices1;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arij
 */
public class PieChartController implements Initializable {
    private Stage stage;
    @FXML
    private PieChart pieChart;
    @FXML
    private ImageView logo;
    @FXML
    private JFXButton add_btn;
    @FXML
    private JFXButton delete_btn;
    @FXML
    private JFXButton update_btn;
    @FXML
    private AnchorPane root;
    @FXML
    private VBox VB;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductServices ps = new ProductServices();
        CategorieServices cs = new CategorieServices();
        Categorie c1 = new Categorie();
        Categorie c2 = new Categorie();
        Categorie c3 = new Categorie();
        c1 = cs.findByName("Hunting");
        c2 = cs.findByName("Fishing");
        c3 = cs.findByName("Camping");
        int i1 = (ps.ProdByCat(c1.getId()).size());
        int i2 = (ps.ProdByCat(c2.getId()).size());
        int i3 = (ps.ProdByCat(c3.getId()).size());
        ObservableList<PieChart.Data> pieChartData=FXCollections.observableArrayList(
        new PieChart.Data("Hunting",i1),new PieChart.Data("Fishing",i2),new PieChart.Data("Camping",i3));
       pieChart.setData(pieChartData);
       pieChart.setLabelLineLength(10);
       pieChart.setLegendSide(Side.LEFT);
       
       final Label caption = new Label("");
       caption.setTextFill(Color.DARKORANGE);
       caption.setStyle("-fx-font: 24 arial;");
       for (final PieChart.Data data : pieChart.getData()) {
       data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(data.getPieValue()) + "%");
             }});}  
       
        List<Produit> lp = new ArrayList<>();
        ProductServices s = new ProductServices();
        try {
            lp =s.getPOut();
        } catch (SQLException ex) {
            Logger.getLogger(PieChartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PieChartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!lp.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);
                    alert.setContentText("Some products are out of stock !!");
                    alert.showAndWait();}
       
    
}
    @FXML
    public void loadProd(ActionEvent t) throws SQLException {

        try {
            stage = (Stage) add_btn.getScene().getWindow(); //yhell window ki tenzel aal bouton
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Views/ProductInterface.fxml")); 
            Parent root = (Parent) Loader.load();
            //scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Manage Products");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     @FXML
    public void loadCat(ActionEvent t) throws SQLException {

        try {
            stage = (Stage) update_btn.getScene().getWindow(); //yhell window ki tenzel aal bouton
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Views/CategoryInterface.fxml")); 
            Parent root = (Parent) Loader.load();
            //scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Manage Products");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     @FXML
    public void loadProdOut(ActionEvent t) throws SQLException {

        try {
            stage = (Stage) delete_btn.getScene().getWindow(); //yhell window ki tenzel aal bouton
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Views/POut.fxml")); 
            Parent root = (Parent) Loader.load();
            //scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Manage Products");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
