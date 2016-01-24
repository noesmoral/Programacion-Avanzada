/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pec9l;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author pedro.barquin
 */

//interface del objeto vigilante rmi
public interface InterfaceVigilante extends Remote {
 
    //metodo del objeto del que es interface la cual retorna un entero que sera la edad del hilo
    int vigilarTobogan() throws RemoteException;
}
