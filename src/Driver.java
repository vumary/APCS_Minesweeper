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
	
	//variables
	int timerDelay = 1000;													//delay in milliseconds of timer
	int secondsPassed = 0;													//seconds that have passed since user started game
	JLabel timeDisplay = new JLabel(Integer.toString(secondsPassed));		//holds the same value as seconds passed but in JLabel form
	Board board = new Board();
	private HashMap<Coordinate, Tile> gameBoard = board.generateBoard();
	
	//only do drawing for paint
	public void paint(Graphics g) {
		super.paintComponent(g);
	}//end of paint method - put code above for anything dealing with drawing -
	
	/* do not draw here */
	public void update() {
		
		//updating the display of the timer
		timeDisplay.setText(Integer.toString(secondsPassed));
		
		if(secondsPassed>3) {	//change this if statement later according to bombs
		//lose condition
		lose.setVisible(true);
		}
		
	}//end of update method - put code above for any updates on variable
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}
	
	public static void main(String[] arg) {
		Driver d = new Driver();
		playMusic("Minesweeper_Game_Boy_Theme-7zrL98CGCEQ2.wav");
	}
	
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
		
		
		/*
		 * 
		 * testing below
		 * 
		 */
		
		
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
		f.getContentPane().addMouseListener(new Mouse());

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