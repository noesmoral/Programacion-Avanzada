/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pec9l;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author pedro.barquin
 */

//clase tobogan
public class Tobogan {

    //atributos
    ArrayList<String> capacidadtobogan = new ArrayList();       //arraylist de la cola de espera
    private ArrayList<String> listatobogan = new ArrayList();           //arraylist de los montados
    ArrayList<Nino> listanino = new ArrayList<Nino>();          //arraylisty especial para poder acceder al hilo cuando se quiere expulsar
    Semaphore semaforotobogan = new Semaphore(1, true);         //semaforo de 1 para evitar que entren mas en el tobogan
    private ParqueInfantil parqueInfantil;                      //parque referenciado
    boolean tob=true;                                           //variable especial para evitar que entre en el ultimo paso el expulsado

    //constructor
    public Tobogan(ParqueInfantil parqueInfantil) {
        this.parqueInfantil = parqueInfantil;
    }

    //metodo de llegada
    public void llegar(int id, int edad) {
        String a = Integer.toString(id);            //se tranforma a string
        capacidadtobogan.add(a);                    //se añade a la lista de espera
        String b = capacidadtobogan.toString();     //se obtiene la lista de espera
        parqueInfantil.listaesperatobogan(b);       //se envia al parque para que se muestre
        try {
            semaforotobogan.acquire(1);             //se obtiene el permiso para entrar de 1 en 1
        } catch (Exception e) {
        }
        capacidadtobogan.remove(a);                 //se quita de la lista de espera
        b = capacidadtobogan.toString();            //se obtiene la lista de espera
        parqueInfantil.listaesperatobogan(b);       //se manda la lista de espera
        listatobogan.add(a);                        //se añade a la lista de montados
        String c = listatobogan.toString();         //se obtiene la lista de montados
        parqueInfantil.listamontadotobogan(c);      //se envia la lista de mosntados
    }

    //metodo de subir en el tobogan
    public void sube(int id, int edad,Nino nino) {
        listanino.add(nino);                        //se añade el niño al array list  especial
        try {
            sleep(1200);                            //se espera durante 1,2 seg
        } catch (InterruptedException e) {          //si se realiza la interupcion se entra en esta parte del codigo
            listatobogan.clear();                   //se limpia de montados
            String d = listatobogan.toString();     //se obtiene la lista de montados
            parqueInfantil.listamontadotobogan(d);  //se manda la lista de montados para que se muestre
            listanino.clear();                      //se limpia la lista especial
        }
    }

    //metodo de salir
    public void baja(int id) {
        try {
            if(tob==true){                              //si el valor de true es aun true se entra
                sleep(500);                             //se espera 0,5
                listatobogan.clear();                   //se limpia la lista de montados
                String de = listatobogan.toString();    //se obtiene la lista de motados
                parqueInfantil.listamontadotobogan(de); //se envia para que se muestre
                listanino.clear();                      //se vacia la especial
                semaforotobogan.release();              //se libera el semaforo
            }else {                                     //si no se a cumplido la condicion
                tob=true;                               //se pone el boolean a true
                semaforotobogan.release();              //se librea el semaforo
            }          
        } catch (Exception e) {
        }
    }
}
