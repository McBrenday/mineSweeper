import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class MineButton extends JButton implements MouseListener {

    Dimension size = new Dimension(20, 20);
    private int x = 0;
    boolean hover = false;
    boolean click = false;
    String text;
    private int row;
    private int col;
    private int value;
    private boolean hasBeenClicked = false;
    private Icon norm; private Icon emptyClicked;
    private Icon type; private Icon flagIcon;
    private Cords cord;
    private boolean flagState = false;
    static int totalFlags;
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
          if(e.getModifiers() == MouseEvent.BUTTON3_MASK) {
              totalFlags++;
            flag();
        } 
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void show() {
     if(!hasBeenClicked && !isFlagged()) {     
     hasBeenClicked = true;
     setIcon(emptyClicked);
     changeIcon();
    }
    }
    public boolean isFlagged() {
        return flagState;
    }
    public void flag() {
        if(!hasBeenClicked) {
        flagState = !flagState;
        if(flagState) {
            setIcon(flagIcon);
        }
        else {
            setIcon(norm);
        }
    }
    }
    public boolean hasShown() {
     return hasBeenClicked;   
    }
    public void mineReset(int v) {
        hasBeenClicked = false;
        flagState = false;
        setIcon(norm);
        changeValue(v);
    }
    public void changeValue(int v) {
        value = v;
    }
    public void addValue(int v) {
        value += v;
        
    }
    public int getValue() {
     return value;   
    }
    public void xFlag() {
        try {       
         String iconPath = "10.png";
        Image icon = ImageIO.read(new File(iconPath));
        type = new ImageIcon(icon);
        setIcon(type);      
    }  
    catch (IOException ex) {
            ex.printStackTrace();
    }
    }
    public void changeIcon() {
     try {       
         String iconPath = "" +value +".png";
        Image icon = ImageIO.read(new File(iconPath));
        type = new ImageIcon(icon);
        setIcon(type);      
    }  
    catch (IOException ex) {
            ex.printStackTrace();
    }
    }
    public Cords getCords() {
        return cord;
    }
    public MineButton(String text, int r, int c, int s, int v) {
        totalFlags = s;
        setUp(text, v);
        cord = new Cords(r, c);
    }
    public void setUp(String text, int v) {
        setVisible(true);
        setFocusable(true);
        setContentAreaFilled(true);
        setBorderPainted(true);
        setBorder(new LineBorder(Color.BLACK, 0));
        setPreferredSize(size);
        setMaximumSize(size);
        
        //set image
        try {
            String iconPath = "norm.png";
            Image icon = ImageIO.read(new File(iconPath));
            norm = new ImageIcon(icon);
            setIcon(norm);
        
         String fl = "flag.png";
          icon = ImageIO.read(new File(fl));
          flagIcon = new ImageIcon(icon);
        
        iconPath = "0.png";
        icon = ImageIO.read(new File(iconPath));
        emptyClicked = new ImageIcon(icon);
        }   catch (IOException ex) {
            ex.printStackTrace();
        }
        validate();
       //classAlcBtn.setIcon(icon);
        value = v;
        addMouseListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(255, 255, 255));
    }

}
