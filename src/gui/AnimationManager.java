/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author lentonen_admin
 */
public class AnimationManager {
    
         int torvalds_starlefttpic  = 1;
         int torvalds_stopleftpic   = 3;
         int torvalds_starrighttpic = 4;
         int torvalds_stoprightpic  = 6;
         int torvalds_starhitpic    = 7;
         int torvalds_stophitpic    = 9;         
         int gates_startleftpic     = 1;
         int gates_stopleftpic      = 3;
         int gates_startrightpic    = 4;
         int gates_stoprightpic     = 6;         
         int gats_starhitpic        = 7;
         int gates_stophitpic       = 9;        
    
         
         public void loadAnimation(){
             
         }
         /**
          * start = 0
          * stop = 5
          * loops from pic0 to pic5 -> pic0,pic1,pic2,pic3,pic4,pic5
          * pics must be named: torvalds_left0,torvalds_left1,torvalds_left2 etc.
          * writes images to an image-array
          * adds each image to individual scenes in animation
          * 
          * 
          * @param startpic
          * @param stoppic
          * @param time
          * @param a
          * @param ima
          * @param nimi
          * @param failityyppi 
          */
         private void loadImagesToAnimation(int startpic, int stoppic, long time, Animation a, String nimi,String failityyppi){
                
             int length = startpic-stoppic+1;
             Image[] ima = new Image[length];
             for(int i=0;i<ima.length;i++){		
//                        System.out.println("["+i+"]:"+nimi+start+"."+failityyppi);
                        ima[i]=ImageLoader.loadImage(nimi,startpic,failityyppi) ;
                        //ima[i]= new ImageIcon(this.getClass().getResource(start+".gif")).getImage();
                        startpic = startpic + 1;		
                }

                for(int j=0;j<ima.length;j++){
                        a.addScene(ima[j], time);
                        System.out.println(j+"loaded");
                }
        }

    
    
    
}
