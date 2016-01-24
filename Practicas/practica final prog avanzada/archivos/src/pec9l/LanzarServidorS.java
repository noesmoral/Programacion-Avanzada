/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pec9l;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author pedro.barquin
 */

//clase servidor socket sin para la ejecucion principal
public class LanzarServidorS extends Thread{
    private Tobogan tobogan;
    private Tiovivo tiovivo;
    private Columpios columpios;
    private ParqueInfantil parqueInfantil;
    
    //contructor del servidor
    public LanzarServidorS(ParqueInfantil parqueInfantil,Tobogan tobogan,Tiovivo tiovivo, Columpios columpios){
        this.parqueInfantil=parqueInfantil;
        this.tobogan=tobogan;
        this.tiovivo=tiovivo;
        this.columpios=columpios;
        start();                //lanza a start el hilo
    }
    
    //metodo run del servidor
    public void run(){
                //atributos e inicializaciones
                ServerSocket lanzarServidorS;   //crea el serversocket
                Socket conexion;                //socket
                DataInputStream  entrada;       //entradas y salidas
                DataOutputStream salida;
                DataOutputStream salida1;
                DataOutputStream salida2;
                String msg;
                
                //creacion
                try {
                lanzarServidorS = new ServerSocket(2222); // Creamos un ServerSocket EN EL PUERTO 2222
                System.out.println("Servidor Socket Arrancado....");
                while (true) {                          //bucle para que este siempre activo
                conexion = lanzarServidorS.accept();     // Esperamos una conexión y creamos el socket
                entrada = new DataInputStream(conexion.getInputStream());  // Abrimos los canales de E/S
                salida  = new DataOutputStream(conexion.getOutputStream());
                String mensaje = entrada.readUTF();    //Leemos el mensaje del cliente
                if(mensaje.equals("Actualizar")) {      //en funcion del mensaje operamos
                    salida1  = new DataOutputStream(conexion.getOutputStream());  //Abrimos los canales de E/S
                    salida2  = new DataOutputStream(conexion.getOutputStream());
                    int c=tobogan.capacidadtobogan.size();          //obtenemos los valores de las colas
                    int b=columpios.espera.size();
                    int a=tiovivo.capacidadtiovivo.size();  
                    String enteroStringc = Integer.toString(c);      //pasamo a string los valores   
                    String enteroStringb = Integer.toString(b);
                    String enteroStringa = Integer.toString(a);
                    salida.writeUTF(enteroStringc);                 //respondemos con dichas valores 
                    salida1.writeUTF(enteroStringb); 
                    salida2.writeUTF(enteroStringa); 
                }
                else if (mensaje.equals("Cerrar")) {            //si el mensaje es cerrar
                     parqueInfantil.setparqueestado(false);     //se manda cerrar al parque
                }                
                msg=mensaje;
                conexion.close();                           // Y cerramos la conexión
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
}
}
