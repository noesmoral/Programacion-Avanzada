package monitor;



import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro.barquin
 */
public class Escritor extends Thread{       //Atributos
    private int numEscritor;
    private Paso paso;
    private Libro libro;
    
    
     public Escritor(int numEscritor, Paso paso, Libro libro){      //Constructor
        this.numEscritor=numEscritor;
        this.paso=paso;
        this.libro=libro;
        start();       
    }
     
     public void run(){ //mirar paso antes de escribir  cuando pide escribir para el resto de escritores/lectores         
         for (int i = 0; i < 5; i++) { //escribe durante 5 veces
            paso.mirar();
            try {   //espera de 1 a 2 seg
                sleep((int) (1000 + 1000 * Math.random()));
            } catch (InterruptedException e) {
            }
            paso.mirar();
            try {          
            this.libro.escribir(numEscritor); //llama escribir          
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }   
            paso.mirar();
            try {   //espera de 1 a 2 seg
                sleep((int) (1000 + 1000 * Math.random()));
            } catch (InterruptedException e) {
            }
            paso.mirar();
            try {          
            this.libro.deja_escribir(); //llama deja-escribir          
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }       
      }   
}
    
    
    
    //llama deja-escribir
    
}
