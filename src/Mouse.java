import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class Mouse extends MouseAdapter {
	
	private int x;
	private int y;
	
	public Mouse() {}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
		
		if(SwingUtilities.isLeftMouseButton(e)) {
			System.out.print("left click. ");
			System.out.println(x+", "+y);
		}
		else {
			System.out.print("right click. ");
			System.out.println(x+", "+y);
		}
	}
}
