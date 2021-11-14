package view.mainapp.results;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTable;

public class ViewResultsCheckInOuts extends ViewResults {

	private static final long serialVersionUID = 1L;
	
	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private JLabel labelResponse;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Constructor.
	 */
	public ViewResultsCheckInOuts(Object[][] dataEntry, String[] titles) {
		initializeAttributes(dataEntry, titles);
		build();
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

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

	@Override
	protected void initializeAttributes(Object[][] dataEntry, String[] titles) {
		setDataEntry(dataEntry);
		setTitles(titles);
		setDataTable(new JTable(dataEntry, titles));
	}

	protected void build() {

		// create frame
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(300, 300, 600, 400);
		setTitle("Results of the check research");

		// create panel
		buildContentPanel();

		add(panel);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		event.getSource();
	}
}
