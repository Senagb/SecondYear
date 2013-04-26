package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import Player.players;
import Shapes.Figures;
import Shapes.FiguresFactory;

public class controller implements ActionListener, Serializable {

	private int height, width, y = 522;
	private Timer timer = new Timer(500, this);
	private boolean gab = true;
	private FiguresFactory instance;
	private Figures[] obj = new Figures[8];
	private players player = new players();

	public controller(int w, int h) {
		timer.start();
		player = new players();
		height = h;
		width = w;
		player.setXPos(width / 2);
		player.setYPos(y);

		for (int i = 0; i < obj.length; i++) {
			obj[i] = null;
		}

	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void start() {
		this.timer.start();
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Figures[] getObj() {
		return obj;
	}

	public Timer getTimer() {
		return timer;
	}

	public int getplayerX(players p) {
		return p.getXPos();
	}

	public int getplayerY(players p) {
		return p.getYPos();
	}

	public players getPlayers() {
		return player;
	}

	public void setObj(Figures[] obj) {
		this.obj = obj;
	}

	public void setPlayers(players players) {
		this.player = players;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (instance == null) {
			instance = FiguresFactory.getInstance(false);

		}
		Random randomGenerator = new Random();
		int x = (randomGenerator.nextInt(width - 50)) % (width - 50);

		for (int i = 0; i < obj.length; i++) {

			if (obj[i] != null) {
				obj[i].setYpos(obj[i].getYPos() + obj[i].getSpeed());
				if (obj[i].getYPos() > height +120) {
					instance.free(obj[i]);
					obj[i] = null;
				} else {

					boolean right = player.rightStack(obj[i], instance);
					boolean left = player.leftStack(obj[i], instance);

					if (right || left) {
						obj[i] = null;
					}

				}
			}
		}
		for (int i = 0; i < obj.length; i++)
			if (obj[i] == null) {
				if (gab) {
					obj[i] = instance.factoryMethod();
					if (obj[i] != null) {
						obj[i].setXpos(x);
						obj[i].setYpos(0);
						gab = false;
						break;
					}
				} else {
					gab = true;
				}
			}

	}

	public int getScore() {
		return player.getScore();
	}
	public ArrayList<Figures> getlist() {
		return instance.getArrayShapes();
	}

	public String check() {
		if (player.checkTopOfTheStack(48)) {
			return "f";
	}else {
		return "";
	} 
}
	public ArrayList<Boolean> getBol() {
		return instance.getArrayShapesValidator();
	}	
	public void run(int code) {
		// notify the painter to repaint
		int playerSpeed = 10;
		if (code == KeyEvent.VK_S) {
			player.setXPos((player.getXPos() - playerSpeed) % width);
			if (player.getXPos() <= 0)
				player.setXPos(0);
		} else if (code == KeyEvent.VK_K) {
			player.setXPos((player.getXPos() - playerSpeed) % width);
			if (player.getXPos() <= 0)
				player.setXPos(0);
		} else if (code == KeyEvent.VK_D) {
			player.setXPos((player.getXPos() + playerSpeed) % width);
			if (player.getXPos() >= width - 100)
				player.setXPos(width - 100);
		} else if (code == KeyEvent.VK_L) {
			player.setXPos((player.getXPos() + playerSpeed) % width);
			if (player.getXPos() >= width - 100)
				player.setXPos(width - 100);
		}
	}

	public void load(ArrayList<Figures> o,ArrayList<Boolean> b) {
		instance = FiguresFactory.getInstance(true);
		instance.load(o,b);
	}
}
