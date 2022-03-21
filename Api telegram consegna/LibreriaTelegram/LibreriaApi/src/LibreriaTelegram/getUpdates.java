/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibreriaTelegram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

/**
 *
 * @author Edo
 */
public class getUpdates {

    JSONArray vett;
    URL url;

    public getUpdates() {
    }
    int Chat_id = 0;

    public getUpdates(String stringa)  {
        try {
            url = new URL(stringa + "/getUpdates");
            Scanner s = new Scanner(url.openStream());
            s.useDelimiter("\u001a");
            String jsonCompleto = s.next();
            System.out.println(jsonCompleto);
            JSONObject oggetto = new JSONObject(jsonCompleto);
            System.out.println(oggetto.length());
            vett = oggetto.getJSONArray("result");
            if (vett.length() > 0) {
                for (int i = 0; i < vett.length(); i++) {

                    int updates_id = vett.getJSONObject(i).getInt("update_id");
                    int message_id = vett.getJSONObject(i).getJSONObject("message").getInt("message_id");
                    int id = vett.getJSONObject(i).getJSONObject("message").getJSONObject("from").getInt("id");
                    boolean is_bot = vett.getJSONObject(i).getJSONObject("message").getJSONObject("from").getBoolean("is_bot");
                    String name = vett.getJSONObject(i).getJSONObject("message").getJSONObject("from").getString("first_name");
//            String username=vett.getJSONObject(i).getJSONObject("message").getJSONObject("from").getString("username");
                    String language = vett.getJSONObject(i).getJSONObject("message").getJSONObject("from").getString("language_code");
                    int id_chat = vett.getJSONObject(i).getJSONObject("message").getJSONObject("chat").getInt("id");
                    String first_name = vett.getJSONObject(i).getJSONObject("message").getJSONObject("chat").getString("first_name");
                    //String username_chat=vett.getJSONObject(i).getJSONObject("message").getJSONObject("chat").getString("username");
                    String type = vett.getJSONObject(i).getJSONObject("message").getJSONObject("chat").getString("type");
                    int date = vett.getJSONObject(i).getJSONObject("message").getInt("date");
                    String text = vett.getJSONObject(i).getJSONObject("message").getString("text");
                    Chat_id = id;
                    System.out.println(updates_id + "\n" + message_id + "\n" + id + "\n" + is_bot + "\n" + name + "\n" + language + "\n" + id_chat + "\n" + first_name + "\n" + type + "\n" + date + "\n" + text);
                    String tmp = "";
                        if (text.startsWith("/citta")) {
                        if (text.length() > 6) {
                            tmp = text.substring(7);
                            boolean cerca = false;
                            List<InfoPersona> info = this.leggiCsv();
                            if (info.size() > 0) {
                                for (int j = 0; j < info.size(); j++) {
                                    System.out.println("Prova-------------" + info.get(j).getId() + " " + info.get(j).getNome() + " " + info.get(j).getCitta());
                                    //Controllo che l'ID alla pos j del file sia già presente
                                    System.out.println("Dati getId " + info.get(j).getId());
                                    //System.out.println("Dati id "+id.);

                                    if (info.get(j).getId().equals(id + "")) {
                                        //Persona trovata
                                        cerca = true;
                                        System.out.println("Dati 1 " + info.size());
                                        //Aggiungo alla lista dati un nuovo elemento
                                        info.add(new InfoPersona(info.get(j).getId(), info.get(j).getNome(), tmp));
                                        System.out.println("Dati 2 " + info.size());
                                        //Rimuovo dalla lista l'elemento doppio
                                        info.remove(j);
                                        System.out.println("Dati 3 " + info.size());
                                        //scrivo la nuova lista su file
                                        this.ripristinaCsv();
                                        this.ScriviCsv(info);
                                        //dati.size elementi-1
                                        for (int k = 0; k < info.size(); k++) {
                                            System.out.println("GetID " + info.get(j).getId());
                                            System.out.println("GetNome " + info.get(j).getNome());
                                            System.out.println("GetCitta " + info.get(j).getCitta());

                                            System.out.println("Prova2-------------" + info.get(k).getId() + " " + info.get(k).getNome() + " " + info.get(k).getCitta());

                                        }

                                    }

                                    // System.out.println(dati.get(i));
                                }
                                if (!cerca) {
                                    FileWriter myWriter = new FileWriter("citta.csv", true);
                                    //myWriter.append(updtate_id+";"+first_name+";"+tmpString+";"+"\n");
                                    myWriter.write(id + ";" + first_name + ";" + tmp + ";" + "\n");
                                    myWriter.close();

                                }
                            } else {
                                FileWriter myWriter = new FileWriter("citta.csv", true);
                                //myWriter.append(updtate_id+";"+first_name+";"+tmpString+";"+"\n");
                                myWriter.write(id + ";" + first_name + ";" + tmp + ";" + "\n");
                                myWriter.close();
                            }

                        }
                        }
                        GetCoordinate benvenuto = new GetCoordinate();
                        System.out.println(stringa);
                        url = new URL(stringa + "/sendMessage?chat_id=" + id_chat + "&text=" + benvenuto.xml(tmp));
                        s = new Scanner(url.openStream());
                        s.next();
                    }

                }
                int nuovo_id = vett.getJSONObject(vett.length() - 1).getInt("update_id") + 1;
                url = new URL(stringa + "/getUpdates?offset=" + nuovo_id);
                s = new Scanner(url.openStream());
                s.next();
                        } catch (MalformedURLException ex) {
            Logger.getLogger(getUpdates.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getUpdates.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(getUpdates.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(getUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
        public int GetChat_id() {
        return Chat_id;
    }
    public List<InfoPersona> leggiCsv() throws FileNotFoundException, IOException {
        BufferedReader reader;
        List<InfoPersona> l = new ArrayList();
        reader = new BufferedReader(new FileReader("citta.csv"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(";");
            l.add(new InfoPersona(split[0], split[1], split[2]));
        }
        reader.close();
        return l;
    }

    public void ripristinaCsv() {
        FileWriter file;
        try {
            file = new FileWriter("citta.csv");
            file.write("");
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(getUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ScriviCsv(List<InfoPersona> l) {
        FileWriter file;
        try {
            file = new FileWriter("citta.csv", true);
            for (int i = 0; i < l.size(); i++) {
                String riga = l.get(i).vis();
                file.write(riga);
            }
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(getUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
    
//boolean cerca = false;
//                            List<InfoPersona> info = this.leggiCsv();
//                            if (info.size() > 0) {
//                                for (int j = 0; j < info.size(); j++) {
//                                    System.out.println("Prova-------------" + info.get(j).getId() + " " + info.get(j).getNome() + " " + info.get(j).getCitta());
//                                    //Controllo che l'ID alla pos j del file sia già presente
//                                    System.out.println("Dati getId " + info.get(j).getId());
//                                    //System.out.println("Dati id "+id.);
//
//                                    if (info.get(j).getId().equals(id + "")) {
//                                        //Persona trovata
//                                        cerca = true;
//                                        System.out.println("Dati 1 " + info.size());
//                                        //Aggiungo alla lista dati un nuovo elemento
//                                        info.add(new InfoPersona(info.get(j).getId(), info.get(j).getNome(), tmp));
//                                        System.out.println("Dati 2 " + info.size());
//                                        //Rimuovo dalla lista l'elemento doppio
//                                        info.remove(j);
//                                        System.out.println("Dati 3 " + info.size());
//                                        //scrivo la nuova lista su file
//                                        this.ripristinaCsv();
//                                        this.ScriviCsv(info);
//                                        //dati.size elementi-1
//                                        for (int k = 0; k < info.size(); k++) {
//                                            System.out.println("GetID " + info.get(j).getId());
//                                            System.out.println("GetNome " + info.get(j).getNome());
//                                            System.out.println("GetCitta " + info.get(j).getCitta());
//
//                                            System.out.println("Prova2-------------" + info.get(k).getId() + " " + info.get(k).getNome() + " " + info.get(k).getCitta());
//
//                                        }
//
//                                    }
//
//                                    // System.out.println(dati.get(i));
//                                }
//                                if (!cerca) {
//                                    FileWriter myWriter = new FileWriter("utenti.csv", true);
//                                    //myWriter.append(updtate_id+";"+first_name+";"+tmpString+";"+"\n");
//                                    myWriter.write(id + ";" + first_name + ";" + tmp + ";" + "\n");
//                                    myWriter.close();
//
//                                }
//                            } else {
//                                FileWriter myWriter = new FileWriter("utenti.csv", true);
//                                //myWriter.append(updtate_id+";"+first_name+";"+tmpString+";"+"\n");
//                                myWriter.write(id + ";" + first_name + ";" + tmp + ";" + "\n");
//                                myWriter.close();
//                            }
//
//                        }

//    public List<InfoPersona> leggiCsv() throws FileNotFoundException, IOException {
//        BufferedReader reader;
//        List<InfoPersona> l = new ArrayList();
//        reader = new BufferedReader(new FileReader("utenti.csv"));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String[] split = line.split(";");
//            l.add(new InfoPersona(split[0], split[1], split[2]));
//        }
//        reader.close();
//        return l;
//    }
//
//    public void ripristinaCsv() {
//        FileWriter file;
//        try {
//            file = new FileWriter("utenti.csv");
//            file.write("");
//            file.close();
//        } catch (IOException ex) {
//            Logger.getLogger(getUpdates.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void ScriviCsv(List<InfoPersona> l) {
//        FileWriter file;
//        try {
//            file = new FileWriter("utenti.csv", true);
//            for (int i = 0; i < l.size(); i++) {
//                String riga = l.get(i).vis();
//                file.write(riga);
//            }
//            file.close();
//        } catch (IOException ex) {
//            Logger.getLogger(getUpdates.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }


//FileWriter myWriter = new FileWriter("citta.csv", true);
//                            myWriter.write(updates_id + " " + tmp + " " + first_name + "\n");
//                            myWriter.close();