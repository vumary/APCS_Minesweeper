import java.util.ArrayList;
import java.util.HashMap;


public class Board {
	
	//hashmap that holds information about all the tiles and is our game board
	private HashMap<Coordinate, Tile> gameBoard = new HashMap<Coordinate, Tile>();
	private int rows;
	private int cols; 
	private int bombCount;		//the amount of bombs on the board
	private ArrayList<Coordinate> bombCoords = new ArrayList<Coordinate>();
	
	public Board(int row, int col, int bomb){
		rows = row;
		cols = col; 
	 	bombCount = bomb; 
	}
	
	public HashMap<Coordinate, Tile> generateBoard(){ 
		for(int i =0; i < 26; i++){
			int bR = (int)Math.random() * 12; 				//random int between 0 and 11; randomly assigned row
			int bC = (int)Math.random() * 16; 				//random int between 0 and 15; randomly assigned col
			Coordinate coords = new Coordinate (bR, bC);	//creating a new coordinate respective to the bombs location	
			//checking for duplicate coordinates
			//thinking bc i didnt finish
			//while the coordinates are in the bombCoords 
			//create new random bR and bC
			for(int j = 0; j<bombCoords.size(); j++){
				if(bombCoords.get(j).getCol()==bC&&
				bombCoords.get(j).getRow()==bR){
						
				}
			}
			Tile bTile = new Tile(coords, true, 9);			//the bomb tiles; if bombadj value is nine then its a bomb tile
			gameBoard.put(coords, bTile);					//adding the tiles to the gameboard at their respective coordinate key
			bombCoords.add(coords);							//adding the coordinate of each bomb to bombCoords
			
		}; 
		
		for(int i = 0; i < 139; i++){
			
		}
		
		return gameBoard; 
	}

}



