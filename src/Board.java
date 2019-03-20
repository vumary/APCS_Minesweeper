import java.util.ArrayList;
import java.util.HashMap;


public class Board {
	

	private HashMap<Coordinate, Tile> gameBoard = new HashMap<Coordinate, Tile>();	//hold all tiles
	private int rows;																//amount of "rows" in hashmap
	private int cols; 																//amount of "cols" in hashmap
	private int bombCount;															//amount of bombs on the board
	private ArrayList<Coordinate> bombCoords = new ArrayList<Coordinate>();			//contains the coordinates of every bomb

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
	
	public boolean inBombCoords(Coordinate coord) {
		for(int i = 0; i < bombCoords.size(); i++) {
			
			//checking if the coordinate is already in the bombCoords
			if(bombCoords.get(i).getCol()==coord.getCol()&&
			bombCoords.get(i).getRow()==coord.getRow()) {
				return true;
			}
			
		}
		
		return false;
	}
	
	//generate 26 bombs then fill the rest of the board with none bomb tiles
	public HashMap<Coordinate, Tile> generateBoard(){ 
		
		/*
		 *GENERATING BOMB TILES
		 */
		
		for(int i =0; i < bombCount; i++){	
			
			int bR = (int)(Math.random() * (rows+1)); 				//random int between 0 and 11; randomly assigned row
			int bC = (int)(Math.random() * (cols+1)); 				//random int between 0 and 15; randomly assigned col
			Coordinate coords = new Coordinate (bR, bC);			//creating a new coordinate respective to the bombs location
			
			//while the coords are in the bombCoord
			//re-assign the coordinate b/c we don't want bombs to overlap
			while(inBombCoords(coords)) {
				bR = (int)(Math.random() * 12); 				//random int between 0 and 11; randomly assigned row
				bC = (int)(Math.random() * 16); 				//random int between 0 and 15; randomly assigned col
				coords = new Coordinate (bR, bC);			//creating a new coordinate respective to the bombs location

			}
			
			Tile bTile = new Tile(coords, true, 9);			//the bomb tiles; if bombadj value is nine then its a bomb tile
			gameBoard.put(coords, bTile);					//adding the tiles to the gameboard at their respective coordinate key
			bombCoords.add(coords);							//adding the coordinate of each bomb to bombCoords
			
		}; 
		
		/*
		 *GENERATING NON-BOMB TILES
		 */
		
		for(int r = 0; r < 11; r++){  
			for (int c = 0; c< 15; c++){
				Coordinate coords = new Coordinate(r,c);
				if (gameBoard.get(coords) != null ){
					continue;
				}
				Tile tile = new Tile(coords, false, 0); // generate non bomb tiles, for now with adjbombs set as 0 
				gameBoard.put(coords, tile); 
				System.out.println("STUCK IN NON-BOMB GENERATION");
			}
		}
		
		return gameBoard; 
	}

}



