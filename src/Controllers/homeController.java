/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import Util.UserSession;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.paint.Color;
import entities.Follow;
import javafx.fxml.FXMLLoader;
import entities.Post;
import entities.Jaime;
import entities.Reclamation;
import entities.User;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Pair;
import javax.mail.MessagingException;
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
    private Label usernamee;
    @FXML
    private Button logout;
    @FXML
    private Pane userinfo;
    @FXML
    private Label follower;
    @FXML
    private Label following;
    @FXML
    private VBox lisf;
    
    @FXML
    private Pane panesl;
    @FXML
    private Pane pnlst;
    @FXML
    private Pane pnlcl;
    @FXML
    private Pane pnlus;
    @FXML
    private ImageView userimg;
    @FXML
    private Pane VBS;
    @FXML
    private Label following1;
    FileChooser Fc = new FileChooser();
    
    File selectedFile ;
    @FXML
    private ImageView changeimg;
    @FXML
    private AnchorPane anachropane;
    @FXML
    private Button save;
    @FXML
    private Pane VBU;
    @FXML
    private VBox userimage;
    @FXML
    private Pane usernom;
    @FXML
    private Pane barimg;
    @FXML
    private Pane barname;
    @FXML
    private VBox VBUP;
    @FXML
    private JFXTextField susername;
    @FXML
    private JFXTextField sphone;
    @FXML
    private JFXTextField sadd;
    @FXML
    private JFXTextField smail;
    @FXML
    private Button browse;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         setbar();
           UserServices su = new UserServices();
         ReadPost();  
         List<User> ls= new ArrayList<>();
         ls = su.findUsers() ;   
         TextFields.bindAutoCompletion(searchbar,ls );
         HomeFollow();
         ReadClaims();
        ServiceFollow sf = new ServiceFollow();
        List<Follow> lf= new ArrayList<>();
        lf = sf.follower(UserSession.getUser().getId());
        follower.setText(""+lf.size());
        List<Follow> lfd= new ArrayList<>();
        lfd=sf.following(UserSession.getUser().getId());
        following.setText(""+lfd.size());
         for (Follow eff : lfd) {
         User user= su.findById(eff.getFollowed());
            
             HBox h1 = new HBox();
             Text fol = new Text(user.getUsername());
             Circle circle = new Circle();
         circle.setCenterX(40.0f); 
         circle.setCenterY(10.0f); 
         circle.setRadius(15.0f); 
         Image userf = new Image("http://localhost/pidev/web/images/"+user.getPhoto());
         circle.setFill(new ImagePattern(userf));
             h1.getChildren().add(circle);
             h1.getChildren().add(fol);
             lisf.getChildren().add(h1);
              
             
         }
        
        
    }    

    @FXML
    private void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnhome) {
            pnlhome.setStyle("-fx-background-color : #F8F8FA");
            pnlhome.toFront();
        }
        if (actionEvent.getSource() == btnprofile) {
            pnlprofile.setStyle("-fx-background-color : #F8F8FA");
            pnlprofile.toFront();
        }
        if (actionEvent.getSource() == btnclaims) {
           pnlclaims.setStyle("-fx-background-color : #F8F8FA");
           pnlclaims.toFront();
        }
        if(actionEvent.getSource()== btnsettings)
        {
            pnlsettings.setStyle("-fx-background-color : #F8F8FA");
            pnlsettings.toFront();
        }
    }
    
    private void setbar() {
      
         UserServices su = new UserServices();
         User u = new User();
         u=su.findById(UserSession.getUser().getId());
         Label name = new Label(u.getUsername());
         Label namep = new Label(u.getUsername());
          Circle circle = new Circle();
         circle.setCenterX(50.0f); 
         circle.setCenterY(15.0f); 
         circle.setRadius(20.0f); 
         Image user = new Image("http://localhost/pidev/web/images/"+u.getPhoto());
         circle.setFill(new ImagePattern(user));
        Circle circlet = new Circle();
         circlet.setCenterX(150.0f); 
         circlet.setCenterY(60.0f); 
         circlet.setRadius(60.0f); 
           circlet.setFill(new ImagePattern(user));
            String stylU = "-fx-font-weight: bold; -fx-font-size: 20px;";
         name.setStyle(stylU);
         namep.setStyle(stylU);
         userimage.getChildren().add(circlet);
         usernom.getChildren().add(namep);
         barimg.getChildren().add(circle);
         barname.getChildren().add(name);
    
        }

    @FXML
    private void CreatePost(ActionEvent event) {
        ServicePost sp = new ServicePost();
        if (testpost.getText().length() == 0){
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("Please fill the field");
                    alert.showAndWait(); 
        }
        else {sp.ajouterPost(new Post(testpost.getText()));
         VB.getChildren().clear();
        ReadPost();}
        
        }
    private void DeletePost(int id ) {
        ServicePost sp = new ServicePost();
        sp.supprimerpost(id);
    
         VB.getChildren().clear();
          VBH.getChildren().clear();
        ReadPost();
        
        }
    
  /*  public void UpdateA (int id) throws IOException  {
    ServicePost sp = new ServicePost();
    String m = contenu.getText();
    sp.modifierpost(id, m);
    VB.getChildren().clear();
     VBB.getChildren().clear();
        ReadPost();
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/homepage.fxml"));
     homeController cont = loader.getController();
     cont.ReadPost();
        System.out.println("ok");
    }*/
    
     private void UpdatePost(int id )   {  
    
      
        ServicePost sp = new ServicePost();
        String m = JOptionPane.showInputDialog("contenu");
        if (m.length()== 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("Please fill the field");
                    alert.showAndWait(); 
        }else
        {sp.modifierpost(id, m);}
        VB.getChildren().clear();
        ReadPost();
           
        
        
        }
         public void ReadPost()
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
         HBox h3= new HBox();  
          VB.setSpacing(5);
          h1.setSpacing(2);
           h3.setSpacing(3);
         ScrollPane sl = new ScrollPane ();
         sl.setVbarPolicy(ScrollBarPolicy.ALWAYS);
         sl.setHbarPolicy(ScrollBarPolicy.NEVER);
         sl.setContent(VB);
         String stylep ="-fx-background-color:none;" ;          
         sl.setStyle(stylep);
         pnlst.getChildren().add(sl);
         Circle circle = new Circle();
         circle.setCenterX(50.0f); 
         circle.setCenterY(15.0f); 
         circle.setRadius(20.0f); 
         Image userp = new Image("http://localhost/pidev/web/images/"+u.getPhoto());
         circle.setFill(new ImagePattern(userp));
         
         Text user = new Text(u.getUsername());
         Text date = new Text(evv.getDateCreation());
         Text contenu = new Text(evv.getContenu());
         Button btnsupp = new Button();
         Button btnmod = new Button();
         Button btnjaime = new Button();
         Button btnjaimep = new Button();
         Label nbjaime = new Label();
         nbjaime.setText(""+ st.nbrejaime(evv.getId()));
         StackPane ct= new StackPane ();
         HBox ht = new HBox();
         ct.getChildren().add(ht); 
         ct.setMargin(ht, new Insets(0, 0, 0, 450));
         ht.getChildren().add(btnsupp);  
         ht.getChildren().add(btnmod); 
       //  ht.getChildren().add(h1); 
      //  h1.getChildren().add(ct);
         h3.getChildren().add(circle); 
         h3.getChildren().add(user); 
         h3.getChildren().add(ct); 
         h1.getChildren().add(h3);
         h1.getChildren().add(date);
         h1.getChildren().add(contenu);
         h2.getChildren().add(nbjaime);
         h2.getChildren().add(btnjaime);
         h2.getChildren().add(btnjaimep);
         h1.getChildren().add(h2);
         ImageView likeimga = new ImageView(getClass().getResource("/icons/likem.png").toString());
         ImageView likeimg = new ImageView(getClass().getResource("/icons/like.png").toString());
         ImageView dislikeimga = new ImageView(getClass().getResource("/icons/dislikem.png").toString());
         ImageView dislikeimg = new ImageView(getClass().getResource("/icons/dislike.png").toString());
         ImageView editimg = new ImageView(getClass().getResource("/icons/edit.png").toString());
         ImageView deleteimg = new ImageView(getClass().getResource("/icons/delete.png").toString());
         String stylebt = "-fx-background-color: #FFFFFF;";
         String style = "-fx-background-color: #FFFFFF; -fx-padding: 20 ;";
         String stylU = "-fx-font-weight: bold; -fx-font-size: 18px;";
        
         btnmod.setGraphic(editimg);
         btnsupp.setGraphic(deleteimg);
         h1.setStyle(style);
         user.setStyle(stylU);
         nbjaime.setStyle(stylU);
         btnjaime.setStyle(stylebt);
         btnjaimep.setStyle(stylebt);
         btnmod.setStyle(stylebt);
         btnsupp.setStyle(stylebt);
         VB.getChildren().add(h1);
         btnsupp.setOnAction(event -> DeletePost(evv.getId()));
         btnmod.setOnAction(event -> {UpdatePost(evv.getId());
          VB.getChildren().clear();
           ReadPost();
         });
         btnjaimep.setGraphic(likeimga);
         btnjaime.setGraphic(likeimg);
         if (lj.size()!=0){
       /*  btnjaime.setGraphic(likeimga);
         btnjaime.setDisable(true);
         btnjaimep.setGraphic(dislikeimg);
         btnjaimep.setDisable(false);*/
         h2.getChildren().remove(btnjaime);
       
         for (Jaime ejj : lj) {
               btnjaimep.setOnAction(event -> dislikePost(ejj.getId()));
          }
         }
         
         else if (lj.size()==0){
      /*   btnjaime.setGraphic(likeimg);
         btnjaime.setDisable(false);
         btnjaimep.setGraphic(dislikeimga);
         btnjaimep.setDisable(true);*/
         btnjaime.setOnAction(event ->likePost(evv.getId()));
         h2.getChildren().remove(btnjaimep);
      
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
      
         VB.getChildren().clear();
         VBB.getChildren().clear();
         searchUser();       
    }
     
          private void dislikePost(int id ) {
        ServiceJaime sp = new ServiceJaime();
        sp.supprimerjaime(id);
         VB.getChildren().clear();
         VBB.getChildren().clear();
         ReadPost();
          VBH.getChildren().clear();
          HomeFollow();
        
    } 
               private void dislikePostU(int id ) {
        ServiceJaime sp = new ServiceJaime();
        sp.supprimerjaime(id);
      
         VB.getChildren().clear();
         VBB.getChildren().clear();
         searchUser();
    }
          private void Follow(int followed) {
        ServiceFollow sf = new ServiceFollow();
        sf.follow(new Follow (followed));
      
         VBH.getChildren().clear();
          VBB.getChildren().clear();
         HomeFollow();
          searchUser();
        
    }
           private void Unfollow(int id) {
        ServiceFollow sf = new ServiceFollow();
        sf.unfollow(id);
       VBB.getChildren().clear();
        VBH.getChildren().clear();
        HomeFollow();
         searchUser();
      
        
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
         VBox h1 = new VBox();
         HBox h2 = new HBox();  
          HBox h3 = new HBox();  
         ScrollPane sl = new ScrollPane ();
         sl.setVbarPolicy(ScrollBarPolicy.ALWAYS);
         sl.setHbarPolicy(ScrollBarPolicy.NEVER);
         sl.setContent(VBH);
         String stylep ="-fx-background-color:none;" ;          
         sl.setStyle(stylep); panesl.getChildren().add(sl);
         VBH.setSpacing(5);
         h1.setSpacing(2);
         h1.setSpacing(3);
         Circle circle = new Circle();
         circle.setCenterX(50.0f); 
         circle.setCenterY(15.0f); 
         circle.setRadius(20.0f); 
         Image userp = new Image("http://localhost/pidev/web/images/"+ u.getPhoto());
         circle.setFill(new ImagePattern(userp));
         Text user = new Text(u.getUsername());
         Text date = new Text(evv.getDateCreation());
         Text contenu = new Text(evv.getContenu());
         Button btnjaime = new Button();
         Button btnjaimep = new Button();
         Label nbjaime = new Label();
         nbjaime.setText(""+ st.nbrejaime(evv.getId()));
         h3.getChildren().add(circle);
         h3.getChildren().add(user);
         h1.getChildren().add(h3);
         h1.getChildren().add(date);
         h1.getChildren().add(contenu);
         h2.getChildren().add(nbjaime);
         h2.getChildren().add(btnjaime);
         h2.getChildren().add(btnjaimep);
         h1.getChildren().add(h2);
         ImageView likeimga = new ImageView(getClass().getResource("/icons/likem.png").toString());
         ImageView likeimg = new ImageView(getClass().getResource("/icons/like.png").toString());
         String stylebt = "-fx-background-color: #FFFFFF;";
         String style = "-fx-background-color: #FFFFFF; -fx-padding: 5 ;";
         String stylU = "-fx-font-weight: bold; -fx-font-size: 20px;";
         h1.setStyle(style);
         user.setStyle(stylU);
         nbjaime.setStyle(stylU);
         btnjaime.setStyle(stylebt);
         btnjaimep.setStyle(stylebt);
        
         VBH.getChildren().add(h1);
         btnjaimep.setGraphic(likeimga);
          btnjaime.setGraphic(likeimg);
         if (lj.size()!=0){
      
         h2.getChildren().remove(btnjaime);
       
         for (Jaime ejj : lj) {
               btnjaimep.setOnAction(event -> dislikePost(ejj.getId()));
          }
         }
         
         else if (lj.size()==0){
      
         btnjaime.setOnAction(event ->likePost(evv.getId()));
         h2.getChildren().remove(btnjaimep);
      
         }
        }
        }

    @FXML
    private void searchUser() {
         if(searchbar.getText().length()==0){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("Please fill the field");
                    alert.showAndWait(); 
         } 
         else{
       VBB.getChildren().clear();
        HE.getChildren().clear();
        User u = new User();
        UserServices su = new UserServices();
        u = su.findByUsername(searchbar.getText());  
        if(u == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("user doesn't existe ... try again ");
                    alert.showAndWait(); 
        } 
        else {
            if(u.getId()== UserSession.getUser().getId()){
                pnlprofile.toFront();
                pnlprofile.setStyle("-fx-background-color : #F8F8FA");
            }else
            {
        UserD.setStyle("-fx-background-color : #F8F8FA");
        UserD.toFront();
        Circle circle = new Circle();
         circle.setCenterX(40.0f); 
         circle.setCenterY(40.0f); 
         circle.setRadius(40.0f); 
         Image userp = new Image("http://localhost/pidev/web/images/"+u.getPhoto());
         circle.setFill(new ImagePattern(userp));
         Text userN = new Text(u.getUsername());
         Button btnfollow = new Button("follow");
         Button btnunfollow = new Button("unfollow");
         Button btnclaim = new Button("claim");
         VBox h2 = new VBox();   
         h2.getChildren().add(circle);
         h2.getChildren().add(userN);
         HBox ht = new HBox();
          StackPane ct= new StackPane ();
           
         ct.getChildren().add(ht); 
         ht.setMargin(btnclaim, new Insets(0, 0, 0, 20));
         ht.getChildren().add(btnfollow);  
         ht.getChildren().add(btnunfollow);
         ht.getChildren().add(btnclaim);
         h2.getChildren().add(ct); 

         
         HE.getChildren().add(h2);
         
         ServicePost sp = new ServicePost();
         ServiceJaime st = new ServiceJaime();
         ServiceFollow sf = new ServiceFollow();
         List<Post> le = new ArrayList<>();
         List<Jaime> lj = new ArrayList<>();
         List<Follow> lf = new ArrayList<>();
        
         ScrollPane sl = new ScrollPane ();
         sl.setVbarPolicy(ScrollBarPolicy.ALWAYS);
         sl.setHbarPolicy(ScrollBarPolicy.NEVER);
         sl.setContent(VBB);
         String stylep ="-fx-background-color:none;" ;          
         sl.setStyle(stylep);
         pnlus.getChildren().add(sl);
        le = sp.afficherpost(u.getId());
        for (Post evv : le) {
         lj = st.reloadjaime(UserSession.getUser().getId(),evv.getId());   
         VBox h1 = new VBox();
         HBox h3 = new HBox();
         VBB.setSpacing(5);
         h1.setSpacing(2);
         Circle circlepost = new Circle();
         circlepost.setCenterX(50.0f); 
         circlepost.setCenterY(15.0f); 
         circlepost.setRadius(20.0f); 
         Image userpost = new Image("http://localhost/pidev/web/images/"+u.getPhoto());
         circlepost.setFill(new ImagePattern(userpost));
         Text user = new Text(u.getUsername());
         Text date = new Text(evv.getDateCreation());
         Text contenu = new Text(evv.getContenu());
         Button btnjaime = new Button();
         Button btnjaimep = new Button();
         Label nbjaime = new Label();
         nbjaime.setText(""+ st.nbrejaime(evv.getId()));
         h1.getChildren().add(circlepost); 
         h1.getChildren().add(user); 
         h1.getChildren().add(date);
         h1.getChildren().add(contenu);
         h3.getChildren().add(nbjaime);
         h3.getChildren().add(btnjaime);
         h3.getChildren().add(btnjaimep);
         h1.getChildren().add(h3);
         VBB.getChildren().add(h1); 
         ImageView likeimga = new ImageView(getClass().getResource("/icons/likem.png").toString());
         ImageView likeimg = new ImageView(getClass().getResource("/icons/like.png").toString());
          btnjaimep.setGraphic(likeimga);
          btnjaime.setGraphic(likeimg);
          String stylebt = "-fx-background-color: #FFFFFF;";
         String style = "-fx-background-color: #FFFFFF; -fx-padding: 5 ;";
         String stylU = "-fx-font-weight: bold; -fx-font-size: 20px;";
         String stylebtF = "-fx-background-color: #00BFFF;-fx-text-fill: #FFFFFF;-fx-font-weight: bold;-fx-margin: 5 ";
         String stylebtUF = "-fx-background-color: #FF0000;-fx-text-fill: #FFFFFF;-fx-font-weight: bold;-fx-margin: 5 ";
         String stylebtC = "-fx-background-color: #32CD32;-fx-text-fill: #FFFFFF;-fx-font-weight: bold;";
         h1.setStyle(style);
         user.setStyle(stylU);
         nbjaime.setStyle(stylU);
         btnjaime.setStyle(stylebt);
         btnjaimep.setStyle(stylebt);
         btnfollow.setStyle(stylebtF);
         btnunfollow.setStyle(stylebtUF);
         btnclaim.setStyle(stylebtC); 
          if (lj.size()!=0){
      
         h3.getChildren().remove(btnjaime);
       
         for (Jaime ejj : lj) {
               btnjaimep.setOnAction(event -> dislikePostU(ejj.getId()));
          }
         }
         
         else if (lj.size()==0){
         btnjaime.setOnAction(event ->likePostU(evv.getId()));
         h3.getChildren().remove(btnjaimep);
      
         }
      
    } 
         final int f =u.getId();
         lf = sf.reloadfollow(UserSession.getUser().getId(),u.getId());
           if (lf.size()!=0){
      
         ht.getChildren().remove(btnfollow);
       
        for (Follow eff : lf) {    
          btnunfollow.setOnAction(event ->  Unfollow(eff.getId()));
          }
         }
         
         else if (lj.size()==0){
          btnfollow.setOnAction(event -> Follow(f));
         ht.getChildren().remove(btnunfollow);
      
         }
      
          btnclaim.setOnAction(event -> singleDialogInformation(f));      
         }
         }
}
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
            if(contenu.getText().length()==0 || objet.getText().length()==0)
            {
               
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("Please fill the field");
                    alert.showAndWait(); 
            }
            }else {
           
               ServiceReclamation sr = new ServiceReclamation();
               sr.ajouterreclamation(new Reclamation(contenu.getText(), objet.getText(), recl));
           
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
        lr = sr.afficherReclamation(UserSession.getUser().getId());
        for (Reclamation evv : lr) {
            VBox h1 = new VBox();
            HBox h2 = new HBox();
            HBox h3 = new HBox();
            HBox h4 = new HBox();
            VBR.setSpacing(5);
            h1.setSpacing(2);
            h4.setSpacing(3);
         ScrollPane sl = new ScrollPane ();
         sl.setVbarPolicy(ScrollBarPolicy.ALWAYS);
         sl.setHbarPolicy(ScrollBarPolicy.NEVER);
         sl.setContent(VBR);
         String stylep ="-fx-background-color:none;" ;          
         sl.setStyle(stylep);
         pnlcl.getChildren().add(sl);    
         u=su.findById(evv.getReclamer());
         Text objet = new Text(evv.getObject());
         Text contenu = new Text(evv.getContenu());
         Text reclamer = new Text(""+u.getUsername());
         Text objc = new Text("object of claim :");   
         Text bodyc = new Text("body of claim :"); 
         Circle circlepost = new Circle();
         circlepost.setCenterX(50.0f); 
         circlepost.setCenterY(15.0f); 
         circlepost.setRadius(20.0f); 
         Image userpost = new Image("http://localhost/pidev/web/images/"+u.getPhoto());
         circlepost.setFill(new ImagePattern(userpost));
         Button btnsupp = new Button();
         ImageView deleteimg = new ImageView(getClass().getResource("/icons/delete.png").toString());
         btnsupp.setGraphic(deleteimg);
         StackPane ct= new StackPane ();
         HBox ht = new HBox();
         ct.getChildren().add(ht); 
         ct.setMargin(ht, new Insets(0, 0, 0, 800));
         ht.getChildren().add(btnsupp);  
         h4.getChildren().add(circlepost); 
         h4.getChildren().add(reclamer); 
         h4.getChildren().add(ct); 
         h2.getChildren().add(objc);
         h2.getChildren().add(objet);
         h3.getChildren().add(bodyc);
         h3.getChildren().add(contenu);
         h1.getChildren().add(h4);
         h1.getChildren().add(h2);
         h1.getChildren().add(h3);
         String stylebt = "-fx-background-color: #FFFFFF;";
         String style = "-fx-background-color: #FFFFFF; -fx-padding: 5 ;";
         String stylU = "-fx-font-weight: bold; -fx-font-size: 20px;";
         String stylUt = "-fx-font-weight: bold; -fx-font-size: 16px;";
         h1.setStyle(style);
         reclamer.setStyle(stylU);
         bodyc.setStyle(stylUt);
         objc.setStyle(stylUt);
         btnsupp.setStyle(stylebt);
         VBR.getChildren().add(h1);
         btnsupp.setOnAction(event -> DeleteR(evv.getId()));
       
    
        }
        }
          
        private void DeleteR(int id ) {
        ServiceReclamation sr = new ServiceReclamation();
        sr.supprimerreclamation(id);
        JOptionPane.showMessageDialog(null, "Post supprimé !");
         VBR.getChildren().clear();
         ReadClaims();
        }
        
    
    
     private void UpdateU(String username,String email,int tel,String adresse,String img, int id ) {
         UserServices su = new UserServices();
        try {
            su.updateImage(username,email,tel,adresse,img,id);
         userimage.getChildren().clear();
         barimg.getChildren().clear();
         VB.getChildren().clear(); 
         ReadPost();
         setbar();
        } catch (MessagingException ex) {
           
        }
        
        }
     
     
     
    @FXML
     private void settings() throws IOException {
        
        Stage stage = (Stage)anachropane.getScene().getWindow();
        File file = Fc.showOpenDialog(stage);
        if (file!=null){
             Path source = Paths.get(file.getAbsoluteFile().toURI());
             Path destination = Paths.get("C://xampp/htdocs/pidev/web/images/"+file.getAbsoluteFile().getName());
         //   System.out.println(""+file.getAbsoluteFile().getName());
          Files.copy(source, destination,StandardCopyOption.REPLACE_EXISTING);
            Image image = new Image(file.getAbsoluteFile().toURI().toString());
            changeimg.setImage(image); 
            UserServices su = new UserServices();
            User u = new User();
            u=su.findById(UserSession.getUser().getId());
            final int id = u.getId();
            final String imagen =file.getAbsoluteFile().getName();
            save.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);    
            if (susername.getText().length()==0 ||smail.getText().length()==0||sphone.getText().length()==0||sadd.getText().length()==0)
                   
             { alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("Please fill all the fields");
                    alert.showAndWait();
             }
                else if (sphone.getText().length()<8) {
                    //Dialog.show("Alert", "Price can't be null", new Command("OK"));
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("phone number is composed by 8 numbers");
                    alert.showAndWait();    
                }
                       /* else if (<1) {
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);  
                    alert.setContentText("Quantity can't be null");
                    alert.showAndWait();
                }*/
                else
                  { UpdateU(susername.getText(),smail.getText(),Integer.parseInt(sphone.getText()),sadd.getText(),imagen,id);
                  
                  }
  
              });
           
        }    
     }
            
        
       
         
     }
     
     
     
     
     
     
        
         
     

     
