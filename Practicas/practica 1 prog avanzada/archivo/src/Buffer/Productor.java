/*
 * La clase Productor define hilos que envían mensajes a un buzón de mensajes.
 * Los mensajes constan de un prefijo String y un sufijo que es un entero del 1 al 5
 * El prefijo, el número de menaajes a escribir y el buzón donde hacerlo,
 *  se reciben como parámetros en el constructor.
 * Entre mensaje y mensaje, esperan un tiempo aleatorio entre 0.5 y 1 seg.
 */
package Buffer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Productor extends Thread {

    private String prefijo;
    private int numMensajes;
    private Buffer miBuffer;

    public Productor(String prefijo, int n, Buffer buzon) {
        this.prefijo = prefijo;
        numMensajes = n;
        miBuffer = buzon;
        start();
    }

    public void run() {
        for (int i = 1; i <= numMensajes; i++) {
            try {
                sleep((int) (500 + 500 * Math.random()));
            } catch (InterruptedException e) {
            }
            try {
                miBuffer.enviaMensaje(prefijo + i);
                System.out.println("Envia" +prefijo + i);
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
