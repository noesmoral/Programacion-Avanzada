/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebarmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 *
 * @author pedro.barquin
 */
public class Servidor {
    
    public static void main(String[] s) throws MalformedURLException{
        try{
            Casio calc = new Casio();
            Naming.rebind("//localhost/casio123", calc);
        }catch (RemoteException e){}
    }

}
