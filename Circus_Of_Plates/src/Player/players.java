package Player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

import SLinkedList.MyLLIteratorS;
import Shapes.Figures;
import Shapes.FiguresFactory;
import Shapes.shapes;
import Stack.*;

public class players implements Serializable {
	private int xPos, yPos, score;
	public MyStack<Figures> firstStack;
	public MyStack<Figures> secondStack;
	private boolean flag = true;
	private boolean flag2 = true;
	private MyLLIteratorS<Figures> iteratorOne;
	private MyLLIteratorS<Figures> iteratorTwo;
	private int topOfTheStackOne;
	private int topOfTheStackTwo;

	public players() {
		score = 0;
		firstStack = new MyStack<Figures>();
		secondStack = new MyStack<Figures>();

	}

	public int getTopOfStackOne() {
		return topOfTheStackOne;
	}

	public void setTopOfTheStackOne(int x) {
		topOfTheStackOne = x;
	}

	public int getTopOfStackTwo() {
		return topOfTheStackTwo;
	}

	public void setTopOfTheStackTwo(int x) {
		topOfTheStackTwo = x;
	}

	public void setXPos(int x) {
		xPos = x;
	}

	public void setYPos(int y) {
		yPos = y;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public int getScore() {
		return score;
	}

	public void addShape(Figures shape, int x) throws Exception {
		if (x == 1) {
			firstStack.push(shape);
			// setStackBounds(1);
		} else {
			if (x == 2)
				secondStack.push(shape);
			// setStackBounds(2);
		}

	}

	private boolean compareColors(Color firstColor, Color secondColor) {
		return (firstColor.getRGB() == secondColor.getRGB());
	}

	public boolean checkTopOfTheStack(int height) {

		return (topOfTheStackOne <= height || topOfTheStackTwo <= height);
	}

	public void check(int x, FiguresFactory f) throws Exception {
		int counter = 0;
		int position = 0;
		if (x == 1) {
			if (firstStack.size() < 3)
				return;
			iteratorOne = firstStack.getIterator();
			iteratorTwo = firstStack.getIterator();
			iteratorTwo.getNext();
		} else if (x == 2) {
			if (secondStack.size() < 3)
				return;
			iteratorOne = secondStack.getIterator();
			iteratorTwo = secondStack.getIterator();
			iteratorTwo.getNext();

		} else {
			throw new Exception();
		}

		while (iteratorOne.hasNext() && iteratorTwo.hasNext()) {
			position++;
			if (compareColors(iteratorOne.getNext().getColor(), iteratorTwo
					.getNext().getColor())) {
				counter++;
				if (counter == 2) {
					score += 1;
					remove(position, x, f);
				}
			} else
				counter = 0;
		}
	}

	public void remove(int index, int x, FiguresFactory f) throws Exception {
		if (x == 1) {
			f.free(firstStack.get(index - 1));
			f.free(firstStack.get(index - 2));
			f.free(firstStack.get(index));
			firstStack.remove(index);
			firstStack.remove(index - 1);
			firstStack.remove(index - 2);
			f.free(firstStack.get(index));
			setTopOfTheStackOne(firstStack.peek().getHigherYpos());

		} else if (x == 2) {
			f.free(secondStack.get(index - 1));
			f.free(secondStack.get(index - 2));
			f.free(secondStack.get(index));
			secondStack.remove(index);
			secondStack.remove(index - 1);
			secondStack.remove(index - 2);
			setTopOfTheStackOne(secondStack.peek().getHigherYpos());

		}

	}

	public void paint(Graphics2D g2, int x) throws Exception {

		if (x == 1 && (!firstStack.isEmpty())) {
			setTopOfTheStackOne(firstStack.peek().getHigherYpos());
			int ypos = yPos + 12;
			for (int i = firstStack.size() - 1; i >= 0; i--) {
				Figures current = firstStack.get(i);
				current.setXpos(getXPos() - 10);
				Class clObj;
				clObj = current.getClass();
				if (clObj.getName() == "Shapes.TShape")
					current.setYpos(ypos - shapes.getDimension());
				else
					current.setYpos(ypos);
				ypos -= (current.getHeigth());
				current.paint(g2);
			}
		} else if (x == 2 && (!secondStack.isEmpty())) {
			setTopOfTheStackTwo(secondStack.peek().getHigherYpos());
			int ypos2 = yPos + 30;
			for (int i = secondStack.size() - 1; i >= 0; i--) {
				Figures current = secondStack.get(i);
				current.setXpos(getXPos() + 80);

				Class clObj = current.getClass();
				if (clObj.getName() == "Shapes.TShape")
					current.setYpos(ypos2 - shapes.getDimension());
				else
					current.setYpos(ypos2);
				ypos2 -= (current.getHeigth());
				current.paint(g2);
			}

		}
	}

	public boolean rightStack(Figures obj, FiguresFactory f) {
		boolean check = false;
		if (isInFirstStackYRegion(obj.getYPos(), obj)) {
			if (isInFirstStackXRegion(obj.getMidPoint())) {
				try {
					this.addShape(obj, 1);
					obj.setYpos(this.getTopOfStackOne());
					check(1, f);
					check = true;
				} catch (Exception e1) {
					return false;
				}
			}
		}
		return check;
	}

	public boolean leftStack(Figures obj, FiguresFactory f) {
		boolean check = false;
		if (isInSecondStackYRegion(obj.getYPos(), obj)) {
			if (isInSecondStackXRegion(obj.getMidPoint())) {
				try {
					this.addShape(obj, 2);
					obj.setYpos(this.getTopOfStackTwo());
					check(2, f);
					check = true;
				} catch (Exception e1) {
					return false;
				}

			}
		}
		return check;
	}

	public boolean isInFirstStackYRegion(int y, Figures figure) {
		if (flag) {
			this.setTopOfTheStackOne(this.getYPos() + 25);
		}
		flag = false;
		return (y < this.getTopOfStackOne() && y + figure.getSpeed() > this
				.getTopOfStackOne());
	}

	public boolean isInSecondStackYRegion(int y, Figures figure) {
		if (flag2) {
			this.setTopOfTheStackTwo(this.getYPos() + 40);
		}
		flag2 = false;
		return (y < this.getTopOfStackTwo() && y + figure.getSpeed() > this
				.getTopOfStackTwo());
	}

	public boolean isInFirstStackXRegion(int x) {
		int firstStackpos = this.getXPos() + 15;
		return ((x > (firstStackpos) - (2 * Shapes.shapes.getDimension())) && (x < (firstStackpos)
				+ (2 * Shapes.shapes.getDimension())));
	}

	public boolean isInSecondStackXRegion(int x) {
		int secondStackpos = this.getXPos() + 95;
		return ((x > (secondStackpos) - (2 * Shapes.shapes.getDimension())) && (x < (secondStackpos)
				+ (2 * Shapes.shapes.getDimension())));
	}

	public boolean checkExistance(Figures f) throws Exception {
		for (int i = 0; i < firstStack.size(); i++) {
			if (firstStack.get(i) == f)
				return true;
		}
		for (int i = 0; i < secondStack.size(); i++) {
			if (secondStack.get(i) == f) {
				return true;
			}
		}
		return false;
	}
}
