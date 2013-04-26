package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import Player.players;
import Shapes.Figures;
import controller.controller;

public class painter extends JPanel implements ActionListener {
	private controller controller;
	private Timer timer;
	private Figures[] obj;
	private controller myController;
	private players players, other;
	private Toolkit tool = Toolkit.getDefaultToolkit();
	private Image img = tool.getImage("images.gif");

	public painter(int h, int w, Figures[] a, players p, ArrayList<Figures> o,
			ArrayList<Boolean> b, players y) {
		myController = new controller(h, w);
		myController.setHeight(h);
		myController.setWidth(w);
		if (a != null && p != null) {
			load(a, p);
			myController.load(o, b);
		}
		timer = new Timer(5, this);
		timer.start();
		other = y;
	}

	public int getScore() {
		return myController.getScore();
	}

	public Figures[] getObj() {
		return obj;
	}

	public players getPlayers() {
		return players;
	}

	public void load(Figures[] a, players p) {
		myController.setObj(a);
		obj = a;
		myController.setPlayers(p);
		players = p;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);

		obj = myController.getObj();
		players = myController.getPlayers();
		try {
			for (Figures r : obj) {
				if (other != null) {
					if (r != null && !other.checkExistance(r))
						r.paint(g2);

				} else {
					if (r != null)
						r.paint(g2);
				}
			}
		} catch (Exception e) {

		}

		g2.drawImage(img, myController.getplayerX(players),
				myController.getplayerY(players), this);
		try {
			players.paint(g2, 1);
			players.paint(g2, 2);
		} catch (Exception e) {

		}
	}

	public controller getController() {
		return myController;
	}

	public ArrayList<Figures> getlist() {
		return myController.getlist();
	}

	public ArrayList<Boolean> getBol() {
		return myController.getBol();
	}
 public void stop (){
	 timer.stop();
 }
	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
	}
}
