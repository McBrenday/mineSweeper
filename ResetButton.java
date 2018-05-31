import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ResetButton extends JButton implements MouseListener {

    Dimension size = new Dimension(20, 20);
    private int x = 0;
    boolean hover = false;
    boolean click = false;
    String text;
    private Icon defaultIcon; private Icon win; private Icon loose;
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public ResetButton() {
        setVisible(true);
        setFocusable(true);
        setContentAreaFilled(false);
        setBorderPainted(true);
        setBorder(new LineBorder(Color.BLACK, 1));
        setPreferredSize(size);
        //* ImageIcon icon = new ImageIcon("whatever.jpg");
        ////Image img = icon.getImage() ;  
       //Image newImg = img.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
      //icon = new ImageIcon( newImg );

       //classAlcBtn.setIcon(icon);
       //             this.add(re);
       
       try {       
         String iconPath = "reset.png";
        Image di = ImageIO.read(new File(iconPath));
        defaultIcon = new ImageIcon(di);
        setIcon(defaultIcon);      
        
        iconPath = "win.png";
        di = ImageIO.read(new File(iconPath));
        win = new ImageIcon(di);
        
        iconPath = "loose.png";
        di = ImageIO.read(new File(iconPath));
        loose = new ImageIcon(di);
        }  
       catch (IOException ex) {
            ex.printStackTrace();
       }
       
        addMouseListener(this);
    }
    
    public void defaultIcon() {
        setIcon(defaultIcon);   
    }
    public void winIcon() {
        setIcon(win);
    }
    public void looseIcon() {
        setIcon(loose);
    }
}
