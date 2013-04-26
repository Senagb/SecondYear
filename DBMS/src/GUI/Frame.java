package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLayeredPane;
import javax.swing.JTree;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;

import DBMS.DataBase;
import DBMS.Schema;
import DBMS.tableData;
import Logger.Loggy;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	public static DataBase data;
	private JPanel contentPane;
	private static String[] statment;
	public static Schema schema;
	Logger logger = Loggy.getInstance();

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */

	public Frame() throws Exception {
		this.setResizable(false);
		data = DataBase.getInstance();
		schema = data.getSchema();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 631);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 25, 184, 202);
		layeredPane.add(scrollPane_3);

		tree = new JTree();
		tree.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		setTree();
		scrollPane_3.setViewportView(tree);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 25, 433, 202);
		layeredPane.add(scrollPane);

		final JTextPane console = new JTextPane();
		scrollPane.setViewportView(console);
		console.setFont(new Font("Tahoma", Font.PLAIN, 14));
		console.setForeground(new Color(0, 0, 128));
		console.setBackground(new Color(211, 211, 211));
		console.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));

		JButton btnNewButton = new JButton("execute");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parse(console.getText());
				log.setText("");
				boolean ok = true;
				for (int i = 0; i < statment.length; i++)
					try {
						data.execute(statment[i]);
						setLabel(statment[i] + ";");
						if (statment[i].matches("select.*")) {
							new selectTable(DataBase.getTable().TableName,
									DataBase.getTable().getColumnNames(),
									DataBase.getTable().getValues());
						} else
							setTree();
					} catch (Exception t) {
						String mess;
						if (t.getMessage() == null)
							mess = "ourSQL exception";
						else
							mess = t.getMessage().replaceAll(".*\\(", "\\0(");

						logger.error(mess);

						log.setText(mess + " at statement" + (i + 1));
						ok = false;
						break;

					}
				if (ok)
					console.setText("");
			}
		});
		btnNewButton.setBounds(281, 238, 333, 39);
		layeredPane.add(btnNewButton);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 288, 646, 184);
		layeredPane.add(scrollPane_1);

		executed = new JTextPane();
		executed.setEditable(false);
		scrollPane_1.setViewportView(executed);
		executed.setForeground(Color.YELLOW);
		executed.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		executed.setBackground(Color.DARK_GRAY);
		executed.setText("");

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 483, 646, 79);
		layeredPane.add(scrollPane_2);

		log = new JTextPane();
		log.setEditable(false);
		scrollPane_2.setViewportView(log);
		log.setForeground(Color.RED);
		log.setFont(new Font("Tahoma", Font.BOLD, 16));
		log.setBackground(Color.BLACK);
		JButton btnNewButton_1 = new JButton("view DB");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DrawTable.getInstance().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(42, 230, 89, 54);
		layeredPane.add(btnNewButton_1);
		setVisible(true);
	}

	JTextPane executed;
	JTextPane log;
	static JTree tree;

	private void setLabel(String s) {
		executed.setText(executed.getText() + "\r\n" + s);
	}

	private void setTree() {
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(
				"DataBase") {
			{
				DefaultMutableTreeNode node;
				LinkedList<tableData> t = schema.getTables();
				for (tableData r : t) {
					node = new DefaultMutableTreeNode(r.getTableName());
					String[][] c = schema.getColumns(r.getTableName());
					for (int i = 0; i < c.length; i++) {
						node.add(new DefaultMutableTreeNode(c[i][0] + " ("
								+ c[i][1] + ") "));
					}
					// add(node);
					add(node);
				}
			}
		}));
	}

	private void parse(String text) {
		String tmp = text.replaceAll("\\s+", " ")
				.replaceAll("( ;)|( ; )|(; )", ";").replaceAll("; ", ";");
		if (tmp.length() == 0 || tmp.charAt(tmp.length() - 1) != ';') {
			statment = new String[] { "errorerroeerroeoeroeor" };
		}
		statment = tmp.split(";");
	}
}