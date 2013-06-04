/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gameobjects.Player;
import gameobjects.GameManager;


/**
 *
 * @author lentonen_admin
 */
class UpdateAll {
    
    public static void update(long timePassed, int screenWidth, int screenHeight){
//        System.out.println("update all:");
        updatePlayer(timePassed, screenWidth, screenHeight);
    }
    public static void updatePlayer(long timePassed, int screenWidth, int screenHeight){
        
//        System.out.println("Torvalds");
       GameManager.getTorvalds().update(timePassed, screenWidth, screenHeight);
//        System.out.println("Gates");
       GameManager.getGates().update(timePassed, screenWidth, screenHeight);
    }
}
