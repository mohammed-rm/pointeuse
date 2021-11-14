package view.mainapp.results;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * 
 * @brief Abstract View used to build some of the Views concerning the results
 *        of a research.
 * @implNote ActionListener.
 *
 */
public abstract class ViewResults extends JFrame implements ActionListener {

	/**
	 * @brief serialVersionUID.
	 */
	private static final long serialVersionUID = -779131960674097386L;

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	protected JPanel panel;
	protected Object[][] dataEntry;
	protected String[] titles;
	protected JTable dataTable;

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/******************************** panel ******************************/

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/***************************** dataEntry *****************************/

	/**
	 * @return the dataEntry
	 */
	public Object[][] getDataEntry() {
		return dataEntry;
	}

	/**
	 * @param dataEntry the dataEntry to set
	 */
	public void setDataEntry(Object[][] dataEntry) {
		this.dataEntry = dataEntry;
	}

	/******************************* titles ******************************/

	/**
	 * @return the titles
	 */
	public String[] getTitles() {
		return titles;
	}

	/**
	 * @param titles the titles to set
	 */
	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	/***************************** dataTable *****************************/

	/**
	 * @return the dataTable
	 */
	public JTable getDataTable() {
		return dataTable;
	}

	/**
	 * @param dataTable the dataTable to set
	 */
	public void setDataTable(JTable dataTable) {
		this.dataTable = dataTable;
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	/**
	 * @brief Method used to build panel.
	 */
	protected void buildContentPanel() {

		panel = new JPanel(new GridBagLayout());

		GridBagConstraints constraintsDataTableHeader = new GridBagConstraints();
		constraintsDataTableHeader.gridx = 0;
		constraintsDataTableHeader.gridy = 0;
		panel.add(dataTable.getTableHeader(), constraintsDataTableHeader);

		GridBagConstraints constraintsDataTable = new GridBagConstraints();
		constraintsDataTable.gridx = 0;
		constraintsDataTable.gridy = 1;
		panel.add(dataTable, constraintsDataTable);

	}

	/*************************** undefined methods *************************/

	/**
	 * @brief Method used to initialize attributes.
	 * 
	 * @param dataEntry the dataEntry to set
	 * @param titles    the titles to set
	 */
	protected abstract void initializeAttributes(Object[][] dataEntry, String[] titles);

	/**
	 * @brief Method which create main frame and panel.
	 */
	protected abstract void build();

	@Override
	public abstract void actionPerformed(ActionEvent event);

}
