/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebarmi;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
/**
 *
 * @author pedro.barquin
 */
public class Casio extends UnicastRemoteObject implements Calculadora{
    
    public Casio() throws RemoteException{} 
     public int sumar(int a, int b){
         return a+b;
     }
}
