import java.awt.*;
import javax.swing.*;

public class DrawPanel extends JPanel {
	public void paintComponent(Graphics g){
		g.setColor(Color.orange);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		int red = (int) (Math.random()*256);
		int green = (int) (Math.random()*256);
		int blue = (int) (Math.random()*256);
		
		Color randomColor = new Color(red, green, blue);
		g.setColor(randomColor);
		g.fillOval(70, 70, 100, 100);
		}

}
