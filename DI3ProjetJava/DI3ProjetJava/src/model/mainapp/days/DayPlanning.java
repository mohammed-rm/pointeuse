package model.mainapp.days;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * 
 * @brief Class which represents a day in an Employee planning.
 * @implNote Implements Serializable.
 *
 */
public class DayPlanning implements Serializable {

	private static final long serialVersionUID = -1187588611307841876L;

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private LocalTime arrivalTime;
	private LocalTime leavingTime;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 */
	public DayPlanning() {
		setArrivalTime(LocalTime.of(8, 15));
		setLeavingTime(LocalTime.of(17, 0));
	}

	/**
	 * @brief Constructor.
	 * @param arrivalTime
	 * @param leavingTime
	 */
	public DayPlanning(LocalTime arrivalTime, LocalTime leavingTime) {
		setArrivalTime(arrivalTime);
		setLeavingTime(leavingTime);
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/**************************** ArrivalTime ****************************/

	/**
	 * @return the arrivalTime
	 */
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**************************** LeavingTime ***************************/

	/**
	 * @return the leavingTime
	 */
	public LocalTime getLeavingTime() {
		return leavingTime;
	}

	/**
	 * @param leavingTime the leavingTime to set
	 */
	public void setLeavingTime(LocalTime leavingTime) {
		this.leavingTime = leavingTime;
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	@Override
	public String toString() {
		return "Arrival Time : " + this.arrivalTime + "\nLeaving Time : " + this.leavingTime;
	}

}
