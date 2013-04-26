package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import DBMS.Load;
import DBMS.Schema;
import DBMS.Table;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class DrawTable extends JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane_1;
	private Schema schema = Frame.schema;
	public JTable table;
	private String[] tableNames = schema.getTableNames();
	private JComboBox comboBox;
	private static DrawTable draw = null;

	public static DrawTable getInstance() {
		if (draw == null)
			draw = new DrawTable();
		return draw;
	}

	/**
	 * Create the frame.
	 */
	private DrawTable() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				draw = null;
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JLayeredPane layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		layeredPane_1 = new JLayeredPane();
		contentPane.add(layeredPane_1, BorderLayout.CENTER);

		comboBox = new JComboBox(tableNames);

		comboBox.setBounds(115, 11, 217, 20);
		layeredPane_1.add(comboBox);

		JButton btnDraw = new JButton("draw");
		btnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					layeredPane_1.add(add());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDraw.setBounds(351, 10, 89, 23);
		layeredPane_1.add(btnDraw);
		setVisible(true);
	}

	private JScrollPane add() throws Exception {
		JScrollPane scroll = getTable((String) comboBox.getSelectedItem());
		scroll.setBounds(100, 100, 500, 400);
		scroll.setVisible(true);
		return scroll;
	}

	public JScrollPane getTable(String Name) throws Exception {
		Load load = new Load();
		load.execute(Name, schema);
		Table t = load.getTable();

		table = new JTable(t.getLength(), t.getColumnLength());

		String[] ColumnNames = schema.getColNames(Name);
		table.setModel(new DefaultTableModel(new String[t.getLength() - 1][t
				.getColumnLength() + 1], ColumnNames));

		for (int i = 1; i < t.getLength(); i++)
			for (int j = 0; j < t.getColumnLength(); j++) {
				table.setValueAt(t.getValue(i, j).toString(), i - 1, j);
			}

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		return new JScrollPane(table);
	}
}
