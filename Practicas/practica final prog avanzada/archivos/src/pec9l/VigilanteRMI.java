/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pec9l;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author pedro.barquin
 */

//clase del objeto remoto creado vigilante
public class VigilanteRMI  extends UnicastRemoteObject implements InterfaceVigilante{
    
    //atributos
    private ParqueInfantil parqueInfantil;
    private Tobogan tobogan;
    
    //constructor  con sus datos pasador
    public VigilanteRMI(ParqueInfantil parqueInfantil,Tobogan tobogan)throws RemoteException{
        this.parqueInfantil=parqueInfantil;     //se asignan los datos
        this.tobogan=tobogan;
    }
   
    //metodo declarado en la interface
    public int vigilarTobogan(){
        //posible tray catch
        Nino nini=tobogan.listanino.get(0);     //se obtiene el hilo que esta en la lista de montados del tobogan
        int eda=nini.edad();                    //se obtiene la esdad de este hilo
        if(eda>=7){                             //se comprueba y se procede acorde al resultado
            //expulsar
            tobogan.tob=false;                  //se pone a false una variable que evita que entre en el siguiente paso a la expulsion el hilo en cuestion
            nini.interrupt();                   //se interumpe el hilo
            return eda;                         //se retorna el valor del resultado que es la edad
        }
        return eda;                             //se retorna el valor del resultado
    }
}
