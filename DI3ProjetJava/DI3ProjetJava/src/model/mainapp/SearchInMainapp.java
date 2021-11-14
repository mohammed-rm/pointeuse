package model.mainapp;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.shared.*;

/**
 * 
 * @brief Abstract class which implements method to search Employees or
 *        CheckInOuts in the Main Application.
 *
 */
public abstract class SearchInMainapp {

	/**
	 * @brief Static method which returns true if str1 begin by str2.
	 * @param str1
	 * @param str2
	 * @return boolean
	 */
	static public boolean areStringsMatching(String str1, String str2) {
		String str1Temp = str1.toLowerCase();
		String str2Temp = str2.toLowerCase();
		if (str1Temp.contains(str2Temp)) {
			for (Integer iterator = 0; iterator < str1Temp.length(); iterator++) {
				if (str1Temp.startsWith(str2Temp, iterator))
					return true;
			}
		}
		return false;
	}

	/*********************************************************************/
	/*************************** RETURN CHECKS ***************************/
	/*********************************************************************/

	/**
	 * 
	 * @param employee
	 * @param beforeCheck
	 * @param afterCheck
	 * @return ArrayList<CheckInOut>
	 */
	static public ArrayList<CheckInOut> searchCheckInOut(Employee employee, LocalDateTime beforeCheck,
			LocalDateTime afterCheck) {
		ArrayList<CheckInOut> resultList = new ArrayList<CheckInOut>();
		ArrayList<CheckInOut> listChecks = new ArrayList<>(employee.getListChecks());

		// the most recent checks are in the end of the array so we start searching from
		// there
		for (Integer iterator = listChecks.size() - 1; iterator >= 0; iterator--) {
			CheckInOut checkTmp = listChecks.get(iterator);
			if (checkTmp.getCheckTime().isAfter(beforeCheck) && checkTmp.getCheckTime().isBefore(afterCheck)) {
				resultList.add(checkTmp);
			}
		}

		return resultList;
	}

	/**
	 * @param department
	 * @param beforeCheck
	 * @param afterCheck
	 * @return ArrayList<CheckInOut>
	 */
	static public ArrayList<CheckInOut> searchCheckInOut(Department department, LocalDateTime beforeCheck,
			LocalDateTime afterCheck) {
		ArrayList<CheckInOut> resultList = new ArrayList<CheckInOut>();
		for (Employee currentEmployee : department.getListEmployees().values()) {
			resultList.addAll(searchCheckInOut(currentEmployee, beforeCheck, afterCheck));
		}
		return resultList;
	}

	// overall
	/**
	 * @param company
	 * @param beforeCheck
	 * @param afterCheck
	 * @return ArrayList<CheckInOut>
	 */
	static public ArrayList<CheckInOut> searchCheckInOut(Company company, LocalDateTime beforeCheck,
			LocalDateTime afterCheck) {
		ArrayList<CheckInOut> resultList = new ArrayList<CheckInOut>();
		for (Department currentDepartment : company.getListDepartment()) {
			resultList.addAll(searchCheckInOut(currentDepartment, beforeCheck, afterCheck));
		}
		return resultList;
	}

	/*********************************************************************/
	/************************* RETURN EMPLOYEES **************************/
	/*********************************************************************/

	/************************ according to check *************************/

	/**
	 * @param department
	 * @param beforeCheck
	 * @param afterCheck
	 * @return ArrayList<Employee>
	 */
	static public ArrayList<Employee> searchEmployee(Department department, LocalDateTime beforeCheck,
			LocalDateTime afterCheck) {
		ArrayList<Employee> resultList = new ArrayList<Employee>();
		for (Employee currentEmployee : department.getListEmployees().values()) {
			if (!searchCheckInOut(currentEmployee, beforeCheck, afterCheck).isEmpty()) {
				resultList.add(currentEmployee);
			}
		}
		return resultList;
	}

