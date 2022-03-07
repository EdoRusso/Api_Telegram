/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibreriaTelegram;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Edo
 */
public class getUpdates {

JSONArray vett;
URL url;

    public getUpdates() {
    }
int Chat_id=0;
    public getUpdates(String stringa) throws IOException {
    try {
        url= new URL(stringa+"/getUpdates");
        Scanner s= new Scanner(url.openStream());
        s.useDelimiter("\u001a");
        String jsonCompleto= s.next();
        System.out.println(jsonCompleto);
        JSONObject oggetto= new JSONObject(jsonCompleto);
        System.out.println(oggetto.length());
        vett= oggetto.getJSONArray("result");
        for (int i = 0; i < vett.length(); i++) {
            int updates_id=vett.getJSONObject(i).getInt("update_id");
            int message_id=vett.getJSONObject(i).getJSONObject("message").getInt("message_id");
            int id=vett.getJSONObject(i).getJSONObject("message").getJSONObject("from").getInt("id");
            boolean is_bot=vett.getJSONObject(i).getJSONObject("message").getJSONObject("from").getBoolean("is_bot");
            String name=vett.getJSONObject(i).getJSONObject("message").getJSONObject("from").getString("first_name");
            String username=vett.getJSONObject(i).getJSONObject("message").getJSONObject("from").getString("username");
            String language=vett.getJSONObject(i).getJSONObject("message").getJSONObject("from").getString("language_code");
            int id_chat=vett.getJSONObject(i).getJSONObject("message").getJSONObject("chat").getInt("id");
            String first_name=vett.getJSONObject(i).getJSONObject("message").getJSONObject("chat").getString("first_name");
            String username_chat=vett.getJSONObject(i).getJSONObject("message").getJSONObject("chat").getString("username");
            String type=vett.getJSONObject(i).getJSONObject("message").getJSONObject("chat").getString("type");
            int date=vett.getJSONObject(i).getJSONObject("message").getInt("date");
            String text=vett.getJSONObject(i).getJSONObject("message").getString("text");
            
            Chat_id=id;
            System.out.println(updates_id+"\n"+message_id+"\n"+id+"\n"+is_bot+"\n"+name+"\n"+username+"\n"+language+"\n"+id_chat+"\n"+first_name+"\n"+username_chat+"\n"+type+"\n"+date+"\n"+text);
        }
    } catch (MalformedURLException ex) {
        Logger.getLogger(getUpdates.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
   public int GetChat_id(){
       return Chat_id;
   }
   
}
