package gui;
/*
 * In this class we handle the flickering/tearing 
 * with doublebuffering aka page folding aka bufferstrategy images
 * Instead of drawing straight to the screen we load all images to
 * the buffer and bam, no flickering, the double buffering load images at 
 * the same time as they are shown, when the images are ready you can point to
 * them and load new ones on the run
 * 
 */

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ScreenManager {

	// video card
	private GraphicsDevice vc;
	private GraphicsEnvironment e;
	private GraphicsConfiguration gc;
        
        
        private static DisplayMode[] modes = {
		new DisplayMode(1024,768,32,0),
		new DisplayMode(1024,768,24,0),
		new DisplayMode(1024,768,16,0),
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0),
	};
	
	// give vc access to monitor screen
	public ScreenManager(){
		this.e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = e.getDefaultScreenDevice();
		gc = e.getDefaultScreenDevice().getDefaultConfiguration();
		
	}
	
	// get all the compatible display modes from this computers graphics card
	public DisplayMode[] getCompatibleDisplayModes(){
		return vc.getDisplayModes();
	}
	
	
	/* compares DM passed in to vc DM and see if they match
	 * if the DMs match you can return either one because they are the same
	 */
	public DisplayMode findFirstCompatibleMode(){
		DisplayMode goodModes[] = vc.getDisplayModes();
		for(int x=0; x<modes.length; x++){
			for(int y=0; y<goodModes.length; y++){
				if(displayModesMatch(modes[x], goodModes[y])){
					return modes[x];
				}
			}
		}
		return null;
	}
	
	
	public GraphicsEnvironment getGE(){
		return this.e;
	}
	
	public GraphicsConfiguration getGC(){
		return this.gc;
	}
	
	/*
	 * get current DM
	 */
	public DisplayMode getCurrentDisplayMode(){
		return vc.getDisplayMode();
	}
	
	/*
	 * check if two modes match each other
	 * first check resolution -> height width, if the resolutions doesn't match return false
	 * bit depth -> how many colours, if the bit depths don't match return false
	 * refresh rate -> 
	 */
	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2){
		
		if(m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()){
			return false;
		}
		if(m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()){
			return false;
		}
		if(m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()){
			return false;
		}
		return true;
	}
	
	/*
	 * make frame full screen
	 */
	public void setFullScreen(DisplayMode dm){
		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setIgnoreRepaint(true);
		f.setResizable(false);
                f.setFocusable(true);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setDefaultCloseOperation(3);
		vc.setFullScreenWindow(f);
		
		if(dm != null && vc.isDisplayChangeSupported()){
			try{
				vc.setDisplayMode(dm);
			}catch(Exception ex){}
		}
		f.createBufferStrategy(2);
                f.addKeyListener(new InputKeyEvents()); 
		
	}
	
	
	
	
	
	/*
	 * we will set Graphics object = to whatever this method returns
	 */
	public Graphics2D getGraphics(){
		//gets all that's going on in the screen and stores it into w
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			BufferStrategy s = w.getBufferStrategy();
			return (Graphics2D)s.getDrawGraphics();
		}else{ 
			return null;
		}
	}
	
	/*
	 * Display update method
	 */
	public void updateScreen(){
		Window w = vc.getFullScreenWindow();
		if(w != null){
			BufferStrategy s = w.getBufferStrategy();
			if(!s.contentsLost()){
				s.show();
			}
		}
	}
	
	/*
	 * return full screen window
	 */
	public Window getFullScreenWindow(){
		return vc.getFullScreenWindow();
	}

	/*
	 * Get the width
	 */
	public int getWidth(){
	 Window w = vc.getFullScreenWindow();
	 if(w != null){
		return w.getWidth(); 
	 }else{
		 return 0;
	 }
	 
	}
	
	
	/*
	 * Get the height
	 */
	public int getHeight(){
	 Window w = vc.getFullScreenWindow();
	 if(w != null){
		return w.getHeight(); 
	 }else{
		 return 0;
	 }
	 
	}
	
	/*
	 * get out of full screen
	 */
	public void restoreScreen(){
		Window w = vc.getFullScreenWindow();
		if(w != null){
			w.dispose();
		}
		vc.setFullScreenWindow(null);
                System.exit(0);
	}
	
	
	/*
	 * make image compatible with this display monitor
	 * get characteristics of our monitor
	 * width, height, transparency
	 * 
	 */
	public BufferedImage createCompatibleImage(int w, int h, int t){
		Window win = vc.getFullScreenWindow();
		if(win != null){
			GraphicsConfiguration gc = win.getGraphicsConfiguration();
			return gc.createCompatibleImage(w, h, t);
		}
		return null;
	}

 
	
}//
