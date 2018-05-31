import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class optionMenu extends JButton implements MouseListener {

    Dimension size = new Dimension(20, 20);
    private int x = 0;
    boolean hover = false;
    boolean click = false;
    String text;
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public optionMenu() {
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
      
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(new Color(255, 255, 255));
        // initialise instance variables
        x = 0;
    }

}
