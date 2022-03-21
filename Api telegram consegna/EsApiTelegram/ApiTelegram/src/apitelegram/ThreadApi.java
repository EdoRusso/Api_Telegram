/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apitelegram;

import static apitelegram.ApiTelegram.chiave;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 *
 * @author Edo
 */
public class ThreadApi extends Thread{

    public ThreadApi() {
        
    }

    @Override
    public void run() {
        while (true) {            
           ApiTelegram a= new ApiTelegram();
           
            
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(ThreadApi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
             
            }
        }
    }

