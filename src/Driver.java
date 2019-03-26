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
	String one_img = "Minesweeper1_32x32.png";
	String two_img = "Minesweeper2_32x32.png";
	String three_img = "Minesweeper3_32x32.png";
	String four_img = "Minesweeper4_32x32.png";
	String flag_img = "MinesweeperFlag64x64.png";
	String small_flag_img = "MinesweeperFlag32x32.png";
	
	//variables
	Mouse mouse = new Mouse();												//mouse used for right and left click
	int timerDelay = 1000;													//delay in milliseconds of timer
	int secondsPassed = 0;													//seconds that have passed since user started game
	int flagCount = mouse.getFlagCount();														//26 - # of flags placed
	JLabel timeDisplay = new JLabel(Integer.toString(secondsPassed));		//holds the same value as secondPassed but in JLabel form
	JLabel flagCountDisplay = new JLabel(Integer.toString(flagCount));		//holds the same value as flagCount but in JLabel form
	Board board = new Board();												//board object created just to use its functionality/ methods
	HashMap<String, Tile> gameBoard = board.generateBoard();				//gameBoard which is the hub of all tile objects
	boolean lostTheGame = false;											//game status; have they lose yet?
	
	ActionListener taskPerformer = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	    	//increments secondsPassed every second
	    	secondsPassed++;
	    }
	};
	
	Timer timer = new Timer(timerDelay, taskPerformer);
	
	//only do drawing for paint
	public void paint(Graphics g) {
		super.paintComponent(g);
	}//end of paint method - put code above for anything dealing with drawing -
	
	/* do not draw here */
	public void update() {
		
		//updating the display of the timer
		timeDisplay.setText(Integer.toString(secondsPassed));
		
		//updating the display of flag count
		flagCountDisplay.setText(Integer.toString(mouse.getFlagCount()));
		
		//if you lost the game then show the sad face image
		if(lostTheGame) { 
		timer.stop();
		lose.setVisible(true);
		}
		
		//click and disappear Tiles
		for(int r = 0; r < board.getRows(); r++) {
			for(int c = 0; c < board.getCols(); c++) {
				
				Tile tempTile = gameBoard.get(r+" "+c);
				
				if(mouse.getX()>tempTile.getX()&&
				mouse.getX()<tempTile.get_x()&&
				mouse.getY()>tempTile.getY()&&
				mouse.getY()<tempTile.get_y()) {
					
					if(mouse.isLeftClick()) {
						//if you click on a bomb tile then you lose
						if(tempTile.getBomb()) {
							lostTheGame = true;
						}
						
						tempTile.setDiscovered(true);
						tempTile.getImg().setVisible(false);
					}
					
					if((!mouse.isLeftClick())&&(!tempTile.isFlagged())) {
						tempTile.getFlag_img().setVisible(true);
					}
					
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
		playMusic("Minesweeper_Game_Boy_Theme-7zrL98CGCEQ2.wav");
	}
	
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
		
		//timer implementation
		timeDisplay.setForeground(Color.RED);
		timeDisplay.setBounds(620,67,120,50);
		timeDisplay.setFont(timeDisplay.getFont().deriveFont(64.0f));
		f.add(timeDisplay);
		timer.start();
		
		//flag count display implementation
		flagCountDisplay.setForeground(Color.RED);
		flagCountDisplay.setBounds(80,67,120,50);
		flagCountDisplay.setFont(flagCountDisplay.getFont().deriveFont(64.0f));
		f.add(flagCountDisplay);
		
		//mouse input
		f.getContentPane().addMouseListener(mouse);
		
		//creating all tiles on board
		for(int r = 0; r < board.getRows(); r++) {
			for(int c = 0; c < board.getCols(); c++) {
				
				Tile tempTile = gameBoard.get(r+" "+c);	//accesses each tile from the gameBoard hashmap
				tempTile.setX((c*48)+48);				//converts the index coordinates to pixel coordinates for GUI placement
				tempTile.setY((r*48)+216);				//converts the index coordinates to pixel coordinates for GUI placement
				
				//adding flag image
				f.add(tempTile.getFlag_img());
				
				//adding tile image to GUI
				f.add(tempTile.getImg());
				tempTile.getFlag_img().setVisible(false);
				
				//initialize all adjBomb variables of each tile
				tempTile.fillAdjTiles(r, c, gameBoard);
				int temp = tempTile.getAdjBombTiles().size();
				tempTile.setAdjBombs(temp);
				
				if(tempTile.getBomb()) {
					
					//adding bomb image
					ImageIcon bmb = new ImageIcon(src+bomb_img);
					JLabel bomb = new JLabel(bmb);
					bomb.setBounds(((c*48)+35), ((r*48)+205), 64, 64);
					f.add(bomb);
					
				}
				
				//add number display of adjacent bombs here
				String temp_img ="";
				if(tempTile.getAdjBombs()==1) {
					temp_img = one_img;
				}
				
				if(tempTile.getAdjBombs()==2) {
					temp_img = two_img;
				}
				
				if(tempTile.getAdjBombs()==3) {
					temp_img = three_img;
				}
				
				if(tempTile.getAdjBombs()==4) {
					temp_img = four_img;
				}
				
				ImageIcon num = new ImageIcon(src+temp_img);
				JLabel number = new JLabel(num);
				number.setBounds(((c*48)+35), ((r*48)+205), 64, 64);
				f.add(number);
				
			}
		}
		
		//adding flag image
		ImageIcon fg = new ImageIcon(src+flag_img);
		JLabel flag = new JLabel(fg);
		flag.setBounds(210, 65, 64, 64);
		f.add(flag);
		
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