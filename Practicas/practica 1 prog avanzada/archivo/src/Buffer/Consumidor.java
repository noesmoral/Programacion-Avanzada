/*
 * La clase Consumidor define hilos que recogen mensajes de un buzón de mensajes
 * y los muestran por pantalla.
 * El buzon y el número de mensajes, los reciben como parámetros del constructor
 * antes de terminar.
 * Entre recogida y recogida, esperan un tiempo aleatorio entre 0.5 y 1 seg.
 */
package Buffer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Thread {
    private int numMensajes;
    private Buffer miBuffer;

    public Consumidor(int numMensajes, Buffer miBuzon){
        this.numMensajes=numMensajes;
        this.miBuffer=miBuzon;
        start();
    }

    @Override
    public void run(){
        for(int i=0; i<numMensajes; i++){
            try{ sleep((int)(500+500*Math.random()));} catch(InterruptedException e){}
            try {
                System.out.println("Recibe"+miBuffer.recibeMensaje());
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Total mensajes leidos: "+numMensajes);
    }
}
