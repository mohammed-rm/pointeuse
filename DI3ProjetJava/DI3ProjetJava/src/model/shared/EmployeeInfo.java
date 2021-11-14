package model.shared;

import java.io.Serializable;

/**
 * 
 * @brief Class which represents main informations about an Employee. Useful for
 *        the Emulator which do not have to know about all the other
 *        informations about an Employee instance.
 * @implNote Implements Serializable.
 *
 */
public class EmployeeInfo implements Serializable {

	private static final long serialVersionUID = -2361731577118015767L;

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private Integer ID;
	private String firstname;
	private String lastname;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 * @throws Exception
	 */
	public EmployeeInfo() throws Exception {
		this(0, "default", "default");
	}

	/**
	 * @brief Constructor.
	 * @param ID
	 * @param firstname
	 * @param lastname
	 * @throws Exception
	 */
	public EmployeeInfo(Integer ID, String firstname, String lastname) throws Exception {
		setID(ID);
		setNames(firstname, lastname);
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/********************************* ID ********************************/

	/**
	 * @return the ID
	 */
	public Integer getID() {
		return ID;
	}

	/**
	 * @param ID the ID to set
	 * @throws Exception
	 */
	public void setID(Integer ID) throws Exception {
		this.ID = ID;
	}

	/***************************** firstName *****************************/

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 * @throws Exception
	 */
	public void setFirstname(String firstname) throws Exception {
		if (firstname.isBlank())
			throw new Exception("Please specify a firstname.     ");

		this.firstname = firstname;
	}

	/****************************** lastName *****************************/

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 * @throws Exception
	 */
	public void setLastname(String lastname) throws Exception {
		if (lastname.isBlank())
			throw new Exception("Please specify a lastname.      ");

		this.lastname = lastname;
	}

	/******************************** Names *******************************/

	/**
	 * @param firstname the firstname to set
	 * @param lastname  the lastname to set
	 * @throws Exception
	 */
	public void setNames(String firstname, String lastname) throws Exception {
		setFirstname(firstname);
		setLastname(lastname);
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	@Override
	public boolean equals(Object other) {
		if (((EmployeeInfo) other).getID().equals(getID())
				&& ((EmployeeInfo) other).getFirstname().equals(getFirstname())
				&& ((EmployeeInfo) other).getLastname().equals(getLastname())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "EmployeeInfo [ID=" + getID() + ", firstname=" + getFirstname() + ", lastname=" + getLastname() + "]";
	}

}
