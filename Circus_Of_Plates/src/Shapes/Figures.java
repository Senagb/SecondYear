package Shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Random;

public abstract class Figures implements Serializable{

	protected Color color;
	protected int xPos, yPos, speed;

	public Figures() {			
	}

	public void setSpeed(int x) {
		speed = x;
	}

	public int getSpeed() {
		return speed;
	}

	public abstract int getLength();

	public abstract int getHeigth();

	public abstract Color getColor();

	public abstract void setColor(Color color);

	public abstract void setXpos(int x);

	public abstract void setYpos(int y);

	public abstract int getXPos();

	public abstract int getYPos();

	public abstract void paint(Graphics2D g2);

	public abstract void updateFigures();

	public abstract int getNearestXpos();

	public abstract int getFurthestXpos();

	public abstract int getLowestYpos();

	public abstract int getHigherYpos();

	public int getMidPoint() {
		return ((getNearestXpos() + getFurthestXpos()) / 2);
	}

	public void randomizeColor() {
		Random randomGenerator = new Random();
		int random = randomGenerator.nextInt(5);
		Random rg = new Random();
		setSpeed(rg.nextInt(5)+ 25);
		switch (random) {
		case 0:
			this.setColor(Color.cyan);
			break;
		case 1:
			this.setColor(Color.BLUE);
			break;
		case 2:
			this.setColor(Color.GREEN);
			break;
		case 3:
			this.setColor(Color.RED);
			break;
		case 4:
			this.setColor(Color.YELLOW);
			break;
		case 5:
			this.setColor(Color.ORANGE);
			break;

		}
	}

}
