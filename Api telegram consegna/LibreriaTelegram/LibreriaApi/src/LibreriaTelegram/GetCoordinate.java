/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibreriaTelegram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Edo
 */
public class GetCoordinate {
    PrintWriter out;
    URL url;
    BufferedReader in = null;
    List <LatLon> getIndicazioni;

    public GetCoordinate() {
    }
    
    public String xml(String citta) throws ParserConfigurationException, SAXException, IOException {
        try {
            out= new PrintWriter("xml.txt");
            url = new URL("https://nominatim.openstreetmap.org/search?q=" + citta+ "&format=xml&addressdetails=1");
            Scanner s = new Scanner(url.openStream());
            s.useDelimiter("\u001a");
            String obj = s.next();
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
               out.println(line);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(GetCoordinate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetCoordinate.class.getName()).log(Level.SEVERE, null, ex);
        }
        MyXMLOperations xml= new MyXMLOperations();
        getIndicazioni=new ArrayList<>();
        getIndicazioni=xml.parseDocument("xml.txt");
        return (getIndicazioni.get(0).lat+" "+getIndicazioni.get(0).log);
    }

}
