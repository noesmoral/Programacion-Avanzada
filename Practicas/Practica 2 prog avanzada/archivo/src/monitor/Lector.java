package monitor;



import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro.barquin
 */
public class Lector extends Thread{     //Atributos
    private int numescritor;
    private Paso paso;
    private Libro libro;
    private int a=1;
    
    
    public Lector(int numescritor, Paso paso, Libro libro){     //Constructor
        this.numescritor=numescritor;
        this.paso=paso;
        this.libro=libro;
        start(); 
    }
    
        
        public void run(){ //mirar paso antes de escribir  cuando pide escribir para el resto de escritores/lectores
           do{
             paso.mirar(); //mirar paso antes de leer
             try {   //espera para leer
                sleep((int) (1000 + 2000 * Math.random()));
            } catch (InterruptedException e) {
            }
            paso.mirar();   //mirar paso
            try {          
            this.libro.toma_leer(numescritor); //llama toma-leer          
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }  
            paso.mirar(); //mirar paso 
             try {   //espera leyendo
                sleep((int) (500 + 1500 * Math.random()));
            } catch (InterruptedException e) {
            }
            paso.mirar();
            try {          
            this.libro.deja_leer(numescritor); //llama deja-leer          
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }   
            } while(libro.libro.size()<=49);//mientras tenga menos de 50 el libro continua
           
    }
}


