/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import gui.Animation;
import gui.ImageLoader;
import java.awt.Image;

/**
 *
 * @author lentonen_admin
 */
public class PlayerAnimationLoader {
    
    
    private Image[] runningleft,runningright, ismack, iinsult; 	
    private Animation runright, runleft, smack, insult;
    private String name;
    private long startingTime;
    
    public PlayerAnimationLoader(){
        this.name = name;
    }
    
    public Animation getRunRight(){
        return runright;
    }
    
    public Animation getRunLeft(){
        return runleft;
    }
    
    public Animation getSmack(){
        return smack;
    }
    
    public Animation getInsult(){
        return insult;
    }
        
    public void loadImagesInAnimation(int startright_pic, int stopright_pic, int startleft_pic, int stopleft_pic,  int time, String nimi,String failityyppi){
        //calculate how many pics in the animation, i.e. stops at pic 3, starts at pic 1, so 3-1=2 and 2+1=3
        // this value is the same for both animations
        int length = (stopright_pic-startright_pic)+1;

        //current_pic = start_pic;
        runright = new Animation();			
        runningright = new Image[length];	
        // and the same for runleft
        runleft = new Animation();
        runningleft = new Image[length];

        this.LoadImages(startright_pic, time, length, runright, runningright,nimi,failityyppi);
            System.out.println("Rightrun loaded");
        this.LoadImages(startleft_pic, time, length, runleft, runningleft,nimi,failityyppi);
            System.out.println("Leftrun loaded");
        
                }

    private void LoadImages(int start, long time, int length, Animation a, Image[] ima, String nimi,String failityyppi){
        for(int i=0;i<ima.length;i++){		
            System.out.println("["+i+"]:"+nimi+start+"."+failityyppi);
            ima[i]=ImageLoader.loadImage(nimi,start,failityyppi);
            //ima[i]= new ImageIcon(this.getClass().getResource(start+".gif")).getImage();
            start = start + 1;	
        }
        //image1= new ImageIcon("C:\\test\\1.gif").getImage();
        //image2= new ImageIcon("C:\\test\\2.gif").getImage();
        //image3= new ImageIcon("C:\\test\\3.gif").getImage();

        for(int j=0;j<ima.length;j++){
            a.addScene(ima[j], time);
            System.out.println(j+"loaded");
        }
    }
                    
}
