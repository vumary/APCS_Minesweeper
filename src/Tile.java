import java.util.ArrayList;
import java.util.HashMap;

public class Tile {
	
	private String coordinate;
	private boolean isBomb;
	private int adjBombs; 
	private boolean isDiscovered = false;
	
	//NON-BOMB TILE
	public Tile(String coord, boolean bomb, int adjacent) {
		coord = coordinate; 
		bomb = isBomb;
		adjacent = adjBombs; 
	}
	
	//BOMB TILE
	public Tile(String coord, boolean bomb) {
		coord = coordinate; 
		bomb = isBomb;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public boolean isBomb() {
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
	
	
}





