package view.mainapp;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.mainapp.BrowserMainapp;
import view.mainapp.results.ViewResultsCheckInOuts;

/**
 * 
 * @brief View build with the results of a checkInOut research.
 * @implNote ViewModel.
 *
 */
public class ViewCheckInOutsSearch extends ViewModel {

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private JTextField employeeFirstName;
	private JTextField employeeLastName;
	private JTextField employeeID;
	private JTextField departmentName;
	private JTextField startDate;
	private JTextField endDate;

	private JLabel labelEmployeeFirstName;
	private JLabel labelEmployeeLastName;
	private JLabel labelEmployeeID;
	private JLabel labelDepartmentName;
	private JLabel labelStartDate;
	private JLabel labelEndDate;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 */
	public ViewCheckInOutsSearch() {
		setArraySize(6);
		initializeAttributes();
		buildContentPanel();
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/************************* employeeFirstName *************************/

	/**
	 * @return the employeeFirstName
	 */
	public JTextField getEmployeeFirstName() {
		return employeeFirstName;
	}

	/**
	 * @param employeeFirstName the employeeFirstName to set
	 */
	public void setEmployeeFirstName(JTextField employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	/************************* employeeLastName **************************/

	/**
	 * @return the employeeLastName
	 */
	public JTextField getEmployeeLastName() {
		return employeeLastName;
	}

	/**
	 * @param employeeLastName the employeeLastName to set
	 */
	public void setEmployeeLastName(JTextField employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	/**************************** employeeID *****************************/

	/**
	 * @return the employeeID
	 */
	public JTextField getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(JTextField employeeID) {
		this.employeeID = employeeID;
	}

	/************************** departmentName ***************************/

	/**
	 * @return the departmentName
	 */
	public JTextField getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(JTextField departmentName) {
		this.departmentName = departmentName;
	}

	/***************************** startDate *****************************/

	/**
	 * @return the startDate
	 */
	public JTextField getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(JTextField startDate) {
		this.startDate = startDate;
	}

	/****************************** endDate ******************************/

	/**
	 * @return the endDate
	 */
	public JTextField getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(JTextField endDate) {
		this.endDate = endDate;
	}

	/*********************** labelEmployeeFirstName **********************/

	/**
	 * @return the labelEmployeeFirstName
	 */
	public JLabel getLabelEmployeeFirstName() {
		return labelEmployeeFirstName;
	}

	/**
	 * @param labelEmployeeFirstName the labelEmployeeFirstName to set
	 */
	public void setLabelEmployeeFirstName(JLabel labelEmployeeFirstName) {
		this.labelEmployeeFirstName = labelEmployeeFirstName;
	}

	/********************** labelEmployeeLastName ************************/

	/**
	 * @return the labelEmployeeLastName
	 */
	public JLabel getLabelEmployeeLastName() {
		return labelEmployeeLastName;
	}

	/**
	 * @param labelEmployeeLastName the labelEmployeeLastName to set
	 */
	public void setLabelEmployeeLastName(JLabel labelEmployeeLastName) {
		this.labelEmployeeLastName = labelEmployeeLastName;
	}

	/************************* labelEmployeeID ***************************/

	/**
	 * @return the labelEmployeeID
	 */
	public JLabel getLabelEmployeeID() {
		return labelEmployeeID;
	}

	/**
	 * @param labelEmployeeID the labelEmployeeID to set
	 */
	public void setLabelEmployeeID(JLabel labelEmployeeID) {
		this.labelEmployeeID = labelEmployeeID;
	}

	/************************ labelDepartmentName ************************/

	/**
	 * @return the labelDepartmentName
	 */
	public JLabel getLabelDepartmentName() {
		return labelDepartmentName;
	}

	/**
	 * @param labelDepartmentName the labelDepartmentName to set
	 */
	public void setLabelDepartmentName(JLabel labelDepartmentName) {
		this.labelDepartmentName = labelDepartmentName;
	}

	/************************** labelStartDate ***************************/

	/**
	 * @return the labelStartDate
	 */
	public JLabel getLabelStartDate() {
		return labelStartDate;
	}

	/**
	 * @param labelStartDate the labelStartDate to set
	 */
	public void setLabelStartDate(JLabel labelStartDate) {
		this.labelStartDate = labelStartDate;
	}

	/*************************** labelEndDate ****************************/

	/**
	 * @return the labelEndDate
	 */
	public JLabel getLabelEndDate() {
		return labelEndDate;
	}

	/**
	 * @param labelEndDate the labelEndDate to set
	 */
	public void setLabelEndDate(JLabel labelEndDate) {
		this.labelEndDate = labelEndDate;
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	protected void initializeAttributes() {

		labelArray = new ArrayList<JLabel>();
		labelEmployeeFirstName = new JLabel("By Employee First Name ");
		labelEmployeeLastName = new JLabel("By Employee Last Name ");
		labelEmployeeID = new JLabel("By Employee ID ");
		labelDepartmentName = new JLabel("By Department Name ");
		labelStartDate = new JLabel("Before DateTime (MM-dd-yyyy HH:mm) ");
		labelEndDate = new JLabel("After DateTime (MM-dd-yyyy HH:mm) ");
		labelArray.add(labelEmployeeFirstName);
		labelArray.add(labelEmployeeLastName);
		labelArray.add(labelEmployeeID);
		labelArray.add(labelDepartmentName);
		labelArray.add(labelStartDate);
		labelArray.add(labelEndDate);

		textFieldArray = new ArrayList<JTextField>();
		employeeFirstName = new JTextField();
		employeeLastName = new JTextField();
		employeeID = new JTextField();
		departmentName = new JTextField();
		startDate = new JTextField();
		endDate = new JTextField();
		textFieldArray.add(employeeFirstName);
		textFieldArray.add(employeeLastName);
		textFieldArray.add(employeeID);
		textFieldArray.add(departmentName);
		textFieldArray.add(startDate);
		textFieldArray.add(endDate);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();

		if (source == submitButton) {

			submitMap = new HashMap<String, JTextField>();
			submitMap.put("firstname", textFieldArray.get(0));
			submitMap.put("lastname", textFieldArray.get(1));
			submitMap.put("id", textFieldArray.get(2));
			submitMap.put("department_name", textFieldArray.get(3));
			submitMap.put("before_date", textFieldArray.get(4));
			submitMap.put("after_date", textFieldArray.get(5));

			try {

				BrowserMainapp controller = new BrowserMainapp();
				Object[][] dataEntry = controller.searchCheckInOut(getSubmitMap());
				String[] titles = {"ID", "Firstname", "Lastname", "Date", "Status"};
				new ViewResultsCheckInOuts(dataEntry, titles);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
