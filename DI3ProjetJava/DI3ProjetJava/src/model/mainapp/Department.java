package model.mainapp;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * @brief Class which represents a department of the company.
 * @implNote Implements Serializable.
 *
 */
public class Department implements Serializable {

	private static final long serialVersionUID = 3779082753004859354L;

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private String name;

	// a map in which each employee can be directly obtained from his ID
	private HashMap<Integer, Employee> listEmployees;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 * @throws Exception
	 */
	public Department() throws Exception {
		this("default");
	}

	/**
	 * @brief Constructor.
	 * @param name
	 * @throws Exception
	 */
	public Department(String name) throws Exception {
		this(name, new Employee("default", "default"));
	}

	/**
	 * @brief Constructor.
	 * @param name
	 * @param boss
	 * @throws Exception
	 */
	public Department(String name, Employee boss) throws Exception {
		setName(name);
		boss.setDepartment(getName());
		setListEmployees(new HashMap<>());
		addEmployee(boss);
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/******************************** Name *******************************/

	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*************************** ListEmployees ***************************/

	/**
	 * @return the listEmployees
	 */
	public HashMap<Integer, Employee> getListEmployees() {
		return listEmployees;
	}

	/**
	 * @param listEmployees the listEmployees to set
	 */
	protected void setListEmployees(HashMap<Integer, Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	/**
	 * @brief Method used to add an employee in listEmployees.
	 * @param employee
	 * @throws Exception
	 */
	public void addEmployee(Employee employee) throws Exception {
		if (getListEmployees().containsKey(employee.getID())) {
			throw new Exception("This employee is already in the department");
		}

		employee.setDepartment(getName());
		getListEmployees().put(employee.getID(), employee);
	}

	@Override
	public String toString() {
		String msg = "Department\t[name=" + name + ",\n\t\t listEmployees=[\n\t\t\t\t";
		for (Employee employeeTmp : listEmployees.values()) {
			msg += employeeTmp.toString() + "\n\t\t\t\t";
		}
		msg = msg.substring(0, msg.length() - 1);
		msg += "]\n\t\t]";
		return msg;
	}

}
