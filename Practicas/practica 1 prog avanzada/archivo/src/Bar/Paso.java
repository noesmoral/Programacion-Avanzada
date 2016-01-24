// La clase Paso define una Región Crítica 
// Si vale false(abierto) el proceso puede continuar. Si es true(cerrado) el proceso se detiene
package Bar;
public class Paso {
    private boolean cerrado;
    private Bar bar = new Bar();

    public synchronized void mirar(){
        while(cerrado){
            try{wait();} catch(InterruptedException ie){}
        }
    }
    public synchronized void abrir(){       //abre paso
        cerrado=false;
        notifyAll();
    }
    public synchronized void cerrar(){      //cierra paso
        cerrado=true;        
        notifyAll();
    }
}
