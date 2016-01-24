/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lock;


import static java.lang.Thread.sleep;


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
    
        
    @Override
        public void run(){ //mirar paso antes de escribir  cuando pide escribir para el resto de escritores/lectores
        try {
            do{
            paso.mirar();
            sleep((int) (1000 + 2000 * Math.random()));
            paso.mirar();
            this.libro.toma_leer(numescritor);
            paso.mirar();
            sleep((int) (500 + 1500 * Math.random()));
            paso.mirar();
            this.libro.deja_leer(numescritor);
        } while(libro.libro.size()<=49);
        } catch (Exception e) {
        }          
    }
}


