package semaforo;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;    
   
/**
 *
 * @author pedro.barquin
 */
public class Libro {
    private int leyendo=0;
    private String b;
    protected ArrayList<String> libro = new ArrayList();
    protected ArrayList<String> lectores = new ArrayList();
    private Semaphore semaforo1;
    private Semaphore semaforo2;

    
    public Libro(Semaphore semaforo, Semaphore semaforoo) {
        semaforo1 = semaforo;
        semaforo2 = semaforoo;
    }
    
    //probar con un semaforo para cada uno 
    public  void escribir(int id) throws InterruptedException {
       
        try {
            semaforo1.acquire(15);
        } catch (InterruptedException ex) {}    
        String a = Integer.toString(id);
        libro.add(a); //añade el mensaje al libro           
        Biblioteca.listaEscritores(a);  //manda el escritor para que se muestre por pantalla
        b=libro.toString();  //pasa datos a otro tipo
        Biblioteca.listaLibro(b);//envia el contenido del libro para mostrarlo  
    }
    
    public  void deja_escribir() throws InterruptedException { 
        Biblioteca.listaEscritores("Vacio");    //muestra vacio
        semaforo1.release(15);    //abre los hilos
    }
    
    public void toma_leer(int id) throws InterruptedException {
        
        try {
            semaforo1.acquire();
        } catch (InterruptedException ex) {} 
        leyendo++;      //aumenta el contador de los lectores
        String a = Integer.toString(id);
        lectores.add(a);    //añade lector a la lista
        b=lectores.toString();  //pasa datos
        Biblioteca.listaLectores(b);    //muestra lectores por pantalla
    }
    
    public  void deja_leer(int id) throws InterruptedException {
        try {
            String a = Integer.toString(id);
            lectores.remove(a);     //borra el lector
            b=lectores.toString();  //pasa datos
            Biblioteca.listaLectores(b);    //muestra lectores por pantalla
            leyendo--;    
            if(leyendo==0){                 
                    Biblioteca.listaLectores("Vacio");
                } 
        } finally {
            semaforo1.release();
        }
    }
}
