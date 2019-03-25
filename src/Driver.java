import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Driver extends JPanel implements ActionListener, KeyListener {
	
	//properties of this class - the panel that shows up
	int screen_width = 800;
	int screen_height = 800;
	int max_vals = 200;
	JLabel background;
	JLabel lose;
	//String of images' filenames below
	String background_img = "MinesweeperBoard800x800.png";
	String hourglass_img = "MinesweeperHourglass64x64.png";
	String lose_img = "MinesweeperDeadFace64x64.png";
	String win_img = "MinesweeperHappyFace64x64.png";
	String singleTile_img = "MinesweeperSingleTile_40x40.png";
	String bomb_img = "MinesweeperBomb32x32.png";
	
	//variables
	int timerDelay = 1000;													//delay in milliseconds of timer
	int secondsPassed = 0;													//seconds that have passed since user started game
	JLabel timeDisplay = new JLabel(Integer.toString(secondsPassed));		//holds the same value as seconds passed but in JLabel form
	Board board = new Board();
	HashMap<String, Tile> gameBoard = board.generateBoard();
	Mouse mouse = new Mouse();
	boolean lostTheGame = false;
	
	//only do drawing for paint
	public void paint(Graphics g) {
		super.paintComponent(g);
	}//end of paint method - put code above for anything dealing with drawing -
	
	/* do not draw here */
	public void update() {
		
		//updating the display of the timer
		timeDisplay.setText(Integer.toString(secondsPassed));
		
		//if you lost the game then show the sad face image
		if(lostTheGame) { 
		lose.setVisible(true);
		}
		
		//click and disappear Tiles
		for(int r = 0; r < board.getRows(); r++) {
			for(int c = 0; c < board.getCols(); c++) {
				
				Tile tempTile = gameBoard.get(r+" "+c);
				
				if(mouse.getX()>tempTile.getX()&&
				mouse.getX()<tempTile.get_x()&&
				mouse.getY()>tempTile.getY()&&
				mouse.getY()<tempTile.get_y()&&
				mouse.isLeftClick()) {
					
					//if you click any tile then an adjTiles stack will be initialized for it
					tempTile.fillAdjTiles(r, c, gameBoard);
					
					//not including diagonals
					System.out.println("AMOUNT OF NEIGHBOR BOMBS: "+tempTile.getAdjBombTiles().size());
					
					//if you click on a bomb tile then you lose
					if(tempTile.getBomb()) {
						lostTheGame = true;
					}
					
					tempTile.setDiscovered(true);
					tempTile.getImg().setVisible(false);
					
				}
				
				
			}
		}
		
	}//end of update method - put code above for any updates on variable
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}
	
	public static void main(String[] arg) {
		Driver d = new Driver();
		
		//music implementation
		//playMusic("Minesweeper_Game_Boy_Theme-7zrL98CGCEQ2.wav");
	}
	
	/*
	//music implementation
	public static void playMusic(String filepath){
			
		    try {
		      File file = new File("Minesweeper_Game_Boy_Theme-7zrL98CGCEQ2.wav");
		      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
		      Clip clip = AudioSystem.getClip();
		      clip.open(stream);
		      clip.start();
		 
		      //sleep to allow enough time for the clip to play
		      Thread.sleep(310200);
		 
		      stream.close();
		 
		    } catch (Exception ex) {
		      System.out.println(ex.getMessage());
		    }
			
		}
		*/
	
	public Driver(){
		
		//starter code
		JFrame f = new JFrame();
		f.setTitle("Click Em");
		f.setSize(screen_width, screen_height);
		f.getContentPane().setBackground(Color.black);
		String src = new File("").getAbsolutePath()+"/src/"; //path to image setup
		f.setResizable(false);
		f.setLayout(null);
		f.addKeyListener(this);
		
		
		System.out.println("TESTING---------------------------------------------------------------");
		
		//board.printBombCoords();
		//System.out.println(board.getBombCoords().size());
		//board.printGameBoard();
		Tile test = gameBoard.get("0 0");
		//System.out.println("test tile: "+test);
		//test.printGameBoard();
		//why do they not share the same gameboard
		
		System.out.println("----------------------------------------------------------------------");
		
		//timer implementation
		timeDisplay.setForeground(Color.RED);
		timeDisplay.setBounds(620,67,120,50);
		timeDisplay.setFont(timeDisplay.getFont().deriveFont(64.0f));
		f.add(timeDisplay);
		ActionListener taskPerformer = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	//increments secondsPassed every second
		    	secondsPassed++;
		    }
		};
		new Timer(timerDelay, taskPerformer).start();
		
		//mouse input
		f.getContentPane().addMouseListener(mouse);
		
		//creating all tiles on board
		for(int r = 0; r < board.getRows(); r++) {
			for(int c = 0; c < board.getCols(); c++) {
				
				Tile tempTile = gameBoard.get(r+" "+c);	//accesses each tile from the gameBoard hashmap
				tempTile.setX((c*48)+48);				//converts the index coordinates to pixel coordinates for GUI placement
				tempTile.setY((r*48)+216);				//converts the index coordinates to pixel coordinates for GUI placement
				f.add(tempTile.getImg());				//add image to GUI
				
				if(tempTile.getBomb()) {
					
					//adding bomb image
					ImageIcon bmb = new ImageIcon(src+bomb_img);
					JLabel bomb = new JLabel(bmb);
					bomb.setBounds(((c*48)+35), ((r*48)+205), 64, 64);
					f.add(bomb);
					
				}
				
			}
		}
		
		
		//adding happy face image
		ImageIcon ls = new ImageIcon(src+lose_img);
		lose = new JLabel(ls);
		lose.setBounds(370,60,64,64);
		f.add(lose);
		lose.setVisible(false);
		
		//adding sad face image
		ImageIcon wn = new ImageIcon(src+win_img);
		JLabel win = new JLabel(wn);
		win.setBounds(370,60,64,64);
		f.add(win);
		
		//adding hour glass image
		ImageIcon hg = new ImageIcon(src+hourglass_img);
		JLabel hourglass = new JLabel(hg);
		hourglass.setBounds(530,65,64,64);
		f.add(hourglass);
		
		//adding the static background
		ImageIcon bg = new ImageIcon(src+background_img);
		background = new JLabel(bg);
		background.setBounds(0,0,800,800); //set location and size of icon
		f.add(background);
		
		//end creating objects
		t = new Timer(17,this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void reset(){
	
	}

	
}