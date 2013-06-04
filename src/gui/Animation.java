/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;

/**
 *
 * @author lentonen_admin
 */
public class Animation {
    

	
	private ArrayList<OneScene> scenes; // a scene is a picture
	private int sceneIndex; // the elements in the ArrayList
	

	private long movieTime; // the actual time of the animation, how long it's been running
	
	private long totalTime; // if movieTime exceeds totalTime, the animation starts over
	private int maxScenes; // 
	
	
	//CONSTRUCTOR
	
	public Animation(){
		 maxScenes = 0;
		 scenes = new ArrayList<OneScene>();
		 totalTime = 0;
		 start(); 
	}
	
	/*
	 * Increases totaltime and adds a new object 'OneScene' to the Arraylist
	 * every time a scene (or an image) to totaltime is added, it's the sum of all scenes
	 * each scene is a whole new object
	 */
   
	public synchronized  void addScene(Image i, long time){
    	totalTime += time; 
    	maxScenes += 1;
    	scenes.add(new OneScene(i, totalTime));
    }
	
    /*
     * (re)start animation from the beginning
     */
    public synchronized void start(){
    	movieTime = 0;
    	sceneIndex = 0;
    }
	
    /*
     * change from scene to scene
     * time now when the method is called - time the method was called last time
     * movietime = sum of all times between updates
     * 
     */
    public synchronized void updateAnimation(long timePassed){
        System.out.println("MovieTime:"+movieTime);
        System.out.println("Scene time:"+getScene(sceneIndex).endTime);
        System.out.println("Scenes:"+scenes.size());
        System.out.println("time passed:"+timePassed);
        System.out.println("total time:"+totalTime);
        
    	if(scenes.size()>1){
    		movieTime += timePassed;
                System.out.println("MovieTime"+movieTime);
    		if(movieTime >= totalTime){
    			movieTime = 0;
    			sceneIndex = 0; 
    		}
    		while(movieTime > getScene(sceneIndex).endTime){
                    System.out.println("next scene");
    			sceneIndex++;
    		}	
    		}    		
    	}
    
    /*
    public synchronized void updateAnimation(){
    	
    	if(sceneIndex == maxScenes-1 ){
    		sceneIndex = 0;
    	}
    	else{
    		sceneIndex++;
    	}
    	
    }
    */
    
    /*get current scene
     * check if the array list is empty and return null if so
     * 
     */
	public synchronized Image getImage(){
		if(scenes.size()==0){
			return null;
		}else{
			return getScene(sceneIndex).pic;
		}
	}

	/*
	 * get scene
	 */
	private OneScene getScene(int x){
		return scenes.get(x);
	}
	
/////// PRIVATE INNER CLASS
	
	private class OneScene{		
		
		Image pic;
		long endTime; //how long the picture stays on the screen before moving on to the next one
		
		
		public OneScene(Image pic, long endTime){
			this.pic = pic;
			this.endTime = endTime;
		}
		
		@SuppressWarnings("unused")
		public Image getpic(){
			return pic;
		}
				
	}
	
	
}