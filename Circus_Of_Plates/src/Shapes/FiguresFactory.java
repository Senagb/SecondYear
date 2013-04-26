package Shapes;

import java.io.Serializable;
import java.util.ArrayList;


public class FiguresFactory implements Serializable {
	private static FiguresFactory myFactory;
	private static ArrayList<Figures> arrayShapes;
	public static ArrayList<Boolean> arrayShapesValidator;
	private static int maxnumber;

	private FiguresFactory(boolean load) {
		try {
			if (!load) {
				arrayShapes = MyClassLoader.excute();
				arrayShapesValidator = new ArrayList<Boolean>();
				for (int i = 0; i < arrayShapes.size(); i++) {
					arrayShapesValidator.add(i, true);
				}
				maxnumber = arrayShapes.size();
			} else {
			}
		} catch (Exception e) {
			return;
		}
	}

	public static int getMaxnumber() {
		return maxnumber;
	}

	public static FiguresFactory getInstance(boolean load) {
		if (myFactory == null) {
			myFactory = new FiguresFactory(load);
		}
		return myFactory;
	}

	public static ArrayList<Figures> getArray() {
		return arrayShapes;
	}

	public void removeShape(Figures shape) {
		int x = arrayShapes.indexOf(shape);
		arrayShapesValidator.remove(x);
		arrayShapes.remove(x);
		replace(x);
	}

	public Figures factoryMethod() {
		int index = -1;
		for (int i = 0; i < arrayShapesValidator.size(); i++)
			if (arrayShapesValidator.get(i)) {
				index = i;
			}
		if (index != -1 && arrayShapes.get(index) != null) {
			arrayShapesValidator.set(index, false);
			arrayShapes.get(index).randomizeColor();

			return arrayShapes.get(index);

		} else {
			return null;
		}

	}

	public void free(Figures r) {

		int x = arrayShapes.indexOf(r);
		if (x != -1) {
			arrayShapesValidator.set(x, true);
			arrayShapes.get(x).setXpos(0);
			arrayShapes.get(x).setYpos(0);

		}
	}

	public static ArrayList<Figures> getArrayShapes() {
		return arrayShapes;
	}

	public static void setArrayShapes(ArrayList<Figures> arrayShapes) {
		FiguresFactory.arrayShapes = arrayShapes;
	}

	public static ArrayList<Boolean> getArrayShapesValidator() {
		return arrayShapesValidator;
	}

	public static void setArrayShapesValidator(
			ArrayList<Boolean> arrayShapesValidator) {
		FiguresFactory.arrayShapesValidator = arrayShapesValidator;
	}

	public void replace(int x) {
		arrayShapes.add(x, MyClassLoader.replace());
		arrayShapesValidator.add(x, true);
	}

	public void load(ArrayList<Figures> o, ArrayList<Boolean> b) {
		arrayShapes = o;
		arrayShapesValidator = b;
		maxnumber = arrayShapes.size();
	}
}
