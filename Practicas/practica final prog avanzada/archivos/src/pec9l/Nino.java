/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pec9l;

import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author pedro.barquin
 */
public class Nino extends Thread {

    //atributos
    private Tobogan tobogan;
    private Tiovivo tiovivo;
    private Columpios columpios;
    private ParqueInfantil parqueInfantil;
    private Paso paso;
    private int id = 0;
    private int edad = 0;

    //constructor con todos los datos
    public Nino(int id, int edad, Paso paso, Tobogan tobogan, Columpios columpios, Tiovivo tiovivo) {
        this.id = id;           //se asignan todos los datos
        this.edad = edad;
        this.paso = paso;
        this.tobogan = tobogan;
        this.columpios = columpios;
        this.tiovivo = tiovivo;
        start();                //strat de los hilos
    }
    
    //metodo para devolver edad
    public int edad(){
        return edad;
    }

    //run de los hilos
    @Override
    public void run() {
        while(parqueInfantil.parqueestado()==true) {    //se repite mientras que el parque este abierto
            try {
                sleep((int) (200 + 2000 * Math.random()));  //se duerme durante un numero aleatorio
                int rand = (int) (Math.random()*3);       //de manera aleatoria se obtiene un numero de 1 a 3
                switch (rand) {                             //se eleige una opcion de las tres 
                    case 0:
                        paso.mirar();                       //mira que esta abierto el paso
                        columpios.llegar(id);               //entra en el columpio
                        paso.mirar();
                        columpios.montar(id);               //monta en el columpio
                        paso.mirar();
                        columpios.salir(id);                //se retira del columpio
                        break;                              //sale
                    case 1:
                        paso.mirar();
                        tobogan.llegar(id, edad);           //entra al tobogan
                        paso.mirar();
                        tobogan.sube(id, edad,this);        //se pone a subir y se pasa el mismo como dato
                        paso.mirar();
                        tobogan.baja(id);                   //se pone a bajar y sale
                        break;                              //se retira
                    case 2:
                        paso.mirar();
                        tiovivo.llegar(id);                 //llega al tiovivo
                        paso.mirar();
                        tiovivo.montar(id);                 //monta el el tiovivo
                        paso.mirar();
                        tiovivo.salir(id);                  //sale del tiovivo
                        break;                              //se retira
                }
            } catch (Exception e) {
            }
        } 
    }
}
