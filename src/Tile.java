import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tile extends Board{
	
	private String coordinate;
	private boolean isBomb;
	private int adjBombs; 
	private boolean isDiscovered = false;
	private JLabel img;
	private int x = 0, y = 0;				//pixel coordinate of where the top left corner of the tile is located
	private int _x, _y; 					//pixel coordinate of where the bottom right corner of the tile is located
	private int width = 40, height = 40;
	//NOTE: .push() to add from top / .pop() to remove from top
	private Stack adjBombTiles = new Stack<Tile>();								//contains all the neighbor tile objects
	private boolean notDone = true;
	
	//NON-BOMB TILE CONSTRUCTOR
	public Tile(String coordinate, boolean isBomb, int adjBombs) {
		
		//JLabel that contains the same single tile image for each Tile object
		String src = new File("").getAbsolutePath()+"/src/";
		ImageIcon ghost = new ImageIcon(src+"MinesweeperSingleTile_40x40.png");
		img = new JLabel(ghost); //connect 
		
		//bound img to the objects
		img.setBounds(x, y, width, height);
		
		//argument variables
		this.coordinate = coordinate;
		this.isBomb = isBomb;
		this.adjBombs = adjBombs;
	}
	
	//BOMB TILE CONSTRUCTOR
	public Tile(String coordinate, boolean isBomb) {
		
		//JLabel that contains the same single tile image for each Tile object
		String src = new File("").getAbsolutePath()+"/src/";
		ImageIcon ghost = new ImageIcon(src+"MinesweeperSingleTile_40x40.png");
		img = new JLabel(ghost); //connect 
		
		//bound img to the object
		img.setBounds(x, y, width, height);
		
		this.coordinate = coordinate; 
		this.isBomb = isBomb;
	}
	
	//adds all neighbor tiles to one stack within that center tile
		public void fillAdjTiles(int r, int c, HashMap<String, Tile> gameBoard) {
			if(notDone) {
			
			notDone = false;
			//adding the left tile to the stack
			if(r-1>0) {
				
				//if tile isn't discovered then add it to the adjBombStack stack
				if(gameBoard.get((r-1)+" "+c).isBomb()) {
					adjBombTiles.push(gameBoard.get((r-1)+" "+c));
				}
				
			}
			
			//adding the right tile to the stack
			if(r+1<rows-1) {
				
				//if tile isn't discovered then add it to the adjBombStack stack
				if(gameBoard.get((r+1)+" "+c).isBomb()) {	
					adjBombTiles.push(gameBoard.get((r+1)+" "+c));
				}
				
			}
			
			//adding the top tile to the stack
			if(c-1>0) {
				
				//if tile isn't discovered then add it to the adjBombStack stack
				if(gameBoard.get(r+" "+(c-1)).isBomb()) {
					adjBombTiles.push(gameBoard.get(r+" "+(c-1)));
				}
			
			}
			
			//adding the bottom tile to the stack
			if(c+1<cols-1) {
				
				//if tile isn't discovered then add it to the adjBombStack stack
				if(gameBoard.get(r+" "+(c+1)).isBomb()) {
					adjBombTiles.push(gameBoard.get(r+" "+(c+1)));
				}
				
			}
			}
			
		}


	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public boolean getBomb() {
		return isBomb;
	}

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}

	public int getAdjBombs() {
		return adjBombs;
	}

	public void setAdjBombs(int adjBombs) {
		this.adjBombs = adjBombs;
	}

	public boolean isDiscovered() {
		return isDiscovered;
	}

	public void setDiscovered(boolean isDiscovered) {
		this.isDiscovered = isDiscovered;
	}

	public JLabel getImg() {
		return img;
	}

	public void setImg(JLabel img) {
		this.img = img;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		img.setBounds(x, y, width, height);
		this.x = x;
		this._x = x + 40;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		img.setBounds(x, y, width, height);
		this.y = y;
		
		this._y = y + 40;
	}

	public int get_x() {
		return _x;
	}

	public void set_x(int _x) {
		this._x = _x;
	}

	public int get_y() {
		return _y;
	}

	public void set_y(int _y) {
		this._y = _y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Stack getAdjBombTiles() {
		return adjBombTiles;
	}

	public void setAdjTiles(Stack adjTiles) {
		this.adjBombTiles = adjTiles;
	}

	public boolean isBomb() {
		return isBomb;
	}
	
}





