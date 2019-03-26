import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class Mouse extends MouseAdapter {
	
	private int x;
	private int y;
	private boolean leftClick;
	private int flagCount = 26;
	
	public Mouse() {}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
		System.out.println("hi");
		if(SwingUtilities.isLeftMouseButton(e)) {
			leftClick = true;
		}
		else {
			flagCount--;
			leftClick = false;
		}
	}

	public void decrement() {
		flagCount--;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isLeftClick() {
		return leftClick;
	}

	public void setLeftClick(boolean leftClick) {
		this.leftClick = leftClick;
	}

	public int getFlagCount() {
		return flagCount;
	}

	public void setFlagCount(int flagCount) {
		this.flagCount = flagCount;
	}
	
}
