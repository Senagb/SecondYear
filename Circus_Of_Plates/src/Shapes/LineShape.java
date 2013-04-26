package Shapes;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class LineShape extends Shapes.shapes {
	public LineShape(int length, int height, Color color) {
		super(color);
	}

	public LineShape() {
	super();
		r1 = (Rectangle2D) new Rectangle(xPos, yPos, DIMENSION, DIMENSION);
		r2 = (Rectangle2D) new Rectangle(xPos + DIMENSION, yPos, DIMENSION,
				DIMENSION);
		r3 = (Rectangle2D) new Rectangle(xPos + (2 * DIMENSION), yPos,
				DIMENSION, DIMENSION);
		r4 = (Rectangle2D) new Rectangle(xPos + (3 * DIMENSION), yPos,
				DIMENSION, DIMENSION);
		updateArray();
	}

	
	@Override
	public int getLength() {
		return DIMENSION * 4;
	}

	@Override
	public int getHeigth() {
		return DIMENSION;
	}

	@Override
	public void updateFigures() {
		{
			r1.setRect(xPos, yPos, DIMENSION, DIMENSION);
			r2.setRect(xPos + DIMENSION, yPos, DIMENSION, DIMENSION);
			r3.setRect(xPos + 2 * DIMENSION, yPos, DIMENSION, DIMENSION);
			r4.setRect(xPos + 3 * DIMENSION, yPos, DIMENSION, DIMENSION);
			updateArray();
		}

	}
	

}
