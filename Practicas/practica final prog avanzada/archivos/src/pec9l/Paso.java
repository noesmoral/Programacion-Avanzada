package pec9l;

/**
 *
 * @author pedro.barquin
 */
//clase paso
public class Paso {
    //atributos
    private boolean cerrado;
    
    //metodo para comprobar el estado
    public synchronized void mirar(){
        while(cerrado){
            try{wait();} catch(InterruptedException ie){}
        }
    }
    //metodo para abrir
    public synchronized void abrir(){       //abre paso
        cerrado=false;                      //cambia el valor de la variable
        notifyAll();                        //avisa del cambio a todos
    }
    //metodo para cerrar
    public synchronized void cerrar(){      //cierra paso
        cerrado=true;        
        notifyAll();                        //avisa del cambio a todos
    }
}
