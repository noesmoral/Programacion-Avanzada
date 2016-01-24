package RMI_Colores;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author pedro.barquin
 */
public interface InterfaceRMI extends Remote {

    String mensaje(String estado) throws RemoteException;
}