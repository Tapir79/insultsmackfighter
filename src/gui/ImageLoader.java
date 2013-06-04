/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import gameobjects.PlayerAnimationLoader;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author lentonen_admin
 */
public class ImageLoader {
    
    static ImageIcon bg = new ImageIcon(ImageLoader.class.getResource("/images/bg.gif"));
    static ImageIcon player = new ImageIcon(ImageLoader.class.getResource("/images/torvalds1.gif"));
    static ImageIcon player2 = new ImageIcon(ImageLoader.class.getResource("/images/gates1.gif"));

    
    
//    static  BufferedImage bufbg = loadBufImage("/images/bg.gif");
   
    public static  Image getBG(){
        return bg.getImage();
    }
    
    public static Image getPlayer(){
        return player.getImage();
    }
    public static Image getPlayer2(){
        return player2.getImage();
    }    
    
    
    public static Image loadImage(String nimi, int jarjnro, String failityyppi){
        System.out.println("entering imageloader");
         System.out.println("Trying to load image "+nimi+jarjnro+"."+failityyppi);
        ImageIcon uusi = new ImageIcon(ImageLoader.class.getResource("/images/"+nimi+jarjnro+"."+failityyppi));
        System.out.println("loaded.");
       
        return uusi.getImage();
    }
    
   
   

}
