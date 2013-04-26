package Shapes;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class LShape extends shapes {
	public LShape(int length, int height, Color color) {
		super(color);
	}

	public LShape() {
		super();
		r1 = (Rectangle2D) new Rectangle(xPos, yPos, DIMENSION, DIMENSION);
		r2 = (Rectangle2D) new Rectangle(xPos, yPos - DIMENSION, DIMENSION,
				DIMENSION);
		r3 = (Rectangle2D) new Rectangle(xPos, yPos - (2 * DIMENSION),
				DIMENSION, DIMENSION);
		r4 = (Rectangle2D) new Rectangle(xPos + DIMENSION, yPos, DIMENSION,
				DIMENSION);
		updateArray();
	}

	@Override
	public int getLength() {
		return DIMENSION * 2;
	}

	@Override
	public int getHeigth() {
		return DIMENSION * 3;
	}

	@Override
	public void updateFigures() {
		{

			r1.setRect(xPos, yPos, DIMENSION, DIMENSION);
			r2.setRect(xPos, yPos - DIMENSION, DIMENSION, DIMENSION);
			r3.setRect(xPos, yPos - (2 * DIMENSION), DIMENSION, DIMENSION);
			r4.setRect(xPos + DIMENSION, yPos, DIMENSION, DIMENSION);
			updateArray();
		}

	}
	@Override
	public int getLowestYpos() {
		return (int)r1.getMinY();
	}
	
	@Override
	public int getHigherYpos() {
		int x =(int) r3.getMaxY();
		return x;
	}


}
