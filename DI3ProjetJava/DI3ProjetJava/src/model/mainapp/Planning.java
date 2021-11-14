package model.mainapp;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import model.mainapp.days.DayName;
import model.mainapp.days.DayPlanning;

/**
 * 
 * @brief Class which represents the weekly planning for an Employee.
 * @implNote Implements Serializable.
 *
 */
public class Planning implements Serializable {

	private static final long serialVersionUID = 7718443083220533564L;

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private ConcurrentHashMap<DayName, DayPlanning> planning;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 */
	public Planning() {
		this.planning = new ConcurrentHashMap<DayName, DayPlanning>();
		for (DayName dayName : DayName.values()) {
			DayPlanning defaultDay = new DayPlanning();
			this.planning.put(dayName, defaultDay);
		}
	}

	/**
	 * @brief Copy constructor.
	 * @param planning
	 */
	public Planning(ConcurrentHashMap<DayName, DayPlanning> planning) {
		setPlanning(planning);
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/**
	 * @return the planning
	 */
	public ConcurrentHashMap<DayName, DayPlanning> getPlanning() {
		return planning;
	}

	/**
	 * @param planning the planning to set
	 */
	private void setPlanning(ConcurrentHashMap<DayName, DayPlanning> planning) {
		this.planning = new ConcurrentHashMap<DayName, DayPlanning>(planning);
	}

	/**
	 * @param name
	 * @return DayPlanning (null if name does not exist)
	 */
	public DayPlanning getDayPlanning(DayName name) {
		return this.planning.get(name);
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	@Override
	public String toString() {
		return this.planning.toString().replace("=", "\n").replace(",", "\n\n");
	}

}
