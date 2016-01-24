package RMI_Colores;

/**
 *
 * @author pedro.barquin
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMI extends UnicastRemoteObject implements InterfaceRMI {
        Paso paso=new Paso();
    
        public RMI(Paso paso) throws RemoteException {
        this.paso=paso;         //recibimos el paso para poder parar la ejecucion
        }  
        
        public String mensaje(String estado) throws RemoteException  {   // Implementación del metodo remoto  
            switch (estado) {                       //Segun lo que nos llegue se realiza una accion o otra
                case "Detener":
                    paso.cerrar();
                    return "Ejecucion Parada";
                case "Reanudar":
                    paso.abrir();
                    return "Ejecucion Abierta";
                default:
                    return "ok";
            }
        }

}
