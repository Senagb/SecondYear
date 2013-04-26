package view;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;

import javax.swing.border.EmptyBorder;

import Player.players;

import controller.Orginate;
import controller.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class playingWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	final painter paint;
	final painter paint1;
	private Timer timer = new Timer(3000, this);
	private int score1 = 0, score2 = 0;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public playingWindow(Orginate o, boolean load) {
		timer.start();
		if (!load) {
			paint = new painter(546, 550, null, null, null, null, null);
			paint1 = new painter(546, 550, null, null, null, null,
					paint.getPlayers());
		} else {
			paint = new painter(546, 622, o.getObj1(), o.getPlayers1(),
					o.getA1(), o.getB1(), null);
			paint1 = new painter(546, 622, o.getObj2(), o.getPlayers2(),
					o.getA2(), o.getB2(), paint.getPlayers());
		}

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Toolkit tool = Toolkit.getDefaultToolkit();
		setUndecorated(true);
		setBounds(0, 0, (int) tool.getScreenSize().getWidth(), (int) tool
				.getScreenSize().getHeight());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel back = new JLabel();
		back.setBounds(0, 0, (int) tool.getScreenSize().getWidth(), 740);
		back.setIcon(new ImageIcon("back.jpg"));
		contentPane.add(back);

		JPanel panel;
		panel = paint;
		panel.setBackground(Color.white);
		panel.setBounds(34, 48, 546, 670);
		contentPane.add(panel);

		JPanel panel_1;
		panel_1 = paint1;
		panel_1.setBackground(Color.white);
		panel_1.setBounds(722, 48, 546, 670);
		contentPane.add(panel_1);

		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.GREEN);
		toolBar.setBounds(0, 740, 685, 28);
		contentPane.add(toolBar);

		JLabel lblScorePlayer = new JLabel("score player 1=");
		toolBar.add(lblScorePlayer);

		lblNewLabel = new JLabel();
		toolBar.add(lblNewLabel);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBackground(Color.GREEN);
		toolBar_1.setBounds(686, 740, 680, 28);
		contentPane.add(toolBar_1);

		JLabel lblScorePlayer_1 = new JLabel("score player 2=");
		toolBar_1.add(lblScorePlayer_1);

		lblNewLabel_1 = new JLabel();

		toolBar_1.add(lblNewLabel_1);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_S || code == KeyEvent.VK_D)
					paint.getController().run(code);
				else if (code == KeyEvent.VK_K || code == KeyEvent.VK_L)
					paint1.getController().run(code);
				if (code == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
				if (code == KeyEvent.VK_U) {
					paint.stop();
					paint1.stop();
					Orginate y = new Orginate(paint.getObj(), paint1.getObj(),
							paint.getPlayers(), paint1.getPlayers(),
							paint.getlist(), paint1.getlist(), paint.getBol(),
							paint1.getBol());
					y.save();
					
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		score1 = paint.getScore();
		score2 = paint1.getScore();

		if (score1 >= 5) {
			JOptionPane.showMessageDialog(null, "player 1 wins :) " + score1
					+ ":" + score2);
			System.exit(0);

		} else if (score2 >= 5) {
			JOptionPane.showMessageDialog(null, "player 2 wins :)" + score2
					+ ":" + score1);
			System.exit(0);
		} else if (paint.getController().check().equals("f")) {
			JOptionPane.showMessageDialog(null,
					"player 2 wins :) player 1 crashed");
			System.exit(0);
		} else if (paint1.getController().check().equals("f")) {
			JOptionPane.showMessageDialog(null,
					"player 1 wins :) player 2 crashed");
			System.exit(0);
		}
		lblNewLabel_1.setText("" + score2);
		lblNewLabel.setText("" + score1);

	}
}
