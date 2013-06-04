/*
 * holds the player-instances
 * they can be called from here anytime and anywhere
 */
package gameobjects;

import gui.InsultSmackFighter;

/**
 *
 * @author lentonen_admin
 */
public class GameManager {
    
    private static Player torvalds;
    private static Player gates;
   
    public static void setPlayers(Player ltorvalds, Player pgates){
        torvalds = ltorvalds;
        gates = pgates;
    }
    
   
    
    public static Player getTorvalds(){
        return torvalds;
    }
    
    public static Player getGates(){
        return gates;
    }
    
}
