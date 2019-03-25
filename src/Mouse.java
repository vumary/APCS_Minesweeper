import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class Mouse extends MouseAdapter {
	
	private int x;
	private int y;
	private boolean leftClick;
	
	public Mouse() {}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
		
		if(SwingUtilities.isLeftMouseButton(e)) {
			leftClick = true;
		}
		else {
			leftClick = false;
		}
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
	
	
	
}
