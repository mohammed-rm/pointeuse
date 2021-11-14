package view.mainapp.results;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controller.mainapp.BrowserMainapp;

/**
 * 
 * @brief View which represents the results of Employee(s) research.
 *
 */
public class ViewResultsEmployees extends ViewResults {

	/**
	 * @brief serialVersionUID.
	 */
	private static final long serialVersionUID = 5485924175594067340L;

	/*********************************************************************/
	/**************************** INTERN CLASS ***************************/
	/*********************************************************************/

	public class ButtonDetailsRenderer extends JButton implements TableCellRenderer {

		/**
		 * @brief serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		public ButtonDetailsRenderer() {
			super("Details");
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setBackground(Color.lightGray);
			} else {
				setBackground(table.getBackground());
			}
			return this;
		}
	}

	public class ButtonDeleteRenderer extends JButton implements TableCellRenderer {

		/**
		 * @brief serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		public ButtonDeleteRenderer() {
			super("Delete");
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setBackground(Color.lightGray);
			} else {
				setBackground(table.getBackground());
			}
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {

		/**
		 * @brief serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		protected JButton button;

		protected String label;

		protected boolean isPushed;

		protected Object[][][] dataEntry;
		protected String[][] titles;
		protected ViewResultsEmployeeDetails frameEmployeeDetailsResults;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			label = (value == null) ? "" : value.toString();
			isPushed = true;
			return button;
		}
	}

	class ButtonDetailsEditor extends ButtonEditor {

		/**
		 * @brief serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		public ButtonDetailsEditor(JCheckBox checkBox) {
			super(checkBox);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped(); // stops any action from the button

					try {
						BrowserMainapp controller = new BrowserMainapp();
						dataEntry = controller.searchEmployeeDetails(label);
						if (dataEntry != null) {
							titles = new String[][] { { "ID", "Firstname", "Lastname", "Department" },
									{ "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" }, { "Checks (in/out)" } };
							frameEmployeeDetailsResults = new ViewResultsEmployeeDetails(dataEntry, titles);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			});
		}

		public Object getCellEditorValue() {
			return label;
		}
	}

	class ButtonDeleteEditor extends ButtonEditor {

		/**
		 * @brief serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		public ButtonDeleteEditor(JCheckBox checkBox) {
			super(checkBox);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						BrowserMainapp controller = new BrowserMainapp();
						controller.delEmployee(label);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					fireEditingStopped(); // stops any action from the button
				}
			});
		}

		public Object getCellEditorValue() {
			return label;
		}
	}

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private TableColumn buttonsDetails;
	private TableColumn buttonsDelete;

	private JLabel labelResponse;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Constructor.
	 */
	public ViewResultsEmployees(Object[][] dataEntry, String[] titles) {
		initializeAttributes(dataEntry, titles);
		build();
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/*************************** buttonsDetails **************************/

	/**
	 * @return the buttonsDetails
	 */
	public TableColumn getButtonsDetails() {
		return buttonsDetails;
	}

	/**
	 * @param buttonsDetails the buttonsDetails to set
	 */
	public void setButtonsDetails(TableColumn buttonsDetails) {
		this.buttonsDetails = buttonsDetails;
	}

	/**************************** buttonsDelete **************************/

	/**
	 * @return the buttonsDelete
	 */
	public TableColumn getButtonsDelete() {
		return buttonsDelete;
	}

	/**
	 * @param buttonsDelete the buttonsDelete to set
	 */
	public void setButtonsDelete(TableColumn buttonsDelete) {
		this.buttonsDelete = buttonsDelete;
	}

	/*************************** labelresponse ***************************/

	/**
	 * @return the labelResponse
	 */
	public JLabel getLabelResponse() {
		return labelResponse;
	}

	/**
	 * @param labelResponse the labelResponse to set
	 */
	public void setLabelResponse(JLabel labelResponse) {
		this.labelResponse = labelResponse;
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	protected void initializeAttributes(Object[][] dataEntry, String[] titles) {
		setDataEntry(dataEntry);
		sortDataByIDEmployees();
		setTitles(titles);
		setDataTable(new JTable(dataEntry, titles));

		buttonsDetails = new TableColumn();
		buttonsDelete = new TableColumn();
		buttonsDetails.setHeaderValue("");
		buttonsDelete.setHeaderValue("");

		buttonsDetails.setCellRenderer(new ButtonDetailsRenderer());
		buttonsDelete.setCellRenderer(new ButtonDeleteRenderer());

		buttonsDetails.setCellEditor(new ButtonDetailsEditor(new JCheckBox()));
		buttonsDelete.setCellEditor(new ButtonDeleteEditor(new JCheckBox()));

		dataTable.addColumn(buttonsDetails);
		dataTable.addColumn(buttonsDelete);
	}

	protected void build() {

		// create frame
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(300, 300, 600, 400);
		setTitle("Results of the employee research");

		// create panel
		buildContentPanel();

		add(panel);
		setVisible(true);

	}

	private void sortDataByIDEmployees() {
		for (int i = 0; i < dataEntry.length; i++) {
			for (int j = 0; j < dataEntry.length; j++) {
				if (Integer.parseInt((String) dataEntry[i][0]) < Integer.parseInt((String) dataEntry[j][0])) {
					Object[] temp = dataEntry[i];
					dataEntry[i] = dataEntry[j];
					dataEntry[j] = temp;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		event.getSource();
	}

}
