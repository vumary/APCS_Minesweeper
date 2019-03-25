import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;


public class Board {
	

	protected HashMap<String, Tile> gameBoard = new HashMap<String, Tile>();	//hold all tiles
	protected int rows;														//amount of "rows" in hashmap
	protected int cols; 														//amount of "cols" in hashmap
	private int bombCount;													//amount of bombs on the board
	private ArrayList<String> bombCoords = new ArrayList<String>();			//contains the coordinates of every bomb

	//default constructor: an 11x15 (rowsxcolumns) game board with 26 bombs and 139 non-bomb tiles
	public Board(){
		this.rows = 11;
		this.cols = 15;
		this.bombCount = 26;
	}
	
	public Board(int rows, int cols, int bombCount){
		this.rows = rows;
		this.cols = cols; 
	 	this.bombCount = bombCount; 
	}
	
	public void printBombCoords() {
		for(int i = 0; i < bombCoords.size(); i++) {
			System.out.println(bombCoords.get(i));
		}
	}
	
	//if the coord is in bombCoords then return true
	public boolean inBombCoords(String coord) {
		
		for(int i = 0; i < bombCoords.size(); i++) {
			
			if(bombCoords.get(i).equals(coord)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void printGameBoard() {
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				System.out.println(gameBoard.get(r+" "+c));
			}
		}
	}
		
	//generate 26 bombs then fill the rest of the board with none bomb tiles
	public HashMap<String, Tile> generateBoard(){ 
		
		/*
		 *GENERATING BOMB TILES
		 */

		for(int i =0; i < bombCount; i++){	
			
			Random rand = new Random();
			String bR = rand.nextInt(rows)+"";	//whole number [0, 11)
			String bC = rand.nextInt(cols)+"";	//whole number [0, 15)
			String coords = bR+" "+bC;
			
			//re-assign the coordinate b/c we don't want bombs to overlap
			while(inBombCoords(coords)) {
				bR = rand.nextInt(rows)+""; 				//random int between 0 and 11; randomly assigned row
				bC = rand.nextInt(cols)+""; 				//random int between 0 and 15; randomly assigned col
				coords = bR+" "+bC;							//creating a new coordinate respective to the bombs location
			}
			
			Tile bTile = new Tile(coords, true);
			gameBoard.put(coords, bTile);					//adding the bomb tiles to the gameboard at their respective coordinate key
			bombCoords.add(coords);							//adding the coordinate of each bomb to bombCoords
			
		}; 
		
		/*
		 *GENERATING NON-BOMB TILES
		 */

		for(int r = 0; r < rows; r++){  
			for (int c = 0; c< cols; c++){
				
				String tempCoord = r+" "+c;
				
				//if row and column is NOT in bomb coords
				if(!inBombCoords(tempCoord)) {
					
					//need to create a non-bomb tile with adjacent things
					Tile tempTile = new Tile(tempCoord, false);
					gameBoard.put(tempCoord, tempTile);
					
				}
			}
		}
		
		return gameBoard; 
	}

	public HashMap<String, Tile> getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(HashMap<String, Tile> gameBoard) {
		this.gameBoard = gameBoard;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getBombCount() {
		return bombCount;
	}

	public void setBombCount(int bombCount) {
		this.bombCount = bombCount;
	}

	public ArrayList<String> getBombCoords() {
		return bombCoords;
	}

	public void setBombCoords(ArrayList<String> bombCoords) {
		this.bombCoords = bombCoords;
	}
	
	

}



