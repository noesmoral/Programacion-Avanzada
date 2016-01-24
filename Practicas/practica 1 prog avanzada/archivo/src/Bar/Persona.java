
package Bar;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Barquin
 */
public class Persona extends Thread{
    private int numPersona;
    private Paso paso;
    private Bar miBar;
   


    //constructro de personas
    public Persona(int numPersona, Paso paso, Bar local){
        this.numPersona=numPersona;
        this.paso=paso;
        this.miBar=local;
        start();
       
    }
 
    //proceso que sigue cada persona de las 1000
    public void run(){
        //espera entre 0,2 y 0,5 seg
        try {   
                sleep((int) (200 + 300 * Math.random()));
            } catch (InterruptedException e) {
            }
        //mira la parada
        paso.mirar();  
        //Entra al bar
     try {          
            this.miBar.entraBar(numPersona); //llama entrada           
        } catch (InterruptedException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        //mira la parada
        paso.mirar();
        // Se toma algo en el bar 0.5 1 seg
        try {   
                sleep((int) (500 + 1000 * Math.random()));
            } catch (InterruptedException e) {
            }
        //sale del bar     
        try {
            this.miBar.saleBar(numPersona);  //llama salida
        } catch (InterruptedException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
