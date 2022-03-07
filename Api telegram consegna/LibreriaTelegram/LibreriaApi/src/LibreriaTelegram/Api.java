/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibreriaTelegram;

import java.io.IOException;

/**
 *
 * @author Edo
 */
public class Api {
    String url;

    public Api(String chiave) {
        url="https://api.telegram.org/bot"+chiave;
    }
    public void getUpdates() throws IOException{
         new getUpdates(url);
    }
//    public void sendMessage() throws IOException{
//        new SendMessage(url);
//    }
}
