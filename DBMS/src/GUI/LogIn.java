
package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JButton btnNewButton;
	private JLabel label1;
	private JLabel lblPassWord;
	private JPasswordField password;
	private HashMap<String, String> passwords;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// look and feel of windows :D
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					LogIn frame = new LogIn();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		readPasswords();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 450, 205);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		JButton btnExit = new JButton("exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		btnExit.setBounds(334, 129, 89, 23);
		layeredPane.add(btnExit);

		username = new JTextField();
		username.setBounds(92, 32, 235, 20);
		layeredPane.add(username);
		username.setColumns(10);

		btnNewButton = new JButton("log");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				String userName = username.getText();
				String pass = password.getText();
				if (passwords.containsKey(userName)
						&& passwords.get(userName).equals(pass)) {
					try {
						new Frame();
						dispose();
					} catch (Exception e) {
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"incorrect user name or password");
				}
			}
		});
		btnNewButton.setBounds(363, 43, 60, 52);
		layeredPane.add(btnNewButton);

		label1 = new JLabel("user name");
		label1.setForeground(Color.WHITE);
		label1.setBackground(Color.DARK_GRAY);
		label1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		label1.setBounds(10, 29, 89, 25);
		layeredPane.add(label1);

		lblPassWord = new JLabel("password");
		lblPassWord.setForeground(Color.WHITE);
		lblPassWord.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblPassWord.setBackground(Color.DARK_GRAY);
		lblPassWord.setBounds(10, 72, 89, 25);
		layeredPane.add(lblPassWord);

		password = new JPasswordField();
		password.setBounds(92, 75, 235, 20);
		layeredPane.add(password);
	}

	public void readPasswords() {
		passwords = new HashMap<String, String>();
		Scanner in = null;
		try {
			in = new Scanner(new File("Passwords.txt"));
		} catch (FileNotFoundException e) {
		}
		;

		int n = in.nextInt();
		for (int i = 0; i < n; i++)
			passwords.put(in.next(), in.next());

	}
}
