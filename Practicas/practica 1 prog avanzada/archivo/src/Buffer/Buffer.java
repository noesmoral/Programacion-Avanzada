/*
 * La clase Buzon define una Región Crítica Condicional
 * El método enviaMensaje debe esperar si el buzón está lleno
 * El método recibeMensaje debe esperar si el buzón está vacío.
 * Cuando un hilo completa su operación, desbloquea a los que estén esperando
 * para que puedan contnuar intentando su acción.
 */
package Buffer;

import java.util.ArrayList;

public class Buffer {

    private boolean lleno = false;
    private int cantidad = 0;
    private boolean escribiendo = false;
    ArrayList<String> mensaje = new ArrayList();

    public synchronized void enviaMensaje(String msg) throws InterruptedException {

        while (mensaje.size() == 4) {       //Cuando se almacenan 4 mensajes manda esperar 
            wait();
        }
        mensaje.add(msg); //añade el mensaje a la cola
        notifyAll();


    }

    public synchronized String recibeMensaje() throws InterruptedException {
        String msg;
        
        while (mensaje.size() ==0) {//cuando hay un mensaje o mas lo envia mientras espera
            wait();
        }
        notifyAll();
        msg = mensaje.get(0);   //almacena el mensaje para evitar que sea borrado antes de enviarlo
        mensaje.remove(0);      //borra el mensaje de buzon
        return msg; //pasa el menasje al consumidor

    }
}
