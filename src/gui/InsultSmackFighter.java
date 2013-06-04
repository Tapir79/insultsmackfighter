/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import gameobjects.GameManager;
import gameobjects.Player;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;



public class InsultSmackFighter implements ActionListener{

	public static void main(String[] args){
		new InsultSmackFighter().run();              
	}
	
	// is the game running
    private static boolean running;
    protected ScreenManager s;
    private long timePassed;
    protected Graphics2D g;
    private Timer gameTime;
    
    private Player torvalds;
    private Player gates;
    private GameManager pmanager;
    public static Graphics2D drawer;
	
	// stops the game
	public static void stop(){
		running = false;
	}
	
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			s.restoreScreen();
		}
	}
	
	/*
	 * initializing the game
	 */
	public void init(){
		s = new ScreenManager();
		DisplayMode dm = s.findFirstCompatibleMode();
		s.setFullScreen(dm); // FULL SCREEN WINDOW
		Window w = s.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN,20));
		w.setBackground(Color.black);
		w.setForeground(Color.black);
		running = true;
                int animationTime = 30;
                // kaikki muu peli-initialisointi tänne 
                gameTime = new Timer(10,this);    
                // torvalds x =0, y = 200, runleft
                torvalds = new Player("torvalds",0,200,1);
                torvalds.loadAnimations(1, 1, 1, 1, animationTime, "torvalds", "gif");
                //gates x=500, y=200, runright
                gates = new Player("gates",500,200,2);
                gates.loadAnimations(1, 3, 1, 3, animationTime, "gates", "gif");
                pmanager.setPlayers(torvalds,gates);
                gameTime.start();
                
	}

        /**
	 * main gameloop
	 * First we get the current time for the starting time
	 * cumTime is the cumulative time and it starts from starting time
	 * while running = true (stop() is not called)
	 * 
	 */
	public void gameLoop(){
                
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		
		while(running){
			timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			
			//draw and update the screen
			g = s.getGraphics();
			draw(g);
			g.dispose();
			s.updateScreen();
	
			try{
				Thread.sleep(30);
			}catch(Exception ex){}
			
		}
	}
        
	
        @Override
        public void actionPerformed(ActionEvent e) {
            UpdateAll.update(timePassed, s.getWidth(),s.getHeight());   
        }
	/*
	 * draw method, which background, what NPCs what objects
	 */
	public synchronized void draw(Graphics2D g){		
                Toolkit.getDefaultToolkit().sync();    
		//Window w = s.getFullScreenWindow();
                this.drawer = (Graphics2D) g;
                DrawAll.drawAll();
	}
        
     

}//end class

