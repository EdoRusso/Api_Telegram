/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibreriaTelegram;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.print.DocFlavor;
import org.json.JSONObject;

/**
 *
 * @author russo_edoardo
 */
public class SendMessage {
    Scanner s;
    URL url;
    String chiave;
    getUpdates G=new getUpdates();
    int id=G.GetChat_id();
    public SendMessage(String chiave) throws IOException {
        String benvenuto = "benvento,sono il bot";
        url= new URL("https://api.telegram.org/bot"+chiave+"/sendMessage?chat_id"+id+benvenuto);
        s=new Scanner(url.openStream());
        s.next();
    }

}
