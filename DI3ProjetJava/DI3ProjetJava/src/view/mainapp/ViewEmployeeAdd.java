package view.mainapp;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.mainapp.BrowserMainapp;

/**
 * 
 * @brief View that allow to add an Employee in the company.
 * @implNote ViewModel.
 *
 */
public class ViewEmployeeAdd extends ViewModel {

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private JTextField firstName;
	private JTextField lastName;
	private JTextField department;

	private JLabel labelFirstName;
	private JLabel labelLastName;
	private JLabel labelDepartment;

	private JLabel labelResponse;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 */
	public ViewEmployeeAdd() {
		setArraySize(3);
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

	protected void initializeAttributes() {

		labelArray = new ArrayList<JLabel>();
		labelFirstName = new JLabel("First Name");
		labelLastName = new JLabel("Last Name");
		labelDepartment = new JLabel("Department Name ");
		labelArray.add(labelFirstName);
		labelArray.add(labelLastName);
		labelArray.add(labelDepartment);

		textFieldArray = new ArrayList<JTextField>();
		firstName = new JTextField();
		lastName = new JTextField();
		department = new JTextField();
		textFieldArray.add(firstName);
		textFieldArray.add(lastName);
		textFieldArray.add(department);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();

		if (source == submitButton) {
			submitMap = new HashMap<String, JTextField>();
			submitMap.put("firstname", textFieldArray.get(0));
			submitMap.put("lastname", textFieldArray.get(1));
			submitMap.put("department_name", textFieldArray.get(2));

			if (getLabelResponse() != null)
				getPanel().remove(getLabelResponse());
			try {
				BrowserMainapp controller = new BrowserMainapp();
				setLabelResponse(new JLabel(controller.addEmployee(submitMap)));

				getPanel().add(getLabelResponse());
				getPanel().revalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
