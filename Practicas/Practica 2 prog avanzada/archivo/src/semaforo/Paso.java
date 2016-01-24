package semaforo;

public class Paso {
    private boolean cerrado;
    

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
