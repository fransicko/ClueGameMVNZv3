package clueControlGUI;

import java.awt.Color;
import java.awt.Graphics;

public class targetBox {
	private static int height = 20;
	private static int width = 20;
	private Color color;
	
	private int x, y;

	public targetBox(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		
		this.color = Color.cyan;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x*width, y*height, width, height);	
	}
}
