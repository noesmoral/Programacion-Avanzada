package Sockets_Colores;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author pedro.barquin
 */
public class Servidor extends Thread {
    Paso paso=new Paso();
    
    public Servidor(Paso paso){
        this.paso=paso;
        start();
    }
    
    public void run(){
                ServerSocket servidor;
                Socket conexion; //socket
                DataInputStream  entrada;
                DataOutputStream salida;
                int num = 0;
                try {
                servidor = new ServerSocket(5000); // Creamos un ServerSocket EN EL PUERTO 5000
                System.out.println("Servidor Arrancado....");
                while (true) {
                conexion = servidor.accept();     // Esperamos una conexión y creamos el socket
                num++;
                System.out.println("Conexión n."+num+" desde: "+conexion.getInetAddress().getHostName());
                entrada = new DataInputStream(conexion.getInputStream());  // Abrimos los canales de E/S
                salida  = new DataOutputStream(conexion.getOutputStream());
                String mensaje = entrada.readUTF();    //Leemos el mensaje del cliente
                if(mensaje.equals("Detener")) {
                    paso.cerrar();
                }
                else if (mensaje.equals("Reanudar")) {
                    paso.abrir();                    
                }
                salida.writeUTF("Servidor avisa recibido " + mensaje);
                System.out.println("Conexión n."+num+" mensaje recivido de Modulo de control: "+mensaje);
                conexion.close();                           // Y cerramos la conexión
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
}
}
