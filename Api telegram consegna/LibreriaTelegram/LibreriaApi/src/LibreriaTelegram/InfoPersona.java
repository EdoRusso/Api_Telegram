/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibreriaTelegram;

/**
 *
 * @author Edo
 */
public class InfoPersona {
    String id,citta,nome;

    public InfoPersona(String id, String citta, String nome) {
        this.id = id;
        this.citta = citta;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String vis(){
        String vis=id+" "+nome+" "+citta;
        return vis;
    }
    
}
