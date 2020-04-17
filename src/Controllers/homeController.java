/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import Util.UserSession;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entities.Follow;
import javafx.fxml.FXMLLoader;
import entities.Post;
import entities.Jaime;
import entities.Reclamation;
import entities.User;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static jdk.nashorn.tools.ShellFunctions.input;
import services.ServicePost;
import services.ServiceJaime;
import services.UserServices;
import org.controlsfx.control.textfield.TextFields;
import services.ServiceFollow;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author ghofrane
 */
public class homeController implements Initializable {

    @FXML
    private Pane pnlsettings;
 
    @FXML
    private Button btnhome;
    @FXML
    private Button btnprofile;
    @FXML
    private Button btnclaims;
    @FXML
    private Button btnsettings;
    @FXML
    private Pane pnlhome;
    @FXML
    private Pane pnlprofile;
    @FXML
    private Pane pnlclaims;
    @FXML
    private Button btnpost;
    @FXML
    private JFXTextField testpost;
    @FXML
    private VBox VB;
    @FXML
    private TextField searchbar;
    @FXML
    private Button btnsearch;
    @FXML
    private Pane UserD;
    @FXML
    private VBox VBB;
    @FXML
    private HBox HE;
    @FXML
    private VBox VBH;
    @FXML
    private VBox VBR;
    @FXML
    private Label usernamee;
    @FXML
    private Label username;
    @FXML
    private Button logout;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
         UserServices su = new UserServices();
         User u = new User();
         u=su.findById(UserSession.getUser().getId());
        username.setText(u.getUsername());
        usernamee.setText(u.getUsername());
        ReadPost();  
         List<User> ls= new ArrayList<>();
        ls = su.findUsers() ;   
       TextFields.bindAutoCompletion(searchbar,ls );
       HomeFollow();
       ReadClaims();
        
        
    }    

    @FXML
    private void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnhome) {
            pnlhome.setStyle("-fx-background-color : #FFFF");
            pnlhome.toFront();
        }
        if (actionEvent.getSource() == btnprofile) {
            pnlprofile.setStyle("-fx-background-color : #FFFF");
            pnlprofile.toFront();
        }
        if (actionEvent.getSource() == btnclaims) {
           pnlclaims.setStyle("-fx-background-color : #FFFF");
           pnlclaims.toFront();
        }
        if(actionEvent.getSource()== btnsettings)
        {
            pnlsettings.setStyle("-fx-background-color : #FFFF");
            pnlsettings.toFront();
        }
    }
    
    

    @FXML
    private void hideButtonAction(Button D) {
        D.setVisible(false);
    }
     @FXML
    private void showButtonAction(Button D) {
        D.setVisible(true);
       
    }

    @FXML
    private void CreatePost(ActionEvent event) {
        ServicePost sp = new ServicePost();
        sp.ajouterPost(new Post(testpost.getText()));
        
        JOptionPane.showMessageDialog(null, "Post ajoutée !");
         VB.getChildren().clear();
        ReadPost();
        
        }
    private void DeletePost(int id ) {
        ServicePost sp = new ServicePost();
        sp.supprimerpost(id);
        
        JOptionPane.showMessageDialog(null, "Post supprimé !");
         VB.getChildren().clear();
          VBH.getChildren().clear();
        ReadPost();
        
        }
    
     private void UpdatePost(int id ) {
        ServicePost sp = new ServicePost();
        String m = JOptionPane.showInputDialog("contenu");
        sp.modifierpost(id, m);
        VB.getChildren().clear();
        ReadPost();
        
        }
         private void ReadPost()
        {
              ServicePost sp = new ServicePost();
              ServiceJaime st = new ServiceJaime();
             List<Post> le = new ArrayList<>();
             List<Jaime> lj = new ArrayList<>();
              UserServices su = new UserServices();
              User u = new User();
            
        //    lj = st.afficherjaime(UserSession.getUser().getId())
        le = sp.afficherpost(UserSession.getUser().getId());
        for (Post evv : le) {
            u=su.findById(evv.getUser_id());
          lj = st.reloadjaime(UserSession.getUser().getId(),evv.getId());   
         VBox h1 = new VBox();
         HBox h2 = new HBox();
               
          VB.setSpacing(5);
          h1.setSpacing(2);
         Text user = new Text(u.getUsername());
         Text date = new Text(evv.getDateCreation());
         Text contenu = new Text(evv.getContenu());
         Button btnsupp = new Button("Delete");
         Button btnmod = new Button("update");
         Button btnjaime = new Button("jaime");
         Button btnjaimep = new Button("jaime pas");
         Label nbjaime = new Label();
         nbjaime.setText(""+ st.nbrejaime(evv.getId()));
         h1.getChildren().add(user); 
         h1.getChildren().add(date);
         h1.getChildren().add(contenu);
         h2.getChildren().add(btnsupp);
         h2.getChildren().add(btnmod);
         h2.getChildren().add(btnjaime);
         h2.getChildren().add(nbjaime);
         h2.getChildren().add(btnjaimep);
         h1.getChildren().add(h2);
         btnjaimep.setVisible(false);
         String style = "-fx-background-color: #ecf0f1; -fx-background-radius:0.5em;";
         h1.setStyle(style);
         VB.getChildren().add(h1);
         btnsupp.setOnAction(event -> DeletePost(evv.getId()));
         btnmod.setOnAction(event -> UpdatePost(evv.getId()));
         btnjaime.setOnAction(event -> 
             likePost(evv.getId())
         );
         for (Jaime ejj : lj) {
               btnjaimep.setOnAction(event -> dislikePost(ejj.getId()));
          }
        }
        }
    private void likePost(int post) {
       
         ServiceJaime sp = new ServiceJaime();
         sp.ajouterjaime(new Jaime (post));
         VB.getChildren().clear();
         VBB.getChildren().clear();
         VBH.getChildren().clear();  
         HomeFollow();
         ReadPost();
       
    }
     private void likePostU(int post) {
        ServiceJaime sp = new ServiceJaime();
        sp.ajouterjaime(new Jaime (post));
        JOptionPane.showMessageDialog(null, "jaime ajouté !");
         VB.getChildren().clear();
         VBB.getChildren().clear();
         searchUser();
        
    }
     
          private void dislikePost(int id ) {
        ServiceJaime sp = new ServiceJaime();
        sp.supprimerjaime(id);
        JOptionPane.showMessageDialog(null, "jaime supp !");
         VB.getChildren().clear();
         VBB.getChildren().clear();
         ReadPost();
          VBH.getChildren().clear();
          HomeFollow();
        
    } 
               private void dislikePostU(int id ) {
        ServiceJaime sp = new ServiceJaime();
        sp.supprimerjaime(id);
        JOptionPane.showMessageDialog(null, "jaime supp !");
         VB.getChildren().clear();
         VBB.getChildren().clear();
         searchUser();
    }
          private void Follow(int followed) {
        ServiceFollow sf = new ServiceFollow();
        sf.follow(new Follow (followed));
        JOptionPane.showMessageDialog(null, "follow ajouté !");
         VBH.getChildren().clear();
         HomeFollow();
        
    }
           private void Unfollow(int id) {
        ServiceFollow sf = new ServiceFollow();
        sf.unfollow(id);
        JOptionPane.showMessageDialog(null, "follow supp !");
        VBH.getChildren().clear();
        HomeFollow();
      
        
    }
           
           
           
    private void Addclaim () throws IOException {
  
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/views/createR.fxml")); 
            Parent root = (Parent) Loader.load();
            //scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("claim ADD");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
    
 }
      
    private void HomeFollow()
        {  
            User u = new User();
            UserServices su = new UserServices();
            ServicePost sp = new ServicePost();
              ServiceFollow sf = new ServiceFollow();
              ServiceJaime st = new ServiceJaime();
             List<Post> le = new ArrayList<>();
             List<Jaime> lj = new ArrayList<>();
        //    lj = st.afficherjaime(UserSession.getUser().getId())
        le = sf.afficherpostfollow(UserSession.getUser().getId());
        for (Post evv : le) {
        u = su.findById(evv.getUser_id());
         lj = st.reloadjaime(UserSession.getUser().getId(),evv.getId());   
         HBox h1 = new HBox();
         VBH.setSpacing(20);
          h1.setSpacing(30);
         Text user = new Text(""+u.getUsername());
         Text date = new Text(evv.getDateCreation());
         Text contenu = new Text(evv.getContenu());
         Button btnjaime = new Button("jaime");
         Button btnjaimep = new Button("jaime pas");
         Label nbjaime = new Label();
         nbjaime.setText(""+ st.nbrejaime(evv.getId()));
         h1.getChildren().add(user); 
         h1.getChildren().add(date);
         h1.getChildren().add(contenu);
         h1.getChildren().add(btnjaime);
         h1.getChildren().add(nbjaime);
         h1.getChildren().add(btnjaimep);
         VBH.getChildren().add(h1);
         btnjaime.setOnAction(event -> likePost(evv.getId()));
         for (Jaime ejj : lj) {
               btnjaimep.setOnAction(event ->dislikePost(ejj.getId()));
          }
        }
        }

    @FXML
    private void searchUser() {
        VBB.getChildren().clear();
        HE.getChildren().clear();
        User u = new User();
        UserServices su = new UserServices();
        u = su.findByUsername(searchbar.getText());  
       
        UserD.setStyle("-fx-background-color : #FFFF");
        UserD.toFront();
         Text userN = new Text(u.getUsername());
         Button btnfollow = new Button("follow");
         Button btnunfollow = new Button("unfollow");
         Button btnclaim = new Button("claim");
         HBox h2 = new HBox();
         h2.getChildren().add(userN);
         h2.getChildren().add(btnfollow);
         h2.getChildren().add(btnunfollow);
         h2.getChildren().add(btnclaim);
         HE.getChildren().add(h2);
         ServicePost sp = new ServicePost();
         ServiceJaime st = new ServiceJaime();
         ServiceFollow sf = new ServiceFollow();
         List<Post> le = new ArrayList<>();
         List<Jaime> lj = new ArrayList<>();
         List<Follow> lf = new ArrayList<>();
        
        le = sp.afficherpost(u.getId());
        for (Post evv : le) {
         lj = st.reloadjaime(UserSession.getUser().getId(),evv.getId());   
          HBox h1 = new HBox();
          VBB.setSpacing(20);
          h1.setSpacing(30);
         Text user = new Text(u.getUsername());
         Text date = new Text(evv.getDateCreation());
         Text contenu = new Text(evv.getContenu());
         Button btnjaime = new Button("jaime");
         Button btnjaimep = new Button("jaime pas");
         Label nbjaime = new Label();
         nbjaime.setText(""+ st.nbrejaime(evv.getId()));
         h1.getChildren().add(user); 
         h1.getChildren().add(date);
         h1.getChildren().add(contenu);
         h1.getChildren().add(btnjaime);
         h1.getChildren().add(nbjaime);
         h1.getChildren().add(btnjaimep); 
         VBB.getChildren().add(h1); 
         btnjaime.setOnAction(event -> likePostU(evv.getId())); 
         for (Jaime ejj : lj) { btnjaimep.setOnAction(event -> dislikePostU(ejj.getId()));}   
    } 
         final int f =u.getId();
         lf = sf.reloadfollow(UserSession.getUser().getId(),u.getId());
          btnfollow.setOnAction(event -> Follow(f));
         for (Follow eff : lf) {    
          btnunfollow.setOnAction(event ->  Unfollow(eff.getId()));
          }
          btnclaim.setOnAction(event -> singleDialogInformation(f));      
         
}
       public void singleDialogInformation(int recl) {
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(0, 2, 2, 2));

        JTextField objet = new JTextField();
        JTextField contenu = new JTextField();

        pane.add(new JLabel("object of claim"));
        pane.add(objet);

        pane.add(new JLabel("body of claim"));
        pane.add(contenu);
        Component frame = null;

        int option = JOptionPane.showConfirmDialog(frame, pane, "Please fill all the fields", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {

           

            try {
               ServiceReclamation sr = new ServiceReclamation();
               sr.ajouterreclamation(new Reclamation(contenu.getText(), objet.getText(), recl));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }

        }
         VBR.getChildren().clear();
         ReadClaims();
    }
       
          private void ReadClaims()
        {
              ServiceReclamation sr = new ServiceReclamation();
            
             List<Reclamation> lr = new ArrayList<>();
             
        UserServices su = new UserServices();
         User u = new User();
         
        //    lj = st.afficherjaime(UserSession.getUser().getId())
        lr = sr.afficherReclamation(UserSession.getUser().getId());
        for (Reclamation evv : lr) {
         HBox h1 = new HBox();
          VBR.setSpacing(20);
          h1.setSpacing(30);
         u=su.findById(evv.getReclamer());
         Text objet = new Text(evv.getObject());
         Text contenu = new Text(evv.getContenu());
         Text reclamer = new Text(""+u.getUsername());
         Button btnsupp = new Button("Delete");
         Button btnmod = new Button("update");
         h1.getChildren().add(new Label("object of claim :"));
         h1.getChildren().add(objet);
         h1.getChildren().add(new Label("body of claim :"));
         h1.getChildren().add(contenu);
         h1.getChildren().add(new Label("target"));
         h1.getChildren().add(reclamer);
         h1.getChildren().add(btnsupp);
         h1.getChildren().add(btnmod);
         VBR.getChildren().add(h1);
         btnsupp.setOnAction(event -> DeleteR(evv.getId()));
         btnmod.setOnAction(event -> UpdateR(evv.getId()));
    
        }
        }
          
        private void DeleteR(int id ) {
        ServiceReclamation sr = new ServiceReclamation();
        sr.supprimerreclamation(id);
        JOptionPane.showMessageDialog(null, "Post supprimé !");
         VBR.getChildren().clear();
         ReadClaims();
        }
    
     private void UpdateR(int id ) {
        ServiceReclamation sr = new ServiceReclamation();
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(0, 2, 2, 2));

        JTextField objet = new JTextField();
        JTextField contenu = new JTextField();

        pane.add(new JLabel("object of claim"));
        pane.add(objet);

        pane.add(new JLabel("body of claim"));
        pane.add(contenu);
        Component frame = null;

        int option = JOptionPane.showConfirmDialog(frame, pane, "Please fill all the fields", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {

           

            try {
              
               sr.modifierreclamation(id ,contenu.getText(), objet.getText());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }

        }
        VBR.getChildren().clear();
       ReadClaims();
    }
        
        }   
     

     
