package Shapes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public abstract class shapes extends Figures {
	protected static final int DIMENSION = 15;
	protected Color color;
	protected int xPos, yPos;
	protected Rectangle2D r1;
	protected Rectangle2D r2;
	protected Rectangle2D r3;
	protected Rectangle2D r4;
	protected Rectangle2D[] rec = new Rectangle2D[4];
	
	public shapes() {
		super();
		this.randomizeColor();
	}
	public shapes(Color color) {
		this.color = color;
	}

	

	public abstract int getLength();

	public abstract int getHeigth();

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setXpos(int x) {
		xPos = x;
		updateFigures();
		}

	public void setYpos(int y) {
		yPos = y;
		updateFigures();
		}

	public int getXPos() {
		return xPos;
	}

	public static  int getDimension()
	{
		return DIMENSION;
	}
	public int getYPos() {
		return yPos;
	}

	public Rectangle2D getRec1() {
		return r1;
	}

	public Rectangle2D getRec2() {
		return r2;
	}

	public Rectangle2D getRec3() {
		return r3;
	}

	public Rectangle2D getRec4() {
		return r4;
	}

	public abstract void updateFigures();

	public void updateArray() {
		rec[0] = r1;
		rec[1] = r2;
		rec[2] = r3;
		rec[3] = r4;
	}

	public void paint(Graphics2D g2, Rectangle2D[] r) {
		for (int i = 0; i < r.length; i++) {
			Rectangle2D myRec = r[i];
			int x = (int) myRec.getX();
			int y = (int) myRec.getY();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(color);
			g2.fillRect(x + 1, y + 1, DIMENSION - 2, DIMENSION - 2);
			g2.setColor(color.brighter());
			g2.drawLine(x, y + DIMENSION - 1, x, y);
			g2.drawLine(x, y, x + DIMENSION - 1, y);
			g2.setColor(color.darker());
			g2.drawLine(x + 1, y + DIMENSION - 1, x + DIMENSION - 1, y
					+ DIMENSION - 1);
			g2.drawLine(x + DIMENSION - 1, y + DIMENSION - 1,
					x + DIMENSION - 1, y + 1);
		}
	}

	public void paint(Graphics2D g2) {
		paint(g2, this.rec);
	}

	@Override
	public int getNearestXpos() {
		return (int)r1.getMinX();
	}

	@Override
	public int getFurthestXpos() {
		
		return (int)r4.getMaxX();
	}

	@Override
	public int getLowestYpos() {
		return (int)r2.getMinY();
	}

	@Override
	public int getHigherYpos() {
		return (int)r3.getMaxY();
	}


}
