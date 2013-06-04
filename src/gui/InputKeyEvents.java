
package gui;

import gameobjects.Player;
import gameobjects.GameManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author lentonen_admin
 */
public class InputKeyEvents extends KeyAdapter{
    
    private Set<Integer> set = new HashSet();
    private Player torvalds,gates;
    private static int steps = 10;
    private final int LEFT = 1;
    private final int RIGHT = 2;
    private final int SMACK = 3;
    private final int INSULT =4;
        
 
   
    /**
     * 
     * @param e 
     */
    public void keyPressed(KeyEvent e) {
         
         
                torvalds =  GameManager.getTorvalds();
                gates = GameManager.getGates();
                int keys = e.getKeyCode();
                int itnext;
                set.add(keys);
                Iterator it = set.iterator(); 
                while(it.hasNext()){
                    itnext = (Integer)it.next();
                        if(itnext == KeyEvent.VK_ESCAPE){
                            InsultSmackFighter.stop();
                        }
                        if(itnext == KeyEvent.VK_ENTER ){
                            InsultSmackFighter.start();
                        }
                        
                        if(itnext == KeyEvent.VK_A){                         
                            torvalds.setXDisplacement(-1*steps,true);
                            torvalds.updateState(LEFT);
                        }
                        if(itnext == KeyEvent.VK_D){
                            torvalds.setXDisplacement(steps,true);
                            torvalds.updateState(RIGHT);
                        }
                        if(itnext == KeyEvent.VK_J){
                            gates.setXDisplacement(-1*steps,true);
                            gates.updateState(LEFT);
                        }
                        if(itnext == KeyEvent.VK_L){
                            gates.setXDisplacement(steps,true);
                            gates.updateState(RIGHT);
                        }
                        
                 }
             
      }
    

    
    /**
     * 
     * @param e 
     */
    public void keyReleased(KeyEvent e){
        torvalds =  GameManager.getTorvalds();
        gates = GameManager.getGates();
        int keys = e.getKeyCode();
        set.remove(keys);
        System.out.println("key removed");
         if(keys == KeyEvent.VK_A || keys == KeyEvent.VK_D){
             torvalds.setXDisplacement(0, false);
         }
         if(keys == KeyEvent.VK_J || keys == KeyEvent.VK_L){
             gates.setXDisplacement(0,false);
         }
    }
  
}
