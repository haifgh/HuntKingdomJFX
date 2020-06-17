
package Controllers;


import Models.Evenement;
import Models.User;
import Services.ServiceEvenement;
import Services.UserServices;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yosrio
 */
public class AfficahgeController implements Initializable {

    ObservableList list= FXCollections.observableArrayList();
    
    private Connection con;
    private ListView<String> eventList;
    public int i=0;
    ServiceEvenement s = new ServiceEvenement();
    Evenement E1 = new Evenement();
    private ObservableList<Evenement> data;
    @FXML
    private Button create;
    @FXML
    private Button delete;
    @FXML
    private TableView<Evenement> list1;
    
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, Date> date_debut;
    @FXML
    private TableColumn<Evenement, Date> date_fin;
    @FXML
    private TableColumn<Evenement, Integer> nb_places;
  
    @FXML
    private Label label;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDesc;
    @FXML
    private TextField ph;
    @FXML
    private TextField nbp;
    @FXML
    private DatePicker cldd;
    private TextField tfId;
    @FXML
    private DatePicker cldf;
    @FXML
    private Button browse_btn;
    @FXML
    private TextField tfsearch;
    @FXML
    private Button btnExportExcel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UserServices us = new UserServices();
        User         u  = new User("Yosri", "yosri");

        us.Authentification(u);
        
        data = FXCollections.observableArrayList();
       
        try {
            loadDataFromDatabasee();
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficahgeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         setsCllTableEvenement();
         setcellValue();  
    }    
    
     private void setcellValue() {
    
        list1.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
        E1 = list1.getItems().get(list1.getSelectionModel().getSelectedIndex());
        String id = Integer.toString(E1.getId());
        String places = Integer.toString(E1.getNbre_places());    
        tfNom.setText(E1.getNom());
        tfDesc.setText(E1.getDescription());
        nbp.setText(places);
        ph.setText(E1.getPhoto());
        
   });
         
    }
     
        private void setsCllTableEvenement() {
        
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        nb_places.setCellValueFactory(new PropertyValueFactory<>("nbre_places"));
        
        
        
    }
 
  private void loadDataFromDatabasee() throws SQLException {
      
       List<Evenement> lisabn =s.readAll();
        for(Evenement e:lisabn){
            System.out.println(e);
            data.add(e);
        }
           list1.setItems(data);
        FilteredList<Evenement> filteredData = new FilteredList<>(data, b -> true);
        
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (aff.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } 
                
                else {
                    return false; // Does not match.
                }
            });
        });   
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(list1.comparatorProperty());
        list1.setItems(sortedData);
        System.out.println(data);
    } 

    @FXML
    private void createAction(ActionEvent event) throws SQLException {
       if (tfNom.getText().length() == 0 || tfDesc.getText().length() == 0 ||  ph.getText().length() == 0||  nbp.getText().length() == 0||cldd.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("some field are empty !!");
            alert.showAndWait();  
         }    
         
         java.sql.Date date1 = java.sql.Date.valueOf(cldd.getValue());
         java.sql.Date date2 = java.sql.Date.valueOf(cldf.getValue());
         
         String url = ph.getText();
         
         
          if (date1.after(date2)) {   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("date fin doit etre superieur a date debut!!");
            alert.showAndWait(); 
          }
       
         if ( Integer.parseInt(nbp.getText()) <= 1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("nb places doit etre superieur a 0 !!");
            alert.showAndWait(); 
         }
       
          else{
            
              
        ServiceEvenement SE=new ServiceEvenement();
        Evenement ev = new Evenement();
        ev.setNom(tfNom.getText());
        ev.setDescription(tfDesc.getText());
        ev.setUser_id(1);
        ev.setDate_debut(date1);
        ev.setDate_fin(date2);
        ev.setValidite("");
        ev.setNbre_places(Integer.parseInt(nbp.getText()));
        ev.setPhoto(url);
       
        SE.ajouter(ev);
        data.clear();
            loadDataFromDatabasee();
       //SE.update(41, 2, "nog", "dec",date,date,"",10,"yosri");
       //SE.delete(ev);
       //System.out.println(SE.readAll());
       }
    }

    @FXML
    private void deleteAction(ActionEvent event) {
       try {
               s.delete(E1);
               data.clear();
               loadDataFromDatabasee();         
           } catch (SQLException ex) {
               Logger.getLogger(AfficahgeController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    @FXML
    private void browse(ActionEvent event) {
       Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une image");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(primary);
        String path = "C:\\wamp64\\www\\pidev\\web\\images\\";
        
        ph.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private boolean modifier(ActionEvent event)throws SQLException, IOException {
      
        int i = Integer.parseInt(nbp.getText());
      
        E1.setNom(nom.getText());
        E1.setDescription(description.getText());
        java.sql.Date date1 = java.sql.Date.valueOf(cldd.getValue());
        java.sql.Date date2 = java.sql.Date.valueOf(cldf.getValue());
        E1.setPhoto(ph.getText());
        
       
  
        
        s.update(E1.getId(),tfNom.getText(), tfDesc.getText(),date1,date2,i, ph.getText());
        data.clear();
        loadDataFromDatabasee();
             
            return true;
    }

    @FXML
    private void ExportToExcel(ActionEvent event) throws FileNotFoundException, IOException, SQLException
    {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < this.list1.getColumns().size(); j++) {
            row.createCell(j).setCellValue(list1.getColumns().get(j).getText());
        }
        for (int ii = 0; ii < list1.getItems().size(); ii++) {
            row = spreadsheet.createRow(ii + 1);
            for (int j = 0; j < list1.getColumns().size(); j++) {
                if (list1.getColumns().get(j).getCellData(ii) != null) {
                    row.createCell(j).setCellValue(list1.getColumns().get(j).getCellData(ii).toString());
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }

    
        Stage primary = new Stage();
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Choisir un emplacement pour enregistrer le fichier excel");
        File folder = dirChooser.showDialog(primary);
        if (folder != null) {
            StringBuilder builder = new StringBuilder();
            builder.append(folder.getAbsolutePath()).append("\\evenement.xls");
            try (FileOutputStream fileOut = new FileOutputStream(builder.toString())) {
                workbook.write(fileOut);
            }
        }
    }

    
    
    
  
    
    
}
