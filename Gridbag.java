import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
// Known Bugs/To DO List:

// BUG: Phantom Mines - PATCHED
// Sometimes the mine is not where it should be,
// Making there be a fake mine there, which causes
// Everything around it to be considered a value
// Most likely the first mine found, which is causing the bug.

// If there is NOT a zero spot below the tile, it will not recurr.
// It will also not recurr if the tile hasShown();

// Looks at the mine below the tile clicked causing 
// Recurssion to not happen sometimes

// Recurssion doesn't always occur when it's a zero tile
// probably something to do with the clicking mechanisms

//When clicking near the borders, it takes a few clicks
// or basically is not searching everything properly with
// the given values

// Add a win condition variable with the amoutn of mines
// Flagged and if all are correctly flagged and every mine
// is shown

//Sometimes the all the tiles are shown when clicked, very rare occasion.
public class Gridbag extends JPanel implements ActionListener
 {
      private GridBagConstraints cst;
      private GridBagLayout grid;
      int numberOfButtons;
      private ResetButton re; private optionMenu opMenu;
      private MineButton buttonFace;
      private MineButton[][] game;
      private static int rows =16; private static int cols = 32; private int numberOfMines;
      private int minesLeft;
     private ArrayList<Cords> mineLocs = new ArrayList<Cords>();
     static int tilesLeft; private boolean firstClick = false;
     static int totalFlags = 0;
     static int theRow = (rows+1 )* 40 +20;
     static int theCol = cols * 40 +20;
     
     private int tilesShown = 0;
      public Gridbag()                   // add buttons to grid
      {
          cst = new GridBagConstraints();
          grid = new GridBagLayout();
          setLayout(grid);                       // set layout of Jpanel to GridBagLayout
          // add button 1 to the panel  at 0,0
          //cst.fill = GridBagConstraints.HORIZONTAL;
            numberOfMines = 99;
            minesLeft = numberOfMines;
            cst.gridx = 0; cst.gridy = 0;
            opMenu = new optionMenu();
            this.add(opMenu);
            opMenu.addActionListener(this);
            add(opMenu, cst);
            
          cst.gridx = rows/2;
          tilesLeft = rows * cols;
          re = new ResetButton();
          this.add(re);
          re.addActionListener(this);
          add(re, cst);
          game = new MineButton[rows][cols];
          for(int r = 1; r <= rows; r++) {
              for(int c = 0; c < cols; c++) {
                  cst.gridx = c; cst.gridy = r;
                  buttonFace = new MineButton("M", r-1, c, totalFlags, getValue()); 
                  this.add(buttonFace);
                  buttonFace.addActionListener(this);
                  add(buttonFace, cst);
                  game[r-1][c] = buttonFace;
                }
            }  
            
            setMines();
        }
    public int getValue() {
        return 0;
    }
    public void setMines() {
        mineLocs = new ArrayList<Cords>();
        //int ro = (int) (Math.random() * rows);
        //int co = (int) (Math.random() * cols);
        //Cords temp = new Cords(ro, co);
        //mineLocs.add(temp);
        firstClick = false;
        for(int num = 0; num < numberOfMines; num++) {
            int ro = (int) (Math.random() * rows);
            int co = (int) (Math.random() * cols);
            Cords temp = new Cords(ro, co);
            game[ro][co].changeValue(9);
            mineLocs.add(num, temp);
        }  
        for (int x = 0; x < mineLocs.size() -1; x++) {
            for (int y = x+1; y < mineLocs.size(); y++) {
                if(x >= 0 && x < mineLocs.size()) {
                Cords cx = mineLocs.get(x);
                Cords cy = mineLocs.get(y);
                if(cx.getRow() == cy.getRow() && cx.getCol() == cy.getCol()) {
                    mineLocs.remove(x);
                    x--;
                    y--;
                }
            }
            }
        }
        for(Cords cord : mineLocs) {
            int row= cord.getRow();
            int col = cord.getCol();          
            for(int r = row -1; r <= row+1; r++) { 
                if(r >= 0 && r < rows) {
               for(int c= col -1; c <= col+1; c++) {
                  if(c >= 0 && c <cols) {
                  if(game[r][c].getValue() < 9)  
                     game[r][c].addValue(1);  
                    }
                }
            }
            }
        }
    }
   
    public void resetTiles() {
        minesLeft = numberOfMines;
        tilesShown = 0;
        re.defaultIcon();
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                game[r][c].mineReset(0);
            }
        }
        setMines();
    }
    public void actionPerformed(ActionEvent e) { 
     if(e.getSource() == re) {
          resetTiles();
        }
     //else if(e.getSource() instanceof MineButton)  {
         else {
          
        MineButton use = (MineButton) e.getSource();
        while(firstClick == false && use.getValue() != 0) {
            int row = use.getCords().getRow(); int col = use.getCords().getCol();
            resetTiles();
            if(game[row][col].getValue() == 0) {
            firstClick = true;
           }
           use = game[row][col];
        }
        firstClick = true;
        if(use.getValue() == 0 && !use.isFlagged()) {
          findSurronding(use.getCords().getRow(), use.getCords().getCol());
          return;
        }
        else
            tilesShown++;
        if(use.getValue() == 9 && !use.isFlagged()) {
            re.looseIcon();
            for(int r = 0; r < rows; r++) {
                for(int c = 0; c < cols; c++) {
                    if(!game[r][c].hasShown()) {
                    if(game[r][c].getValue() != 9 && game[r][c].isFlagged())
                       game[r][c].xFlag();
                    else if(game[r][c].getValue() != 9)
                    game[r][c].show();
                    else if(!game[r][c].isFlagged() && game[r][c].getValue() == 9) {
                        game[r][c].show();
                    }
                }
                }
            }
        }
        else if(tilesShown == rows * cols - numberOfMines) {
            re.winIcon();
        }
        else
         use.show();
    }
    }
    
    
    public void findSurronding(int row, int col) {
        if(row >= 0 && col >= 0 && row < rows && col < cols) {
        if(game[row][col].hasShown() == false) {
            game[row][col].show();
            tilesShown++;
            System.out.println(tilesShown);
            if(game[row][col].getValue() == 0) {
                findSurronding(row-1, col-1);
                findSurronding(row-1, col);
                findSurronding(row-1, col+1);
                findSurronding(row, col-1);
                findSurronding(row, col+1); 
                findSurronding(row+1, col-1);
                findSurronding(row+1, col);
                findSurronding(row+1, col+1);
            }   
        }  
    }
    }
    
    public static void main(String[] args) {
 
        JFrame frame = new JFrame("MineSweeper BY");
        Gridbag panel = new Gridbag();                      // new Jpanel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(theCol, theRow);
        frame.add(panel);
        frame.setVisible(true);
    }
}
