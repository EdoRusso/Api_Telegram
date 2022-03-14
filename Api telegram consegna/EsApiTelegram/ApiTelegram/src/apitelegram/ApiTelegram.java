/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apitelegram;

import LibreriaTelegram.Api;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edo
 */
public class ApiTelegram {
static String chiave="5130903459:AAHJ7stpGOH3ADTxyxAf88zu02VMpqXhUag";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
     Api a= new Api(chiave);
    try {
        a.getUpdates();
    } catch (IOException ex) {
        Logger.getLogger(ApiTelegram.class.getName()).log(Level.SEVERE, null, ex);
    }
//    try {
//        a.sendMessage();
//    } catch (IOException ex) {
//        Logger.getLogger(ApiTelegram.class.getName()).log(Level.SEVERE, null, ex);
//    }
//     
    }
    
}
