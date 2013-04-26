import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class entery extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private final JSeparator separator_1 = new JSeparator();
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					entery frame = new entery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public entery() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(58, 147, 621, 14);
		contentPane.add(separator);

		JLabel lblCalculatingThePower = new JLabel("calculating the power:");
		lblCalculatingThePower
				.setFont(new Font("Tekton Pro Ext", Font.BOLD, 16));
		lblCalculatingThePower.setBounds(10, 11, 213, 24);
		contentPane.add(lblCalculatingThePower);

		textField = new JTextField();
		textField.setBounds(69, 46, 86, 38);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(211, 46, 86, 38);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(371, 46, 86, 38);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JButton btnDone = new JButton("(A^B)mod N");
		btnDone.setBounds(26, 95, 238, 41);
		contentPane.add(btnDone);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(326, 96, 178, 40);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblExtendsEcluidsThereom = new JLabel(
				"extended Euclid\u2019s algorithm:");
		lblExtendsEcluidsThereom.setFont(new Font("Tekton Pro Ext", Font.BOLD,
				16));
		lblExtendsEcluidsThereom.setBounds(10, 161, 254, 24);
		contentPane.add(lblExtendsEcluidsThereom);

		textField_4 = new JTextField();
		textField_4.setBounds(69, 195, 86, 34);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(237, 195, 86, 34);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(326, 241, 178, 38);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		JButton btnCalculateMultInverse = new JButton("calculate mult. inverse");
		btnCalculateMultInverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int b = 0, m = 0;
				try {
					b = Integer.parseInt(textField_4.getText());
					m = Integer.parseInt(textField_5.getText());
					int re = extendEculidsTheorem.calculate(m, b);
					if (!extendEculidsTheorem.isNo()) {
						textField_6.setText("" + re);
					} else {
						textField_6
								.setText("there isn't multiplicative inverse");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "error check input");
				}

			}
		});
		btnCalculateMultInverse.setBounds(26, 240, 238, 39);
		contentPane.add(btnCalculateMultInverse);

		JLabel lblA = new JLabel("A =");
		lblA.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setBounds(10, 196, 46, 33);
		contentPane.add(lblA);

		JLabel lblNewLabel = new JLabel("N =");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setEnabled(true);
		lblNewLabel.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
		lblNewLabel.setBounds(181, 196, 46, 33);
		contentPane.add(lblNewLabel);
		separator_1.setBounds(58, 290, 621, 31);
		contentPane.add(separator_1);

		JLabel lblChineseRemainderTheorem = new JLabel(
				"Chinese Remainder Theorem:");
		lblChineseRemainderTheorem.setFont(new Font("Tekton Pro Ext",
				Font.PLAIN, 16));
		lblChineseRemainderTheorem.setBounds(10, 303, 254, 24);
		contentPane.add(lblChineseRemainderTheorem);

		textField_7 = new JTextField();
		textField_7.setBounds(69, 338, 86, 34);
		contentPane.add(textField_7);
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setBounds(307, 338, 110, 34);
		contentPane.add(textField_8);
		textField_8.setColumns(10);

		JButton btnNewButton = new JButton("C.R.T");
		btnNewButton.setBounds(14, 423, 238, 39);
		contentPane.add(btnNewButton);

		JLabel lblA_1 = new JLabel("A=");
		lblA_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblA_1.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
		lblA_1.setBounds(10, 46, 46, 36);
		contentPane.add(lblA_1);

		JLabel lblB = new JLabel("B=");
		lblB.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setBounds(165, 46, 46, 38);
		contentPane.add(lblB);

		JLabel lblN = new JLabel("N=");
		lblN.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
		lblN.setHorizontalAlignment(SwingConstants.CENTER);
		lblN.setBounds(315, 46, 46, 38);
		contentPane.add(lblN);

		JLabel lblM = new JLabel("M=");
		lblM.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setBounds(10, 338, 46, 34);
		contentPane.add(lblM);

		JLabel lblArrayOfM = new JLabel("Array of m=");
		lblArrayOfM.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
		lblArrayOfM.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrayOfM.setBounds(181, 332, 116, 40);
		contentPane.add(lblArrayOfM);

		JLabel lblIe = new JLabel("i.e. {2,3,4,5}");
		lblIe.setBounds(211, 371, 116, 14);
		contentPane.add(lblIe);

		JLabel lblA_2 = new JLabel("A=");
		lblA_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblA_2.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
		lblA_2.setBounds(427, 338, 46, 34);
		contentPane.add(lblA_2);

		textField_9 = new JTextField();
		textField_9.setBounds(465, 338, 66, 34);
		contentPane.add(textField_9);
		textField_9.setColumns(10);

		JLabel lblB_1 = new JLabel("B=");
		lblB_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblB_1.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
		lblB_1.setBounds(541, 338, 46, 34);
		contentPane.add(lblB_1);

		textField_10 = new JTextField();
		textField_10.setBounds(597, 338, 66, 34);
		contentPane.add(textField_10);
		textField_10.setColumns(10);

		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setBounds(279, 398, 178, 39);
		contentPane.add(textField_11);
		textField_11.setColumns(10);

		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setBounds(549, 398, 166, 39);
		contentPane.add(textField_12);
		textField_12.setColumns(10);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
		btnExit.setBounds(604, 12, 89, 23);
		contentPane.add(btnExit);

		JLabel lblMadeBy = new JLabel(
				"Made by : Ahmad magdy & Mostafa el Zonkoly");
		lblMadeBy.setBounds(427, 448, 266, 14);
		contentPane.add(lblMadeBy);

		JLabel lblSum = new JLabel("Sum =");
		lblSum.setBounds(237, 410, 46, 14);
		contentPane.add(lblSum);

		JLabel lblMultiplication = new JLabel("multiplication= ");
		lblMultiplication.setBounds(465, 410, 86, 14);
		contentPane.add(lblMultiplication);
	}

}


/*textfield mn 3'er 7aga --> kymt A
 * textField1 --> B 
 * textfield2--> n
 * output--> textfield 3
 * button --> btndone
 *
 * 
 * for CRT 
 * textfield7 --> M
 * textfield8-->array
 * textfield9-->A
 * textfield10-->B
 * textfield11_sum
 * textfield12-->multplicatiom
 * btnnewButton--> CRT 
 * */
 