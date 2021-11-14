package model.mainapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @brief Abstract class that represents the Company by its department list.
 * @implNote Implements Serializable and Cloneable.
 *
 */
public class Company implements Serializable, Cloneable {

	private static final long serialVersionUID = -5385628284286557836L;

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private ArrayList<Department> listDepartment;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 */
	public Company() {
		this.listDepartment = new ArrayList<Department>();
	}

	/**
	 * @brief Constructor.
	 * @param listDepartment
	 */
	public Company(ArrayList<Department> listDepartment) {
		setListDepartment(listDepartment);
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/**************************** listDepartment *************************/

	/**
	 * @return the listDepartment
	 */
	public ArrayList<Department> getListDepartment() {
		return listDepartment;
	}

	/**
	 * @param listDepartment the listDepartment to set
	 */
	public void setListDepartment(ArrayList<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}

	/****************************** department ***************************/

	/**
	 * @param department the department to add
	 * @throws Exception
	 * @return Department
	 */
	public Department getDepartment(String departmentName) throws Exception {
		for (Department currentDepartment : getListDepartment()) {
			if (currentDepartment.getName().equals(departmentName))
				return currentDepartment;
		}
		return null;
	}

	/**
	 * @param department the department to add
	 * @throws Exception
	 */
	public void addDepartment(Department department) throws Exception {
		for (Department currentDepartment : getListDepartment()) {
			if (currentDepartment == department || currentDepartment.getName() == department.getName())
				throw new Exception("This department exists in this company already");
		}
		getListDepartment().add(department);
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	@Override
	public String toString() {
		String msg = "Company[\n";
		for (Department departmentTmp : getListDepartment()) {
			msg += "    " + departmentTmp.toString() + ",\n";
		}
		msg = msg.substring(0, msg.length() - 2);
		msg += "\n]";
		return msg;
	}

}
