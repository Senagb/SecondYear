package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Player.players;
import Shapes.Figures;

public class Orginate implements Serializable {

	private Figures[] obj1;
	private Figures[] obj2;
	private players players1;
	private players players2;
	private ArrayList<Figures> a1;
	private ArrayList<Figures> a2;
	private ArrayList<Boolean> b1;
	private ArrayList<Boolean> b2;

	public Orginate(Figures[] o1, Figures[] o2, players p1, players p2,
			ArrayList<Figures> aa, ArrayList<Figures> ab,
			ArrayList<Boolean> ba, ArrayList<Boolean> bb) {
		obj1 = o1;
		obj2 = o2;
		players1 = p1;
		players2 = p2;
		a1 = aa;
		a2 = ab;
		b1 = ba;
		b2 = bb;

	}

	public ArrayList<Figures> getA1() {
		return a1;
	}

	public void setA1(ArrayList<Figures> a1) {
		this.a1 = a1;
	}

	public ArrayList<Figures> getA2() {
		return a2;
	}

	public void setA2(ArrayList<Figures> a2) {
		this.a2 = a2;
	}

	public ArrayList<Boolean> getB1() {
		return b1;
	}

	public void setB1(ArrayList<Boolean> b1) {
		this.b1 = b1;
	}

	public ArrayList<Boolean> getB2() {
		return b2;
	}

	public void setB2(ArrayList<Boolean> b2) {
		this.b2 = b2;
	}

	public Figures[] getObj1() {
		return obj1;
	}

	public Figures[] getObj2() {
		return obj2;
	}

	public players getPlayers1() {
		return players1;
	}

	public players getPlayers2() {
		return players2;
	}

	public void save() {
		try {
			String name;
			String s = JOptionPane.showInputDialog(null,
					"please enter the name you want to save with :");
			if (s.equals("")) {
				name = "default";
			} else {
				name = s;
			}
			FileOutputStream fileOut = new FileOutputStream(name + ".sr");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (Exception i) {

		}
	}

	public static Orginate load() {
		JFileChooser f = new JFileChooser("pluginfiles");

		f.setVisible(true);

		f.showOpenDialog(null);

		File file = f.getSelectedFile();
		Orginate o;
		try {
			FileInputStream fileIn = new FileInputStream(file.getName());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			o = (Orginate) in.readObject();
			in.close();
			fileIn.close();
			return o;
		} catch (Exception i) {
			JOptionPane.showMessageDialog(null, "error in load file");
		}
		return null;

	}
}
