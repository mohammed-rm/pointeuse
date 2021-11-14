package model.emulator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.emulator.Emulator;
import model.shared.CheckInOut;
import model.shared.EmployeeInfo;

/**
 * 
 * @brief Class to represent events which occur during CheckInOut from the
 *        emulator.
 *
 */
public class History implements Serializable {

	/* ================================================================= */
	/************************* CLASS ATTRIBUTES **************************/
	/*********************************************************************/

	private static final long serialVersionUID = 1L;

	// All CheckInOut per employee from the first day
	private Hashtable<EmployeeInfo, CopyOnWriteArrayList<CheckInOut>> checksPerEmployee;

	/*
	 * All CheckInOut per employee during a day. The table is reset to 0 at the end
	 * of day
	 */
	private Hashtable<LocalDate, Hashtable<EmployeeInfo, CopyOnWriteArrayList<CheckInOut>>> checksPerEmployeePerDay;

	/* ================================================================= */
	/*************************** BUILDERS ********************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 */
	public History() {
		checksPerEmployee = new Hashtable<EmployeeInfo, CopyOnWriteArrayList<CheckInOut>>();
		for (EmployeeInfo currentEmployeeInfo : Emulator.getListEmployeeInfo()) {
			if (checksPerEmployee.get(currentEmployeeInfo) == null)
				checksPerEmployee.put(currentEmployeeInfo, new CopyOnWriteArrayList<CheckInOut>());
		}
		checksPerEmployeePerDay = new Hashtable<LocalDate, Hashtable<EmployeeInfo, CopyOnWriteArrayList<CheckInOut>>>();
	}

	/* ================================================================= */
	/*************************** GETTERS/SETTERS *************************/
	/*********************************************************************/

	/************************** checksPerEmployee ************************/
	/**
	 * @return the checksPerEmployee
	 */
	public Hashtable<EmployeeInfo, CopyOnWriteArrayList<CheckInOut>> getChecksPerEmployee() {
		return checksPerEmployee;
	}

	/**
	 * @param checksPerEmployee the checksPerEmployee to set
	 */
	public void setChecksPerEmployee(Hashtable<EmployeeInfo, CopyOnWriteArrayList<CheckInOut>> checksPerEmployee) {
		this.checksPerEmployee = checksPerEmployee;
	}

	/*********************** checksPerEmployeePerDay *********************/
	/**
	 * @return the checksPerEmployeePerDay
	 */
	public Hashtable<LocalDate, Hashtable<EmployeeInfo, CopyOnWriteArrayList<CheckInOut>>> getChecksPerEmployeePerDay() {
		return checksPerEmployeePerDay;
	}

	/**
	 * @param checksPerEmployeePerDay the checksPerEmployeePerDay to set
	 */
	public void setChecksPerEmployeePerDay(
			Hashtable<LocalDate, Hashtable<EmployeeInfo, CopyOnWriteArrayList<CheckInOut>>> checksPerEmployeePerDay) {
		this.checksPerEmployeePerDay = checksPerEmployeePerDay;
	}

	/* ================================================================= */
	/***************************** METHODS *******************************/
	/*********************************************************************/

	/**
	 * @brief Static method which adds a check to the EmployeeInfo list.
	 * @param check
	 * @param info
	 */
	public void addToHistory(CheckInOut check, EmployeeInfo info) {
		if (!checksPerEmployee.keySet().contains(info)) {
			checksPerEmployee.put(info, new CopyOnWriteArrayList<CheckInOut>());
		}
		checksPerEmployee.get(info).add(check);
		addToHistory(LocalDate.now(), checksPerEmployee);
	}

	/**
	 * @brief Static method which add a check to the EmployeeInfo list with date.
	 * @param check
	 * @param info
	 * @param date
	 */
	public void addToHistory(LocalDate date,
			Hashtable<EmployeeInfo, CopyOnWriteArrayList<CheckInOut>> checksPerEmployee) {
		checksPerEmployeePerDay.put(date, checksPerEmployee);
	}
}
