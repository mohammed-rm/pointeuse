package controller.mainapp.tcp;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import javax.swing.JTextField;

/**
 * @brief Class to modify port number and IP address
 *
 */
public class ModifyTCPSettings implements Serializable {

	/* ================================================================= */
	/*************************** ATTRIBUTES ******************************/
	/*********************************************************************/

	private static final long serialVersionUID = -8826014027937834304L;
	private String regexPattern = "\\.";
	private TCPMainAppSettings settings;

	/* ================================================================= */
	/**************************** BUILDERS *******************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor
	 */
	public ModifyTCPSettings() {
		regexPattern = new String();
	}

	/**
	 * @brief One argument copy constructor
	 * @param settings2
	 */
	public ModifyTCPSettings(TCPMainAppSettings settings2) {
		this.settings = settings2;
	}

	/* ================================================================= */
	/**************************** GETS/SETS ******************************/
	/*********************************************************************/

	/*************************** regexPattern ****************************/
	/**
	 * @return the regexPattern
	 */
	public String getRegexPattern() {
		return regexPattern;
	}

	/**
	 * @param regexPattern the regexPattern to set
	 */
	public void setRegexPattern(String regexPattern) {
		this.regexPattern = regexPattern;
	}

	/***************************** settings ******************************/
	/**
	 * @return the settings
	 */
	public TCPMainAppSettings getSettings() {
		return settings;
	}

	/**
	 * @param settings the settings to set
	 */
	public void setSettings(TCPMainAppSettings settings) {
		this.settings = settings;
	}

	/* ================================================================= */
	/***************************** METHODS *******************************/
	/*********************************************************************/

	/**
	 * @brief Method to modify port number and IP address in the main application
	 * @param request
	 * @return a string message
	 */
	public String ModifyConnectionSettings(HashMap<String, JTextField> request) throws UnknownHostException {

		// extract information from the request
		String newPortNumberServer = request.get("portnumber_server").getText();
		String[] newIPAddressServerStr = request.get("ipaddress_server").getText().split(getRegexPattern());
		String newPortNumberClient = request.get("portnumber_client").getText();
		String[] newIPAddressClientStr = request.get("ipaddress_client").getText().split(getRegexPattern());

		if (!newPortNumberServer.isBlank() && !newPortNumberClient.isBlank() && newIPAddressServerStr.length == 4
				&& newIPAddressClientStr.length == 4) {
			// Converting strings to the correct form
			Integer portNumberServerToSet = Integer.parseInt(newPortNumberServer);
			byte[] IPAddressServerToSet = new byte[4];
			for (Integer iterator = 0; iterator < 4; iterator++)
				IPAddressServerToSet[iterator] = Integer.valueOf(Integer.parseInt(newIPAddressServerStr[iterator]))
						.byteValue();
			Integer portNumberClientToSet = Integer.parseInt(newPortNumberClient);
			byte[] IPAddressClientToSet = new byte[4];
			for (Integer iterator = 0; iterator < 4; iterator++)
				IPAddressClientToSet[iterator] = Integer.valueOf(Integer.parseInt(newIPAddressClientStr[iterator]))
						.byteValue();

			// Changing elements
			settings.setNumPortServer(portNumberServerToSet);
			settings.setIPaddressServer(InetAddress.getByAddress(IPAddressServerToSet));
			settings.setNumPortServer(portNumberClientToSet);
			settings.setIPaddressServer(InetAddress.getByAddress(IPAddressClientToSet));

			return "Successfull change       ";
		} else
			return "Incorrect Form";
	}
}
