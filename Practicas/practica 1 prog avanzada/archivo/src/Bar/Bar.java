
package Bar;


import java.util.ArrayList;


/**
 *
 * @author Pedro Barquin
 */
public class Bar {

    
  
    public String a;
    public String b;
    protected ArrayList<String> capacidad = new ArrayList();
    
    public synchronized void entraBar(int id) throws InterruptedException {
        String a = Integer.toString(id);
        while (capacidad.size() == 10) {       //Cuando se almacenan 10 personas esperar 
            wait();
        }
        capacidad.add(a); //añade el mensaje a la cola        
        b=capacidad.toString(); //pasa el arrylist
        ElBar.lista(b);  //manda el texto
        notifyAll();
    }
    
    
    public synchronized void saleBar (int id) throws InterruptedException {
        String a = Integer.toString(id);
        
        capacidad.remove(a);        //borra de la lista
         if(capacidad.size()<10 && capacidad.size()>1){
             b=capacidad.toString();
             ElBar.lista(b);        //manda el texto  cuando hay menos de 10
        }       
        if(capacidad.size()==0){
            ElBar.lista("Vacio");   //manda el texto al terminar
        }
        notifyAll();
        
    }
    
   
}
    
