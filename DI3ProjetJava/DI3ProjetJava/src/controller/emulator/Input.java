package controller.emulator;

import java.time.LocalDateTime;
import javax.swing.JTextField;
import controller.emulator.tcp.TCPClientEmulator;
import model.shared.CheckInOut;
import model.shared.EmployeeInfo;

/**
 * @brief Class for emulator inputs and checks
 *
 */
public class Input {

	/* ================================================================= */
	/*************************** ATTRIBUTES ******************************/
	/*********************************************************************/

	private EmulatorSettings settings;

	/* ================================================================= */
	/**************************** BUILDERS *******************************/
	/*********************************************************************/

	/**
	 * @brief One argument copy constructor
	 * @param settings2
	 */
	public Input(EmulatorSettings settings) {
		this.settings = settings;
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/**
	 * @return the settings
	 */
	public EmulatorSettings getSettings() {
		return settings;
	}

	/**
	 * @param settings the settings to set
	 */
	public void setSettings(EmulatorSettings settings) {
		this.settings = settings;
	}

	/*********************************************************************/
	/***************************** METHODS *******************************/
	/*********************************************************************/
	
	/**
	 * @brief Method to send the check in out when an employee enters his id
	 * @param emulator
	 * @throws Exception
	 * @brief Method to get employee id during a check in out and to send it.
	 */
	public void sendCheck(JTextField request) throws Exception {
		CheckInOut checkInOutToSend = null;
		try {
			Integer ID = Integer.parseInt(request.getText());
			checkInOutToSend = new CheckInOut(Integer.parseInt(request.getText()), LocalDateTime.now(), false);

			// Employee in Emulator database
			boolean found = false;
			for (EmployeeInfo employeeTemp : EmulatorSettings.getListEmployeeInfo()) {
				if (employeeTemp.getID().equals(ID)) {
					found = true;
					EmulatorSettings.getWaitingChecks().add(checkInOutToSend);

					new Thread(new TCPClientEmulator(checkInOutToSend, settings.getIPaddressClient(),
							settings.getNumPortClient())).start();
				}
			}
			if (!found)
				System.out.println("ID does not exist in the list.\nMaybe it needs to be downloaded. Try again in 2s...");

		} catch (NumberFormatException e) {
			System.out.println("Unexpected argument");
		}
	}
}
