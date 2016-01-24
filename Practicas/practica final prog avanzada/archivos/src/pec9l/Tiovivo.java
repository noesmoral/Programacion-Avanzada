/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pec9l;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 *
 * @author pedro.barquin
 */
public class Tiovivo {

    //atributos
    ArrayList<String> capacidadtiovivo = new ArrayList();   //arraylist de la cola de espera
    private ArrayList<String> listatiovivo = new ArrayList();       //arraylist de lista en uso
    private ParqueInfantil parqueInfantil;
    Semaphore semaforotiovivo = new Semaphore(5, true);     //semaforo de 5 para controlar la entrada
    CyclicBarrier barrera = new CyclicBarrier(5);           //barrera para limitar los hilos hasta que llegen el resto

    //constructor al que le pasamos el parque para poder trabajar con el
    public Tiovivo(ParqueInfantil parqueInfantil) {
        this.parqueInfantil = parqueInfantil;
    }

    //metrodo de llegada    
    public void llegar(int id) {
        entrarcola(id);
        try {
            semaforotiovivo.acquire();          //aqui se obtiene un permiso del semaforo para asi evitar que entren mas de 5
        } catch (Exception e) {
        }
        salirrcola(id);
        entrarmontar(id);
    }

    //metodo montar
    public void montar(int id) {
        try {
            barrera.await();                //barrera para que no avanze hasta que esten 5 montados
            sleep(5000);                    //sleep de 5 seg
        } catch (InterruptedException | BrokenBarrierException e) {
        }
    }

    //metodo salir
    public synchronized void salir(int id) {
        try {
            salirmontar(id);
        } finally {
            semaforotiovivo.release();              //se libera el semaforo
        }
    }

    public synchronized void entrarcola(int id) {
        String a = Integer.toString(id);        //se transforma el valor a string para poder añadirlo a los arraylist
        capacidadtiovivo.add(a);                //se añade a la lista de espera
        String b = capacidadtiovivo.toString(); //se obtiene un string de la lista de espera
        parqueInfantil.listaesperatiovivo(b);  //se actualiza la lista de espera que esta en parque infantil
    }

    public synchronized void salirrcola(int id) {
        String b = Integer.toString(id);
        capacidadtiovivo.remove(b);             //se elimina de la lista de espera el hilo
        b = capacidadtiovivo.toString();
        parqueInfantil.listaesperatiovivo(b);   //se actualiza la lista de espera
    }

    public synchronized void entrarmontar(int id) {
        String c = Integer.toString(id);
        listatiovivo.add(c);                    //se añade el hilo a la lista de montados
        String d = listatiovivo.toString();     //se tranforma
        parqueInfantil.listamonstadostiovivo(d);//se actualiza la lista de montados
    }

    public synchronized void salirmontar(int id) {
        String e = Integer.toString(id);        //se tranforma a string el numero
        listatiovivo.remove(e);                 //se elimina de la lista de montados
        String d = listatiovivo.toString();     //se pasa la lista a string
        parqueInfantil.listamonstadostiovivo(d);
    }
}
