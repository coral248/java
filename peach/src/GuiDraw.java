import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiDraw implements ActionListener{

	JFrame frame;
	
	public static void main(String[] args) {
		GuiDraw gui = new GuiDraw ();
		gui.go();

	}
	
	public void go(){
		frame = new JFrame();
		JButton button = new JButton ("Change colors");
		DrawPanel draw = new DrawPanel();
		button.addActionListener(this);
		frame.getContentPane().add(BorderLayout.CENTER, draw);
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	
	public void actionPerformed (ActionEvent event) {
		frame.repaint();
	}

}
