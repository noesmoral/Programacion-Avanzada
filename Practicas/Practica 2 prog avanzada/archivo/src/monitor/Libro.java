package monitor;

import java.util.ArrayList; 

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
    
    public synchronized void escribir(int id) throws InterruptedException {
        while (escribiendo==true) {       //Si hay un escritor activo bloquea al resto de escritores 
            wait();
        }
        escribiendo=true;           //activa el flag de escribiendo   
        while (leyendo>=1) {       //Cuando esten leyendo alguien espera hasta que no queda nadie 
            wait();
        }
        String a = Integer.toString(id);
        libro.add(a); //añade el mensaje al libro           
        Biblioteca.listaEscritores(a);  //manda el escritor para que se muestre por pantalla
        b=libro.toString();  //pasa datos a otro tipo
        Biblioteca.listaLibro(b);//envia el contenido del libro para mostrarlo
    }
    
    public synchronized void deja_escribir() throws InterruptedException { 
        Biblioteca.listaEscritores("Vacio");    //muestra vacio
        escribiendo=false; //cuando termina pone el escritor a falso
        notifyAll();    //abre los hilos
          
    }
    
    public synchronized void toma_leer(int id) throws InterruptedException {
        while (escribiendo==true) {       //Cuando esten escribiendo manda esperar espera 
            wait();
        }
        leyendo++;      //aumenta el contador de los lectores
        String a = Integer.toString(id);
        lectores.add(a);    //añade lector a la lista
        b=lectores.toString();  //pasa datos
        Biblioteca.listaLectores(b);    //muestra lectores por pantalla
    }
    
    public synchronized void deja_leer(int id) throws InterruptedException {
        String a = Integer.toString(id);
        lectores.remove(a);     //borra el lector
        b=lectores.toString();  //pasa datos
        Biblioteca.listaLectores(b);    //muestra lectores por pantalla
        leyendo--;        
        if(leyendo==0){          
          notifyAll();
          Biblioteca.listaLectores("Vacio");
        }
    }
}
