/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Util.JavaMailUtil;
import javax.mail.MessagingException;

/**
 *
 * @author ghofrane
 */
public class mailtest {
      public static void main(String[] args) throws MessagingException {
          JavaMailUtil js = new JavaMailUtil();
          js.sendMail("adhunt1235@gmail.com");
      }
}
