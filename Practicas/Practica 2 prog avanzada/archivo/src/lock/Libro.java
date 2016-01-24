package lock;


import java.util.ArrayList;    
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;   

/**
 *
 * @author pedro.barquin
 */
public class Libro {
    private int leyendo=0;
    private boolean escribiendo=false;
    private String b;
    protected ArrayList<String> libro = new ArrayList();
    protected ArrayList<String> lectores = new ArrayList();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock r = lock.readLock();
    private Lock w = lock.writeLock();    
    
    public void escribir(int id) throws InterruptedException{
        w.lock();
        String a = Integer.toString(id);
        libro.add(a); //añade el mensaje al libro
        Biblioteca.listaEscritores(a);  //manda el escritor para que se muestre por pantalla
        b=libro.toString();  //pasa datos a otro tipo
        Biblioteca.listaLibro(b);//envia el contenido del libro para mostrarlo        
    }
    
    public void deja_escribir()  throws InterruptedException { 
        try{
            Biblioteca.listaEscritores("Vacio");    //muestra vacio
        escribiendo=false; //cuando termina pone el escritor a falso            
        } finally {
            w.unlock(); //desbloquea los ecritores
        }           
    }
    
    public void toma_leer(int id){
        r.lock();
        leyendo++;      //aumenta el contador de los lectores
        String a = Integer.toString(id);
        lectores.add(a);    //añade lector a la lista
        b=lectores.toString();  //pasa datos
        Biblioteca.listaLectores(b);    //muestra lectores por pantalla     
    }
    
    public void deja_leer(int id) {
        try{
            String a = Integer.toString(id);
            lectores.remove(a);     //borra el lector
            b=lectores.toString();  //pasa datos
            Biblioteca.listaLectores(b);    //muestra lectores por pantalla
            leyendo--;  //decrementa los lectores
            if(leyendo<=1){
                Biblioteca.listaLectores("Vacio");
                //r.unlock();
            }
        } finally {r.unlock();}    //desbloquea los lectores       
    }
}
