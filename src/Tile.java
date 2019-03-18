import java.util.ArrayList;
import java.util.HashMap;

public class Tile {
	
	private Coordinate coordinate;
	private boolean isBomb;
	private int adjBombs; 
	
	public Tile( Coordinate coord, boolean bomb, int adjacent) {
		coord = coordinate; 
		bomb = isBomb;
		adjacent = adjBombs; 
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
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





