package view.mainapp;

import javax.swing.*;

import controller.mainapp.MainappSettings;
import controller.mainapp.tcp.TCPMainAppSettings;

/**
 * 
 * @brief Main view of the main app. Contains all the others from the package.
 *
 */
public class ViewMainapp extends JFrame {

	/**
	 * @brief serialVersionUID.
	 */
	private static final long serialVersionUID = 3511639449947083712L;

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private JTabbedPane mainTabbedPane;
	private ViewEmployeesSearch frameEmployeesSearch;
	private ViewEmployeeAdd frameEmployeeAdd;
	private ViewCheckInOutsSearch frameCheckInOuts;
	private ViewSettings frameSettings;
	private MainappSettings current;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor.
	 * @param current
	 */
	public ViewMainapp(MainappSettings current) {
		this.current = current;
		build();

		// make a save when we close the app
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				current.save();
			}
		});
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/*************************** mainTabbedPane **************************/

	/**
	 * @return the mainTabbedPane
	 */
	public JTabbedPane getMainTabbedPane() {
		return mainTabbedPane;
	}

	/**
	 * @param mainTabbedPane the mainTabbedPane to set
	 */
	public void setMainTabbedPane(JTabbedPane mainTabbedPane) {
		this.mainTabbedPane = mainTabbedPane;
	}

	/*********************** frameEmployeesSearch ***********************/

	/**
	 * @return the frameEmployeesSearch
	 */
	public ViewEmployeesSearch getFrameEmployeesSearch() {
		return frameEmployeesSearch;
	}

	/**
	 * @param frameEmployees the frameEmployeesSearch to set
	 */
	public void setFrameEmployeesSearch(ViewEmployeesSearch frameEmployeesSearch) {
		this.frameEmployeesSearch = frameEmployeesSearch;
	}

	/************************ frameEmployeesAdd *************************/

	/**
	 * @return the frameEmployeeAdd
	 */
	public ViewEmployeeAdd getFrameEmployeeAdd() {
		return frameEmployeeAdd;
	}

	/**
	 * @param frameEmployeeAdd the frameEmployeeAdd to set
	 */
	public void setFrameEmployeeAdd(ViewEmployeeAdd frameEmployeeAdd) {
		this.frameEmployeeAdd = frameEmployeeAdd;
	}

	/************************* frameCheckInOuts *************************/

	/**
	 * @return the frameCheckInOuts
	 */
	public ViewCheckInOutsSearch getFrameCheckInOuts() {
		return frameCheckInOuts;
	}

	/**
	 * @param frameCheckInOuts the frameCheckInOuts to set
	 */
	public void setFrameCheckInOuts(ViewCheckInOutsSearch frameCheckInOuts) {
		this.frameCheckInOuts = frameCheckInOuts;
	}

	/************************* frameSettings *************************/

	/**
	 * @return the frameSettings
	 */
	public ViewSettings getFrameSettings() {
		return frameSettings;
	}

	/**
	 * @param frameSettings the frameSettings to set
	 */
	public void setFrameSettings(ViewSettings frameSettings) {
		this.frameSettings = frameSettings;
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	/**
	 * @brief Method which create main frame and panel.
	 */
	private void build() {

		// create frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(425, 125, 500, 500);
		this.setTitle("Main application™");

		// create panel
		buildContentMainTabbedPane();

		this.add(mainTabbedPane);
		this.setVisible(true);
	}

	/**
	 * @brief Method which builds main tabbedPane.
	 */
	private void buildContentMainTabbedPane() {
		mainTabbedPane = new JTabbedPane();

		// Employees
		frameEmployeesSearch = new ViewEmployeesSearch();
		frameEmployeeAdd = new ViewEmployeeAdd();
		JTabbedPane tabbedPaneEmployees = new JTabbedPane();
		tabbedPaneEmployees.addTab("Search", frameEmployeesSearch.getPanel());
		tabbedPaneEmployees.addTab("Add", frameEmployeeAdd.getPanel());
		mainTabbedPane.addTab("Employees", tabbedPaneEmployees);

		// CheckInOuts
		frameCheckInOuts = new ViewCheckInOutsSearch();
		JTabbedPane tabbedPaneCheckInOuts = new JTabbedPane();
		tabbedPaneCheckInOuts.addTab("Search", frameCheckInOuts.getPanel());
		mainTabbedPane.addTab("CheckInOuts", tabbedPaneCheckInOuts);

		// Settings
		frameSettings = new ViewSettings((TCPMainAppSettings) current);
		// JTabbedPane tabbedPaneSettings = new JTabbedPane();
		// tabbedPaneSettings.addTab("Settings", frameSettings.getPanel());
		mainTabbedPane.addTab("Settings", frameSettings.getPanel());

	}

}
