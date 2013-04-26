package Shapes;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class SquareShape extends shapes {
	public SquareShape(int length, int height, Color color) {
		super(color);
	}

	public SquareShape() {
		super();
		r1 = (Rectangle2D) new Rectangle(xPos, yPos, DIMENSION, DIMENSION);
		r2 = (Rectangle2D) new Rectangle(xPos + DIMENSION, yPos, DIMENSION,
				DIMENSION);
		r3 = (Rectangle2D) new Rectangle(xPos, yPos - DIMENSION, DIMENSION,
				DIMENSION);
		r4 = (Rectangle2D) new Rectangle(xPos + DIMENSION, yPos - (DIMENSION),
				DIMENSION, DIMENSION);
		updateArray();
	}

	@Override
	public int getLength() {
		return DIMENSION * 2;
	}

	@Override
	public int getHeigth() {
		return DIMENSION * 2;
	}

	@Override
	public void updateFigures() {
		{
			r1.setRect(xPos, yPos, DIMENSION, DIMENSION);
			r2.setRect(xPos + DIMENSION, yPos, DIMENSION, DIMENSION);
			r3.setRect(xPos, yPos - DIMENSION, DIMENSION, DIMENSION);
			r4.setRect(xPos + DIMENSION, yPos - (DIMENSION), DIMENSION,
					DIMENSION);
			updateArray();
		}

	}

}
