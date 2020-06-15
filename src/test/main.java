/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Util.UserSession;
import entities.Follow;
import entities.Jaime;
import entities.Post;
import entities.Reclamation;
import entities.User;
import services.ServiceFollow;
import services.ServiceJaime;
import services.ServicePost;
import services.ServiceReclamation;
import services.UserServices;

/**
 *
 * @author ghofrane
 */
public class main {
     public static void main(String[] args) {
         
   UserServices us = new UserServices();   
         User e = new User("haifa", "haifa");
         us.Authentification(e);
         System.out.println(us.findByUsername("haifa"));
   
  /*System.out.println("*************************post***************************");
 
     Post p1 = new Post("ggg");
    //Post p2 = new Post(2, "test","ghof");
 
   
    ServicePost srv = new ServicePost();


        //ajout
      //srv.ajouterPost(p1);
       //srv.ajouterPost(p2);
    
    // affichage
        System.out.println("--------------------------");
        srv.afficherpost(UserSession.getUser().getId());
        System.out.println("--------------------------");
        
 
       //modification
       srv.modifierpost(46, "fffff");
        System.out.println("------------post modifié--------------");
        

        //supp
        //srv.supprimerpost(45);
         srv.afficherpost(UserSession.getUser().getId());
         System.out.println("--------------------------");
   System.out.println("******************************************************");    */
 
 
 
 
 
/* System.out.println("*****************reclamation***************************");
 Reclamation r1 = new Reclamation("FF", "FFFF", 1 , 2);
  //  reclamation r2 = new reclamation("24/01/2020","test1","test1" , 2); 
 
  
    ServiceReclamation rrv = new ServiceReclamation();


        //ajout
   rrv.ajouterreclamation(r1);
   // rrv.ajouterreclamation(r2);
    // affichage
        System.out.println("--------------------------");
        rrv.afficherReclamation();
       // System.out.println("--------------------------");

        //modification
        rrv.modifierreclamation(3, "bad" , "post");
        System.out.println("--------------------------");
        rrv.afficherReclamation();
        System.out.println("--------------------------");

        //supp
      rrv.supprimerreclamation(5);
        System.out.println("--------------------------");
       rrv.afficherReclamation();
        System.out.println("--------------------------");

   System.out.println("********************************************************"); */


/*
 System.out.println("*************************like***************************");
      Jaime j1 = new Jaime(14);
      
 //   Jaime j2 = new Jaime(2,10);
 
   
    ServiceJaime jrv = new ServiceJaime();
        //ajout
            //  jrv.ajouterjaime(j1);
             System.out.println(jrv.nbrejaime(14));
  
 

        //supp
        jrv.supprimerjaime(29);
        System.out.println("--------------------------");
        System.out.println("--------------------------");

   System.out.println("*****************************************************"); */
    
    
    
    
  /* System.out.println("****************follow********************************");
    Follow f1 = new Follow(1);
   // follow f2 = new follow(2,2,2);
    ServiceFollow frv = new ServiceFollow();
    //ajout
          frv.follow(f1);
          //frv.follow(f2);
   /* // affichage
        System.out.println("--------------------------");
        frv.afficherfollow();
        frv.afficherpostfollow(2);
        System.out.println("--------------------------");


    //supp
        frv.unfollow(1);
        System.out.println("--------------------------");
        frv.afficherfollow();
        System.out.println("--------------------------");

        System.out.println("*****************************************************");   */
  
  
   
   
     
     }  
    
}
