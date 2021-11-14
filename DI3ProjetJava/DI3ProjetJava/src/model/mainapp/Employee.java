package model.mainapp;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import model.shared.CheckInOut;
import model.shared.EmployeeInfo;

/**
 * @brief Class which represents an Employee in the company.
 */
public class Employee extends EmployeeInfo {

	private static final long serialVersionUID = 1L;

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	// ID 0 is forbidden
	private static ArrayList<Integer> listUsedIDs = new ArrayList<>(Arrays.asList(0));
	private String department;
	private Planning planning;
	private CopyOnWriteArrayList<CheckInOut> listChecks;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 * @throws Exception
	 */
	public Employee() throws Exception {
		this("default", "default");
	}

	/**
	 * @brief Constructor.
	 * @param firstname
	 * @param lastname
	 * @throws Exception
	 */
	public Employee(String firstname, String lastname) throws Exception {
		// generate a new ID
		Integer availableID;
		ArrayList<Integer> listUnusedIDs = new ArrayList<Integer>();
		for (Integer unusedID = 0; unusedID < getlistUsedIDs().size(); unusedID++) {
			if (!getlistUsedIDs().contains(unusedID))
				listUnusedIDs.add(unusedID);
		}
		if (!listUnusedIDs.isEmpty())
			availableID = listUnusedIDs.get(0);
		else
			availableID = getlistUsedIDs().size();

		addUsedIDToList(availableID); // reserve availableID in listUsedIDs
		try {
			setID(availableID);
			setFirstname(firstname);
			setLastname(lastname);
			setDepartment("default");
			setPlanning(new Planning());
			setListChecks(new CopyOnWriteArrayList<CheckInOut>());
		} catch (Exception e) {
			Employee.getlistUsedIDs().remove(getlistUsedIDs().size() - 1);
			throw e;
		}
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/***************************** listUsedIDs ****************************/

	/**
	 * @return listUsedIDs
	 */
	public static ArrayList<Integer> getlistUsedIDs() {
		return listUsedIDs;
	}

	/**
	 * @param ID the ID to add
	 */
	public static void addUsedIDToList(Integer ID) {
		Employee.listUsedIDs.add(ID);
	}

	/***************************** Department ****************************/

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/****************************** Planning *****************************/

	/**
	 * @return planning
	 */
	public Planning getPlanning() {
		return planning;
	}

	/**
	 * @param planning the planning to set
	 */
	public void setPlanning(Planning planning) {
		this.planning = planning;
	}

	/***************************** ListChecks ****************************/

	/**
	 * @return listChecks
	 */
	public CopyOnWriteArrayList<CheckInOut> getListChecks() {
		return listChecks;
	}

	/**
	 * @param listChecks the listChecks to set
	 */
	protected void setListChecks(CopyOnWriteArrayList<CheckInOut> listChecks) {
		this.listChecks = listChecks;
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	@Override
	public String toString() {
		return "Employee [ID=" + getID() + ", firstname=" + getFirstname() + ", lastname=" + getLastname()
				+ ", department=" + getDepartment()
				// + ", planning=\n" + getPlanning() + "\n\n"
				+ ", listChecks=" + getListChecks() + "]";
	}

}
