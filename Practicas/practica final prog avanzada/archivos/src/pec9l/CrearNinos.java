/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pec9l;

import static java.lang.Thread.sleep;

/**
 *
 * @author pedro.barquin
 */

//clase especial para crear los niños sin para la ejecucion principal
public class CrearNinos extends Thread{
    //atributos
    private Tobogan tobogan;
    private Tiovivo tiovivo;
    private Columpios columpios;
    private Paso paso;
    
    //contructor
    public CrearNinos(Tobogan tobogan,Tiovivo tiovivo,Columpios columpios,Paso paso){
        this.tobogan=tobogan;           //se asignan los datos
        this.tiovivo=tiovivo;
        this.columpios=columpios;
        this.paso=paso;
        start();            //lanza a ejecutar el hilo que creara los niños
    }
    
    //run donde se crean los niños del ejercicio
    public void run(){
        for(int i=11;i<=60;i++){                //bucle para crear los niños
            try {
                sleep(200);                     //sleep para insertar un retardo entre creacion y creacion
            } catch (InterruptedException ex) {
            }
            int edad=i%10;                      //se obtienen la edad
            edad=edad+3;    
            Nino nino=new Nino(i,edad,paso,tobogan,columpios,tiovivo);  //se crea el niño con la informacion necesaria
        }
    }
    
}
