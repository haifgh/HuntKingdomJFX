/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utilities.Connexion;
import Models.Categorie;
import Models.Produit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Etudiant
 */
public class CategoryController implements Initializable {

    @FXML
    private TableView<Categorie> Table_categorie;
    @FXML
    private TableColumn<?, ?> nom_col;



    private ObservableList<Categorie> data;
    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;
    private Connexion conn;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton add_btn;
    @FXML
    private JFXButton delete_btn;
    @FXML
    private JFXButton update_btn;
    @FXML
    private JFXTextField tfsearchbyid;
    @FXML
    private JFXButton refresh_btn;
    @FXML
    private ImageView logo;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = Connexion.getInstance();
        try {
            InitCategory();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    public void AddCat(ActionEvent t) throws SQLException {

        try {
            stage = (Stage) add_btn.getScene().getWindow(); //yhell window ki tenzel aal bouton
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Views/CategoryADDInterface.fxml")); 
            Parent root = (Parent) Loader.load();
            //scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Category ADD");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    public boolean DeleteCat(ActionEvent t) throws SQLException {
     conn = Connexion.getInstance();

        int selectedIndex = Table_categorie.getSelectionModel().getSelectedIndex();
        Categorie m = (Categorie) Table_categorie.getSelectionModel().getSelectedItem();
        int tempItemTag = m.getId();

        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + m.getNom()+ " ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                conn = Connexion.getInstance();
                String query1 = "DELETE FROM categorie WHERE id=?";
                pst = conn.getCnx().prepareStatement(query1);
                pst.setInt(1, tempItemTag);
                pst.execute();
                Table_categorie.getItems().remove(selectedIndex);
            }
        }
        return true;
    
    }


    @FXML
    public boolean UpdateCat(ActionEvent t) throws SQLException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/CategoryUPDATE.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            
            Stage stage = new Stage();
            stage.setTitle("UPDATE");
            
            CategoryUPDATEController upc = fxmlLoader.getController();
            upc.TranslatingData(Table_categorie.getSelectionModel().getSelectedItem());
            stage.setScene(new Scene(root));
            stage.show();
       
        } catch (IOException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            return true;
    }


    public void InitCategory() throws SQLException {
        conn = Connexion.getInstance();

        try {

            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM categorie";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                conn = Connexion.getInstance();
                //data.addAll(data);
                data.add(new Categorie(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));


        Table_categorie.setItems(null);
        Table_categorie.setItems(data);
    }

    
    @FXML
    public void search(ActionEvent t) {
                ObservableList data = Table_categorie.getItems();
        tfsearchbyid.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                Table_categorie.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<Categorie> subentries = FXCollections.observableArrayList();

            long count = Table_categorie.getColumns().stream().count();
            for (int i = 0; i < Table_categorie.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + Table_categorie.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(Table_categorie.getItems().get(i));
                        break;
                    }
                }
            }
            Table_categorie.setItems(subentries);

        });
    }

    

    @FXML
    private void selectionne(MouseEvent event) throws SQLException, MalformedURLException {
               conn= Connexion.getInstance();
        int indexSelected = Table_categorie.getSelectionModel().getSelectedIndex();
        Categorie s = (Categorie) Table_categorie.getSelectionModel().getSelectedItem();
         conn= Connexion.getInstance();
        int id = s.getId();



        try {
            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM categorie where categorie.id='"+id+"'";
            rs = conn.getCnx().createStatement().executeQuery(sql);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void refresh(ActionEvent event) throws IOException {
        
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage s1 = new Stage();
        FXMLLoader L = new FXMLLoader();
        Pane root = L.load(getClass().getResource("/Views/CategoryInterface.fxml"));
        Scene c = new Scene(root);

        s1.setScene(c);
        s1.centerOnScreen();
        s1.show();

    }
  

}
