package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class selectTable extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public selectTable(String tbaleName, String[] columns, String[][] values) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblN = new JLabel(tbaleName);
		lblN.setBounds(22, 11, 256, 32);
		contentPane.add(lblN);

		table = new JTable(values.length, columns.length);
		table.setModel(new DefaultTableModel(values, columns));

		table.setBounds(22, 54, 388, 239);
		contentPane.add(table);
		setVisible(true);
	}
}
