package clueControlGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import clueGame.Board;

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

	public boolean containsClick(int mouseX,int mouseY) {
		Rectangle rect = new Rectangle(x, y, 20, 20);
		
		if (rect.contains(new Point(mouseX, mouseY))) {
			return true;
		}
		
		return false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
