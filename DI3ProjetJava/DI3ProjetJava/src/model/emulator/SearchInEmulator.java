package model.emulator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.emulator.Emulator;
import model.shared.*;

/**
 * 
 * @brief Abstract class which implements method to search employee or
 *        CheckInOuts in an Emulator.
 *
 */
public abstract class SearchInEmulator {

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
	/************************* RETURN EMPLOYEES **************************/
	/*********************************************************************/

	/************************ according to check *************************/

	// overall
	/**
	 * @param history
	 * @param beforeCheck
	 * @param afterCheck
	 * @return ArrayList<EmployeeInfo>
	 */
	static public ArrayList<EmployeeInfo> searchEmployee(History history, LocalDateTime beforeCheck,
			LocalDateTime afterCheck) {
		ArrayList<EmployeeInfo> resultList = new ArrayList<EmployeeInfo>();
		Hashtable<EmployeeInfo, CopyOnWriteArrayList<CheckInOut>> totalList = history.getChecksPerEmployee();

		for (EmployeeInfo currentEmployeeInfo : totalList.keySet()) {
			ArrayList<CheckInOut> listChecks = new ArrayList<>(history.getChecksPerEmployee().get(currentEmployeeInfo));

			boolean isInto = false;
			for (Integer iterator = listChecks.size() - 1; iterator >= 0 && !isInto; iterator--) {
				CheckInOut checkTmp = listChecks.get(iterator);
				if (checkTmp.getCheckTime().isAfter(beforeCheck) && checkTmp.getCheckTime().isBefore(afterCheck)) {
					isInto = true;
					resultList.add(currentEmployeeInfo);
				}
			}

		}
		return new ArrayList<EmployeeInfo>(resultList);
	}

	/************************* according to ID ***************************/
	// necessarily only one employee per ID

	// overall
	/**
	 * @param history
	 * @param ID
	 * @return EmployeeInfo
	 */
	static public EmployeeInfo searchEmployee(History history, Integer ID) {
		for (EmployeeInfo currentEmployeeInfo : Emulator.getListEmployeeInfo()) {
			if (currentEmployeeInfo.getID().equals(ID)) {
				return currentEmployeeInfo;
			}
		}
		return null;
	}

	/************************ according to name **************************/

	// overall
	/**
	 * @param history
	 * @param firstname
	 * @param lastname
	 * @return ArrayList<EmployeeInfo>
	 */
	static public ArrayList<EmployeeInfo> searchEmployee(History history, String firstname, String lastname) {
		ArrayList<EmployeeInfo> resultList = new ArrayList<EmployeeInfo>();
		for (EmployeeInfo currentEmployeeInfo : Emulator.getListEmployeeInfo()) {
			if (areStringsMatching(currentEmployeeInfo.getFirstname(), firstname)
					&& areStringsMatching(currentEmployeeInfo.getLastname(), lastname)) {
				resultList.add(currentEmployeeInfo);
			}
		}
		return new ArrayList<EmployeeInfo>(resultList);
	}

	// overall
	/**
	 * @param history
	 * @param name
	 * @param nName
	 * @return ArrayList<EmployeeInfo>
	 */
	static public ArrayList<EmployeeInfo> searchEmployee(History history, String name, Integer nName) {
		ArrayList<EmployeeInfo> resultList = new ArrayList<EmployeeInfo>();
		for (EmployeeInfo currentEmployeeInfo : Emulator.getListEmployeeInfo()) {
			if ((areStringsMatching(currentEmployeeInfo.getFirstname(), name) && nName == 0)
					|| (areStringsMatching(currentEmployeeInfo.getLastname(), name) && nName == 1)) {
				resultList.add(currentEmployeeInfo);
			}
		}
		return new ArrayList<EmployeeInfo>(resultList);
	}
}
