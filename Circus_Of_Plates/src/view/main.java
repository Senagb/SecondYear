package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Orginate;

public class main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main mainWindow = new main();
					mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the mainWindow.
	 */
	public main() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Toolkit tool = Toolkit.getDefaultToolkit();
		setUndecorated(true);
		setBounds(0, 0, (int) tool.getScreenSize().getWidth(), (int) tool
				.getScreenSize().getHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel title = new JLabel("(: Circuis Plates :)");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Vivaldi", Font.PLAIN, 99));
		title.setBounds(369, 11, 622, 148);
		contentPane.add(title);

		TransparentButton btnStart = new TransparentButton("Start");
		btnStart.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 100));
		btnStart.setSize(500, 500);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				playingWindow play = new playingWindow(null, false);
				play.show();
				dispose();

			}
		});
		btnStart.setBounds(448, 250, 492, 88);
		contentPane.add(btnStart);

		TransparentButton btnLoad = new TransparentButton("Load");
		btnLoad.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 99));
		btnLoad.setBounds(448, 373, 492, 88);
		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Orginate o = Orginate.load();
				playingWindow play = new playingWindow(o, true);
				play.show();

				dispose();
			}

		});
		contentPane.add(btnLoad);

		TransparentButton btnExit = new TransparentButton("Exit");
		btnExit.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 99));
		btnExit.setBorder(null);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(448, 485, 492, 88);
		contentPane.add(btnExit);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("sky-11.jpg"));
		label.setBounds(0, 0, (int) tool.getScreenSize().getWidth(), (int) tool
				.getScreenSize().getHeight());

		contentPane.add(label);
	}
}

class TransparentButton extends JButton {
	public TransparentButton(String text) {
		super(text);
		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setOpaque(false);
	}

}