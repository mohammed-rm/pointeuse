package view.mainapp;

import java.awt.event.ActionEvent;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.mainapp.tcp.ModifyTCPSettings;
import controller.mainapp.tcp.TCPMainAppSettings;

/**
 * @brief Class to show connection settings (port number and IP address for the
 *        main application) in the interface.
 *
 */
public class ViewSettings extends ViewModel {

	/* ================================================================= */
	/**************************** ATTRIBUTES *****************************/
	/*********************************************************************/

	private JTextField fieldPortNumberServer;
	private JTextField fieldIPAddressServer;
	private JTextField fieldPortNumberClient;
	private JTextField fieldIPAddressClient;

	private JLabel labelPortNumberServer;
	private JLabel labelIPAddressServer;
	private JLabel labelPortNumberClient;
	private JLabel labelIPAddressClient;

	private JLabel labelResponse;

	private TCPMainAppSettings settings;

	/* ================================================================= */
	/**************************** BUILDER ********************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor
	 */
	public ViewSettings() {
		setArraySize(4);
		initializeAttributes();
		buildContentPanel();
	}

	public ViewSettings(TCPMainAppSettings settings) {
		this.settings = settings;
		setArraySize(4);
		initializeAttributes();
		buildContentPanel();
	}

	/* ================================================================= */
	/*************************** SETS/GETS *******************************/
	/*********************************************************************/

	/************************ fieldPortNumberServer **********************/
	/**
	 * @return the fieldPortNumberServer
	 */
	public JTextField getFieldPortNumberServer() {
		return fieldPortNumberServer;
	}

	/**
	 * @param fieldPortNumberServer the fieldPortNumberServer to set
	 */
	public void setFieldPortNumberServer(JTextField fieldPortNumberServer) {
		this.fieldPortNumberServer = fieldPortNumberServer;
	}

	/************************ fieldIPAddressServer ***********************/
	/**
	 * @return the fieldIPAddressServer
	 */
	public JTextField getFieldIPAddressServer() {
		return fieldIPAddressServer;
	}

	/**
	 * @param fieldIPAddressServer the fieldIPAddressServer to set
	 */
	public void setFieldIPAddressServer(JTextField fieldIPAddressServer) {
		this.fieldIPAddressServer = fieldIPAddressServer;
	}

	/*********************** fieldPortNumberClient ***********************/
	/**
	 * @return the fieldPortNumberClient
	 */
	public JTextField getFieldPortNumberClient() {
		return fieldPortNumberClient;
	}

	/**
	 * @param fieldPortNumberClient the fieldPortNumberClient to set
	 */
	public void setFieldPortNumberClient(JTextField fieldPortNumberClient) {
		this.fieldPortNumberClient = fieldPortNumberClient;
	}

	/*********************** fieldIPAddressClient ************************/
	/**
	 * @return the fieldIPAddressClient
	 */
	public JTextField getFieldIPAddressClient() {
		return fieldIPAddressClient;
	}

	/**
	 * @param fieldIPAddressClient the fieldIPAddressClient to set
	 */
	public void setFieldIPAddressClient(JTextField fieldIPAddressClient) {
		this.fieldIPAddressClient = fieldIPAddressClient;
	}

	/*********************** labelPortNumberServer ***********************/
	/**
	 * @return the labelPortNumberServer
	 */
	public JLabel getLabelPortNumberServer() {
		return labelPortNumberServer;
	}

	/**
	 * @param labelPortNumberServer the labelPortNumberServer to set
	 */
	public void setLabelPortNumberServer(JLabel labelPortNumberServer) {
		this.labelPortNumberServer = labelPortNumberServer;
	}

	/************************ labelIPAddressServer ***********************/
	/**
	 * @return the labelIPAddressServer
	 */
	public JLabel getLabelIPAddressServer() {
		return labelIPAddressServer;
	}

	/**
	 * @param labelIPAddressServer the labelIPAddressServer to set
	 */
	public void setLabelIPAddressServer(JLabel labelIPAddressServer) {
		this.labelIPAddressServer = labelIPAddressServer;
	}

	/********************** labelPortNumberClient ************************/
	/**
	 * @return the labelPortNumberClient
	 */
	public JLabel getLabelPortNumberClient() {
		return labelPortNumberClient;
	}

	/**
	 * @param labelPortNumberClient the labelPortNumberClient to set
	 */
	public void setLabelPortNumberClient(JLabel labelPortNumberClient) {
		this.labelPortNumberClient = labelPortNumberClient;
	}

	/********************** labelIPAddressClient *************************/
	/**
	 * @return the labelIPAddressClient
	 */
	public JLabel getLabelIPAddressClient() {
		return labelIPAddressClient;
	}

	/**
	 * @param labelIPAddressClient the labelIPAddressClient to set
	 */
	public void setLabelIPAddressClient(JLabel labelIPAddressClient) {
		this.labelIPAddressClient = labelIPAddressClient;
	}

	/*************************** labelresponse ***************************/

	/**
	 * @return the labelResponse
	 */
	public JLabel getLabelResponse() {
		return labelResponse;
	}

	/**
	 * @param labelResponse the labelResponse to set
	 */
	public void setLabelResponse(JLabel labelResponse) {
		this.labelResponse = labelResponse;
	}

	/**************************** settings *******************************/
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
	/**************************** METHODS ********************************/
	/*********************************************************************/

	@Override
	protected void initializeAttributes() {
		labelArray = new ArrayList<JLabel>();

		labelPortNumberServer = new JLabel("Server Port Number ");
		labelIPAddressServer = new JLabel("Server IP Address ");
		labelPortNumberClient = new JLabel("Client Port Number ");
		labelIPAddressClient = new JLabel("Client IP Address ");

		labelArray.add(labelPortNumberServer);
		labelArray.add(labelIPAddressServer);
		labelArray.add(labelPortNumberClient);
		labelArray.add(labelIPAddressClient);

		textFieldArray = new ArrayList<JTextField>();

		fieldPortNumberServer = new JTextField();
		fieldIPAddressServer = new JTextField();
		fieldPortNumberClient = new JTextField();
		fieldIPAddressClient = new JTextField();

		textFieldArray.add(fieldPortNumberServer);
		textFieldArray.add(fieldIPAddressServer);
		textFieldArray.add(fieldPortNumberClient);
		textFieldArray.add(fieldIPAddressClient);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();

		if (source == submitButton) {
			submitMap = new HashMap<String, JTextField>();
			submitMap.put("portnumber_server", textFieldArray.get(0));
			submitMap.put("ipaddress_server", textFieldArray.get(1));
			submitMap.put("portnumber_client", textFieldArray.get(2));
			submitMap.put("ipaddress_client", textFieldArray.get(3));

			if (getLabelResponse() != null)
				getPanel().remove(getLabelResponse());
			try {
				ModifyTCPSettings settingsToModify = new ModifyTCPSettings(settings);
				setLabelResponse(new JLabel(settingsToModify.ModifyConnectionSettings(submitMap)));

				getPanel().add(getLabelResponse());
				getPanel().revalidate();
			} catch (UnknownHostException e) {
				System.out.println(e.getMessage());
			}

		}

	}
}
