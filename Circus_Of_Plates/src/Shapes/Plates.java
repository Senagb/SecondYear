package Shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Plates extends Figures {
	private static int LENGTH = 40;
	private static int HEIGHT = 20;

	public Plates() {
		this.randomizeColor();
		
	}

	@Override
	public int getLength() {
		return LENGTH;
	}

	@Override
	public int getHeigth() {
		return HEIGHT;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void setXpos(int x) {
		this.xPos = x;

	}

	@Override
	public void setYpos(int y) {
		this.yPos = y;
	}

	@Override
	public int getXPos() {
		return xPos;
	}

	@Override
	public int getYPos() {
		return yPos;
	}

	@Override
	public void updateFigures() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(color);
		g2.fillOval(xPos, yPos, LENGTH, HEIGHT);
	}

	@Override
	public int getNearestXpos() {
		return xPos;
	}

	@Override
	public int getFurthestXpos() {
		return xPos+LENGTH;
	}

	@Override
	public int getLowestYpos() {
		return yPos-HEIGHT/2;
	}

	@Override
	public int getHigherYpos() {
		return yPos+HEIGHT/2;
	}

}
