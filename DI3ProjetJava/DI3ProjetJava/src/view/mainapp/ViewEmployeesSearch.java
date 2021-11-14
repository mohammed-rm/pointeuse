package view.mainapp;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.mainapp.BrowserMainapp;
import view.mainapp.results.ViewResultsEmployees;

/**
 * 
 * @brief View which allow to search an Employee or a group of Employee in the
 *        Company.
 * @implNote ViewModel.
 *
 */
public class ViewEmployeesSearch extends ViewModel {

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private JTextField firstName;
	private JTextField lastName;
	private JTextField ID;
	private JTextField department;

	private JLabel labelFirstName;
	private JLabel labelLastName;
	private JLabel labelID;
	private JLabel labelDepartment;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 */
	public ViewEmployeesSearch() {
		setArraySize(4);
		initializeAttributes();
		buildContentPanel();
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/***************************** firstName *****************************/

	/**
	 * @return the firstName
	 */
	public JTextField getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(JTextField firstName) {
		this.firstName = firstName;
	}

	/****************************** lastName *****************************/

	/**
	 * @return the lastName
	 */
	public JTextField getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(JTextField lastName) {
		this.lastName = lastName;
	}

	/******************************** ID *********************************/

	/**
	 * @return the iD
	 */
	public JTextField getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(JTextField iD) {
		ID = iD;
	}

	/***************************** department ****************************/

	/**
	 * @return the department
	 */
	public JTextField getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(JTextField department) {
		this.department = department;
	}

	/*************************** labelFirstName **************************/

	/**
	 * @return the labelFirstName
	 */
	public JLabel getLabelFirstName() {
		return labelFirstName;
	}

	/**
	 * @param labelFirstName the labelFirstName to set
	 */
	public void setLabelFirstName(JLabel labelFirstName) {
		this.labelFirstName = labelFirstName;
	}

	/**************************** labelLastName **************************/

	/**
	 * @return the labelLastName
	 */
	public JLabel getLabelLastName() {
		return labelLastName;
	}

	/**
	 * @param labelLastName the labelLastName to set
	 */
	public void setLabelLastName(JLabel labelLastName) {
		this.labelLastName = labelLastName;
	}

	/****************************** labelID ******************************/

	/**
	 * @return the labelID
	 */
	public JLabel getLabelID() {
		return labelID;
	}

	/**
	 * @param labelID the labelID to set
	 */
	public void setLabelID(JLabel labelID) {
		this.labelID = labelID;
	}

	/************************** labelDepartment **************************/

	/**
	 * @return the labelDepartment
	 */
	public JLabel getLabelDepartment() {
		return labelDepartment;
	}

	/**
	 * @param labelDepartment the labelDepartment to set
	 */
	public void setLabelDepartment(JLabel labelDepartment) {
		this.labelDepartment = labelDepartment;
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	protected void initializeAttributes() {

		labelArray = new ArrayList<JLabel>();
		labelFirstName = new JLabel("By First Name");
		labelLastName = new JLabel("By Last Name");
		labelID = new JLabel("By ID");
		labelDepartment = new JLabel("By Department Name ");
		labelArray.add(labelFirstName);
		labelArray.add(labelLastName);
		labelArray.add(labelID);
		labelArray.add(labelDepartment);

		textFieldArray = new ArrayList<JTextField>();
		firstName = new JTextField();
		lastName = new JTextField();
		ID = new JTextField();
		department = new JTextField();
		textFieldArray.add(firstName);
		textFieldArray.add(lastName);
		textFieldArray.add(ID);
		textFieldArray.add(department);

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

			try {

				BrowserMainapp controller = new BrowserMainapp();
				Object[][] dataEntry = controller.searchEmployee(getSubmitMap());
				String[] titles = { "ID", "Firstname", "Lastname", "Department", "Date of last check" };
				new ViewResultsEmployees(dataEntry, titles);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
