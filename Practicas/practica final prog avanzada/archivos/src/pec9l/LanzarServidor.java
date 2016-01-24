/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pec9l;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author pedro.barquin
 */

//clase para lanzar el servidor rmi
public class LanzarServidor extends Thread {

    //atributos
    private Tobogan tobogan;
    private ParqueInfantil parqueInfantil;

    //contructor sin para la ejecucion principal
    public LanzarServidor(ParqueInfantil parqueInfantil, Tobogan tobogan) {
        this.parqueInfantil = parqueInfantil;
        this.tobogan = tobogan;
        start();            //lanza a ejecutar el hilo que crear el servidor
    }

    public void run() {
        try {
            VigilanteRMI obj = new VigilanteRMI(parqueInfantil, tobogan); //Crea una instancia de sí mismo como objeto a registrar pasonado el parque infantil y el tobogan
            Registry registry = LocateRegistry.createRegistry(1099); //Arranca un rmiregistry local en el Puerto 1099
            Naming.rebind("//127.0.0.1/ObjetoEstado", obj);      //conecta con rmiregistry en este ordenador y le avisa que ObjetoRemoto
            System.out.println("Registrado el RMI");       //Mensaje de conexion
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
