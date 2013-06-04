/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author lentonen_admin
 */
public interface Sprite {
    
    public void update(long timePassed, int screenWidth, int screenHeight);
    public void setX(int sx);
    public void setY(int sy);
    public int getX();
    public int getY();
    public Image getCurrentImage();
    public Rectangle getMyRectangle();
    public boolean checkScreenCollision(int screenWidth, int screenHeight); 
    public int getWidth();
    public int getHeight();
}
