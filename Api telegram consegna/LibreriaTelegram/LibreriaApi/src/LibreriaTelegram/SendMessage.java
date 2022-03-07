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
import javax.print.DocFlavor;
import org.json.JSONObject;

/**
 *
 * @author russo_edoardo
 */
public class SendMessage {
   
    getUpdates G=new getUpdates();
    int id=G.GetChat_id();
    public SendMessage() throws IOException {
        String benvenuto = "benvento,sono il bot";
        String urlString = "https://api.telegram.org/bot" + id + "/sendMessage?chat_id=" + id + "&text=" + benvenuto;
        URL url = new URL(urlString);
        URLConnection c = url.openConnection();
        StringBuilder s = new StringBuilder();
        InputStream i = new BufferedInputStream(c.getInputStream());
        BufferedReader buffer = new BufferedReader(new InputStreamReader(i));
        String scritta = "";
        while ((scritta = buffer.readLine()) != null) {
            s.append(scritta);
        }
    }

}
