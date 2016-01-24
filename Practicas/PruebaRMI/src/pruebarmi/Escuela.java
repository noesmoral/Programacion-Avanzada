/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebarmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author pedro.barquin
 */
public class Escuela {
    public static void main(String[] s) throws NotBoundException, MalformedURLException, RemoteException{
        Calculadora c = (Calculadora) Naming.lookup("//localhost/casio123");
        System.out.println("dos y dos son:"+ c.sumar(2, 2));
    }
}