	// overall
	/**
	 * @param company
	 * @param beforeCheck
	 * @param afterCheck
	 * @return ArrayList<Employee>
	 */
	static public ArrayList<Employee> searchEmployee(Company company, LocalDateTime beforeCheck,
			LocalDateTime afterCheck) {
		ArrayList<Employee> resultList = new ArrayList<Employee>();
		for (Department currentDepartment : company.getListDepartment()) {
			resultList.addAll(searchEmployee(currentDepartment, beforeCheck, afterCheck));
		}
		return resultList;
	}

	/************************* according to ID ***************************/
	// necessarily only one employee per ID

	/**
	 * @param department
	 * @param ID
	 * @return Employee
	 */
	static public Employee searchEmployee(Department department, Integer ID) {
		return department.getListEmployees().get(ID);
	}

	// overall
	/**
	 * @param company
	 * @param ID
	 * @return Employee
	 */
	static public Employee searchEmployee(Company company, Integer ID) {
		for (Department currentDepartment : company.getListDepartment()) {
			if (searchEmployee(currentDepartment, ID) != null) {
				return searchEmployee(currentDepartment, ID);
			}
		}
		return null;
	}

	/************************ according to name **************************/

	/**
	 * @param department
	 * @param firstname
	 * @param lastname
	 * @return ArrayList<Employee>
	 */
	static public ArrayList<Employee> searchEmployee(Department department, String firstname, String lastname) {
		ArrayList<Employee> resultList = new ArrayList<Employee>();
		for (Employee currentEmployee : department.getListEmployees().values()) {
			if (areStringsMatching(currentEmployee.getFirstname(), firstname)
					&& areStringsMatching(currentEmployee.getLastname(), lastname)) {
				resultList.add(currentEmployee);
			}
		}
		return resultList;
	}

	/**
	 * @param department
	 * @param name
	 * @param nName
	 * @return ArrayList<Employee>
	 */
	static public ArrayList<Employee> searchEmployee(Department department, String name, Integer nName) {
		ArrayList<Employee> resultList = new ArrayList<Employee>();
		for (Employee currentEmployee : department.getListEmployees().values()) {
			if ((areStringsMatching(currentEmployee.getFirstname(), name) && nName == 0)
					|| (areStringsMatching(currentEmployee.getLastname(), name) && nName == 1)) {
				resultList.add(currentEmployee);
			}
		}
		return resultList;
	}

	// overall
	/**
	 * @param company
	 * @param firstname
	 * @param lastname
	 * @return ArrayList<Employee>
	 */
	static public ArrayList<Employee> searchEmployee(Company company, String firstname, String lastname) {
		ArrayList<Employee> resultList = new ArrayList<Employee>();
		for (Department currentDepartment : company.getListDepartment()) {
			resultList.addAll(searchEmployee(currentDepartment, firstname, lastname));
		}
		return resultList;
	}

	// overall
	/**
	 * @param company
	 * @param name
	 * @param nName
	 * @return ArrayList<Employee>
	 */
	static public ArrayList<Employee> searchEmployee(Company company, String name, Integer nName) {
		ArrayList<Employee> resultList = new ArrayList<Employee>();
		for (Department currentDepartment : company.getListDepartment()) {
			resultList.addAll(searchEmployee(currentDepartment, name, nName));
		}
		return resultList;
	}

	/******************************** all ********************************/

	/**
	 * @param department
	 * @return ArrayList<Employee>
	 */
	static public ArrayList<Employee> searchEmployee(Department department) {
		ArrayList<Employee> resultList = new ArrayList<Employee>();
		for (Employee currentEmployee : department.getListEmployees().values()) {
			resultList.add(currentEmployee);
		}
		return resultList;
	}

	// overall
	/**
	 * @param company
	 * @return ArrayList<Employee>
	 */
	static public ArrayList<Employee> searchEmployee(Company company) {
		ArrayList<Employee> resultList = new ArrayList<Employee>();
		for (Department currentDepartment : company.getListDepartment()) {
			resultList.addAll(searchEmployee(currentDepartment));
		}
		return resultList;
	}

}
