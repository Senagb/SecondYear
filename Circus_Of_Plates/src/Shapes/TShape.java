package Shapes;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class TShape extends shapes {
	public TShape(int length, int height, Color color) {
		super(color);
	}

	public TShape() {
		super();
		r1 = (Rectangle2D) new Rectangle(xPos, yPos, DIMENSION, DIMENSION);
		r2 = (Rectangle2D) new Rectangle(xPos + DIMENSION, yPos+DIMENSION, DIMENSION,DIMENSION);
		r3 = (Rectangle2D) new Rectangle(xPos +DIMENSION, yPos,DIMENSION, DIMENSION);
		r4 = (Rectangle2D) new Rectangle(xPos +2*DIMENSION, yPos,DIMENSION, DIMENSION);
		updateArray();
	}

	
	@Override
	public int getLength() {
		return DIMENSION * 3;
	}

	@Override
	public int getHeigth() {
		return DIMENSION*2;
	}

	@Override
	public void updateFigures() {
		{
			r1.setRect(xPos, yPos, DIMENSION, DIMENSION);
			r2.setRect(xPos + DIMENSION, yPos+DIMENSION, DIMENSION,DIMENSION);
			r3.setRect(xPos +DIMENSION, yPos,DIMENSION, DIMENSION);
			r4.setRect(xPos +2*DIMENSION, yPos,DIMENSION, DIMENSION);	
			updateArray();
		}

	}
	@Override
	public int getNearestXpos() {
		return (int)r2.getMinX();
	}

	@Override
	public int getLowestYpos() {
		return (int)r1.getMinY();
	}

	@Override
	public int getHigherYpos() {
		return (int)r3.getMaxY();
	}

	public int getYPos() {
		return yPos;
	}



}
