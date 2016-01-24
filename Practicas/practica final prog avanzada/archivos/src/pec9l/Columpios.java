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

//clase columpios
public class Columpios {
    
        //atriburos
        ArrayList<String> espera = new ArrayList();                 //arraylist de la lista de espera
        private ArrayList<String> lista = new ArrayList();          //arraylista de la lista de montados
        Semaphore semaforotobogan = new Semaphore (3, true);        //semadoto de 3 para que no entren mas de los deseados
        private ParqueInfantil parqueInfantil;              
        
        //constructor
        public Columpios(ParqueInfantil parqueInfantil){
            this.parqueInfantil=parqueInfantil;
        }
        
        //metodo llegar
        public void llegar(int id){
            String a = Integer.toString(id);            //se tranforma el valor
            espera.add(a);                              //se añade a la lista de espera
            String b=espera.toString();                 //se obtiene la lista de espera
            parqueInfantil.listaesperacolumpios(b);     //se manda la lista de espera para mostrarla
            try {         
                semaforotobogan.acquire(1);             //se obtiene el permiso para evitar que entren mas de 3
            } catch (Exception e) {
            }
            espera.remove(a);                           //se elimina de la lista de espera
            b=espera.toString();                        //se obtiene la lista de espera 
            parqueInfantil.listaesperacolumpios(b);     //se envia la llista de espera
            lista.add(a);                               //se añade a la lista de montados
            String c=lista.toString();                  //se obtiene la lista de montados
            parqueInfantil.listamonstadoscolumpios(c);  //se manda la lista de monstados para mostrarlos
        }
        
        //metodo montar
        public void montar(int id){
            try {
                
                sleep((int) (200 + 2000 * Math.random()));      //espera un valor aleatorio
                
            } catch (Exception e) {
            }
        }
        
        //metodo salir
        public void salir(int id){
            try {
                String e = Integer.toString(id);            //se pasa el valor
                lista.remove(e);                            //se elimina de la lista de montados
                String d=lista.toString();                  //se obtiene la lista de montados
                parqueInfantil.listamonstadoscolumpios(d);  //se envia la lista de montados para mostrarla
                semaforotobogan.release();                  //se libera el semaforo
            } catch (Exception e) {
            }
        }
}
