/*
 * Programa que lanza cuatro lectores y un escritor.
 * que se comunican a través de un buzón de mensajes.
 * Debe comprobarse que no se pierden los mensajes ni se leen dos veces
 */
package Buffer;
public class PruebaBuffer {
    public static void main(String[] s){
        Buffer bufferX = new Buffer();
        Productor pedro = new Productor("Pedro ",5,bufferX);
        Productor juan = new Productor("Juan ",4,bufferX);
        Productor antonio = new Productor("Antonio ",6,bufferX);
        Productor luis = new Productor("Luis ",7,bufferX);
        Consumidor jose = new Consumidor(21,bufferX);
    }
}
