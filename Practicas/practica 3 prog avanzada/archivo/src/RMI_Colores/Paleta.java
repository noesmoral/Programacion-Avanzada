package RMI_Colores;

import java.awt.Color;

// La clase Paleta contiene los cuatro colores del parchís y los sirve a los pitores

/**
 *
 * @author pedro.barquin
 */
public class Paleta {
    private Color [] colores = new Color[4];
    private boolean[] libre = new boolean[4];
    public Paleta(){
        colores[0]=Color.red;
        colores[1]=Color.blue;
        colores[2]=Color.green;
        colores[3]=Color.yellow;
        for(int i=0;i<4;i++)libre[i]=true;
    }
    public synchronized Color tomaColor(){
        while(true){
            int i = (int)(4 * Math.random());    //Elige color al azar
            if(libre[i]) {
                libre[i]=false;
                return colores[i];
            }
        }
    }
    public synchronized void dejaColor(Color c){
        for(int i=0;i<4;i++) if(c.equals(colores[i])) libre[i]=true;
    }
}
