package semaforo;


import static java.lang.Thread.sleep;


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
             try {
                 paso.mirar();
                 sleep((int) (1000 + 1000 * Math.random()));    //hace un pausa
                 paso.mirar();
                 this.libro.escribir(numEscritor); //llama al ecritor
                 paso.mirar();
                 sleep((int) (1000 + 1000 * Math.random()));
                 paso.mirar();
                 this.libro.deja_escribir();        //retira el escritor
             } catch (Exception e) {
             }
            
      }   
}
    
    
    
    //llama deja-escribir
    
}
