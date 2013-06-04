/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gameobjects.Player;
import gameobjects.GameManager;
import java.awt.Graphics2D;

/**
 *
 * @author lentonen_admin
 */
public class DrawAll extends InsultSmackFighter{
    
//    public DrawAll(){
//        
//    }
//    private static Graphics2D g;
    
    public static void drawAll(){
        
        drawBG();
        drawPlayer1(GameManager.getTorvalds());
        drawPlayer2(GameManager.getGates());
//        g = drawer;
    }
    
    private static void drawBG(){
        drawer.drawImage(ImageLoader.getBG(), 0, 0, null); 
    }
     private static void drawPlayer2(Player gates){
       //drawer.drawImage(ImageLoader.getPlayer2(),gates.getX(),gates.getY(),null);
         drawer.drawImage(gates.getCurrentImage(),gates.getX(),gates.getY(),null);
    }
    private static void drawPlayer1(Player torvalds){
       // drawer.drawImage(ImageLoader.getPlayer(),torvalds.getX(),torvalds.getY(),null);
        drawer.drawImage(torvalds.getCurrentImage(),torvalds.getX(),torvalds.getY(),null);
       
    }
   
}
