/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import gui.Animation;
import gui.ImageLoader;
import gui.ScreenManager;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author lentonen_admin
 */
public class Player implements Sprite{
    
    private int x=0;
    private int y=0;
    private String name;
    private int xDisplacement=0;
    private Animation left,right,smack,insult;
    private long startingtime;
    // 1 runleft, 2 runright, 3 hit, 4 insult
    private int state;
    // current image to draw
    private Image curImage;
    PlayerAnimationLoader loader = new PlayerAnimationLoader();
    boolean updateAnimation = false;

   /**
    * load animations
    * @param x
    * @param y
    * @param state 
    */
   public Player(String nimi, int x, int y, int state){
       this.name = nimi;
       this.x = x;
       this.y = y;
       this.state = state;
       
   }
   
  
//    public static Image getImage(){
//        return playerImage;
//    }
    
    public void update(long timePassed, int screenWidth, int screenHeight){
        //check screenCollision   
            updateX(screenWidth);
            updateAnimation(timePassed);   
    }

    
    public void updateX(int screenWidth){
        //System.out.println("X:"+x);
        //System.out.println("displacement:"+xDisplacement);
        if(xDisplacement<0 && checkScreenCollisionLeft() == false){
            int sx = x + xDisplacement;
            x = sx;
        }
        else if(xDisplacement>=0 && checkScreenCollisionRight(screenWidth) == false){
             int sx = x + xDisplacement;
             x = sx;
        }
       
    }
    
    /**
     * 
     */
    public void updateState(int state){
        this.state = state;
    }
    
    
    private void updateAnimation(long timePassed) {
         System.out.println("update animation");
         if(updateAnimation){
            left.updateAnimation(timePassed);
            right.updateAnimation(timePassed);
         }
         else{
            left.start();
            right.start();
         }
    }
    
    public void setX(int sx){
        x = sx;
    }
    public void setY(int sy){
        y = sy;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    
    @Override
    public int getWidth() {
        return getCurrentImage().getWidth(null);
    }

    @Override
    public int getHeight() {
        return getCurrentImage().getHeight(null);
    }

    
    public void setXDisplacement(int i, boolean update){
        xDisplacement = i;
        updateAnimation = update;
    }
    
    public Image getCurrentImage(){

    System.out.println("Getting current image. State:"+state);
        Image image = null;
        if(state == 1){
                image = left.getImage();
                }
        if(state == 2){
                image = right.getImage();
                }		
        if(state == 3){
                image = smack.getImage();
                }
        if(state == 4){
                image = insult.getImage();
                }
        return image;
    }

    public void loadAnimations(int startright_pic, int stopright_pic, int startleft_pic, int stopleft_pic,  int time, String nimi,String failityyppi){
        loader.loadImagesInAnimation(startright_pic, stopright_pic, startleft_pic, stopleft_pic,  time, nimi, failityyppi);
        left = loader.getRunLeft();
        right = loader.getRunRight();
    }

   /**
    * 
    * @return 
    */
    public Rectangle getMyRectangle()
      {  return  new Rectangle((int)this.x, (int)this.y, left.getImage().getWidth(null), left.getImage().getHeight(null)); 
      }

    /**
     * if player collides with the other
     * 
     * @param sprite
     * @return 
     */
    public boolean spriteCollision(Player player){

            boolean collision = false;

        Rectangle spriterec = player.getMyRectangle();
        spriterec.grow(-spriterec.width/3, 0);   // make bounded box thinner

        if (spriterec.intersects( getMyRectangle() )) {    // collision?
          collision = true;
        }
        return collision;		
    }

/***************************************************
* 
* 
*/
    @Override
    public boolean checkScreenCollision(int screenWidth, int screenHeight) {
        
        if(checkScreenCollisionLeft()) { 
            return true;
        }
        if(checkScreenCollisionRight(screenWidth)){
            return true; 
        }
        return false;
    }

    public boolean checkScreenCollisionLeft(){
        int x = getX();  
        if(x <= 0){
            setX(0);
            return true;
        }
        else {
            return false;
        }
    }

    /*
    * if the player has reached the end of the screen on right
    * 
    * at the moment just stops the player
    */
    public boolean checkScreenCollisionRight(int screenWidth){
        int x = getX();  
        
        if((getX()+getWidth())>= screenWidth){
            setX(screenWidth-(getWidth()));
            return true;
        }
        else {
            return false;
        }
        }

        /*
        * if the player has reached the end of the screen up, stop the player
        */

    public boolean checkScreenCollisionUp(){
        int y = getY();
        if(y < 0){
            setY(0);
            return true;
        }
        else{
            return false;
        }
    }


    /*
    * 
    */
    public boolean checkScreenCollisionDown(int screenHeight){
        int y = getY();
        if((getY()+getHeight()) >= screenHeight){
            setY(screenHeight -(getHeight()));
            return true;
        }
        else{
            return false;
        }	
    }





}
