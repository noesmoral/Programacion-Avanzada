package RMI_Colores;

/**
 *
 * @author pedro.barquin
 */
public class Paso {     //Aqui se realizan las paradas y la reanudacion
    private boolean cerrado=false;

    public synchronized void mirar(){
        while(cerrado){
            try{wait();} catch(InterruptedException ie){}
        }
    }
    public synchronized void abrir(){
        cerrado=false;
        notifyAll();
    }
    public synchronized void cerrar(){
        cerrado=true;
    }
}
