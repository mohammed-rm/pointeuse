package view.emulator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.*;

import controller.emulator.EmulatorSettings;
import controller.emulator.Input;
import model.shared.CheckInOut;

public class ViewEmulator extends JFrame implements ActionListener {
	/* ================================================================= */
	/************************* CLASS ATTRIBUTES **************************/
	/*********************************************************************/

	private static final long serialVersionUID = 1L;

	// Main frame and panel
	private JFrame frame;
	private JPanel panel;

	// Labels
	private JLabel frontTitle;
	private JLabel reelTimeClock;
	private JLabel roundTime;
	private JLabel IDLabel;

	// Time and date
	private Date currentDate;
	private LocalTime time;
	private LocalTime lastQuarter;

	// Button
	private JButton startButton;

	// Text field
	private JTextField IDField;

	// Dimension
	private Dimension sizeTitle;
	private Dimension sizeIDLabel;

	/*************** Size and Location elements ***************/
	// Frame
	private static final int FRAME_SIZE_X = 500;
	private static final int FRAME_SIZE_Y = 300;

	// Button
	private static final int BUTTON_LOCATION_X = 330;
	private static final int BUTTON_LOCATION_Y = 208;
	private static final int BUTTON_SIZE_X = 150;
	private static final int BUTTON_SIZE_Y = 50;

	// Text field
	private static final int ID_FIELD_X = 90;
	private static final int ID_FIELD_Y = 223;
	private static final int ID_FIELD_W = 200;
	private static final int ID_FIELD_H = 30;

	// Frame title
	private static final int FRONT_TITLE_X = 175;
	private static final int FRONT_TITLE_Y = 15;
	// private static final int FRONT_TITLE_W;
	// private static final int FRONT_TITLE_H;

	// Time
	private static final int TIME_X = 118;
	private static final int TIME_Y = 40;
	private static final int TIME_W = 400;
	private static final int TIME_H = 30;

	private static final int ROUNDED_TIME_X = 195;
	private static final int ROUNDED_TIME_Y = 40;
	private static final int ROUNDED_TIME_W = 100;
	private static final int ROUNDED_TIME_H = 100;

	// ID
	private static final int ID_LABEL_X = 10;
	private static final int ID_LABEL_Y = 230;
	// private static final int ID_LABEL_W;
	// private static final int ID_LABEL_H;

	/*************** Titles ***************/
	private static final String FRAME_TITLE = "JavaTech™ V.1.0";
	private static final String PANEL_NAME = "Time Tracker Emulator";
	private static final String BUTTON_NAME = "Check in/out";
	private static final String ID_LABEL = "Employee ID";

	// Data to be sent to the controller
	private LocalDate date; // Date sent with a check in out
	private CheckInOut checks;
	private EmulatorSettings settings;

	/* ================================================================= */
	/***************************** BUILDER *******************************/
	/*********************************************************************/
	/**
	 * @brief Default constructor
	 */
	public ViewEmulator(EmulatorSettings current) {
		this.settings = current;
		build();

		// make a save when we close the app
		getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				current.save();
			}
		});
	}

	/* ================================================================= */
	/**************************** GETS/SETS ******************************/
	/*********************************************************************/

	/****************************** frame ********************************/
	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/****************************** panel ********************************/
	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**************************** frontTitle *****************************/
	/**
	 * @return the frontTitle
	 */
	public JLabel getFrontTitle() {
		return frontTitle;
	}

	/**
	 * @param frontTitle the frontTitle to set
	 */
	public void setFrontTitle(JLabel frontTitle) {
		this.frontTitle = frontTitle;
	}

	/************************** reelTimeClock ****************************/
	/**
	 * @return the reelTimeClock
	 */
	public JLabel getReelTimeClock() {
		return reelTimeClock;
	}

	/**
	 * @param reelTimeClock the reelTimeClock to set
	 */
	public void setReelTimeClock(JLabel reelTimeClock) {
		this.reelTimeClock = reelTimeClock;
	}

	/**************************** roundTime ******************************/
	/**
	 * @return the roundTime
	 */
	public JLabel getRoundTime() {
		return roundTime;
	}

	/**
	 * @param roundTime the roundTime to set
	 */
	public void setRoundTime(JLabel roundTime) {
		this.roundTime = roundTime;
	}

	/***************************** IDLabel *******************************/
	/**
	 * @return the iDLabel
	 */
	public JLabel getIDLabel() {
		return IDLabel;
	}

	/**
	 * @param iDLabel the iDLabel to set
	 */
	public void setIDLabel(JLabel iDLabel) {
		IDLabel = iDLabel;
	}

	/*************************** currentDate *****************************/
	/**
	 * @return the currentDate
	 */
	public Date getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	/****************************** time *********************************/
	/**
	 * @return the time
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}

	/*************************** lastQuarter *****************************/
	/**
	 * @return the lastQuarter
	 */
	public LocalTime getLastQuarter() {
		return lastQuarter;
	}

	/**
	 * @param lastQuarter the lastQuarter to set
	 */
	public void setLastQuarter(LocalTime lastQuarter) {
		this.lastQuarter = lastQuarter;
	}

	/*************************** startButton *****************************/
	/**
	 * @return the startButton
	 */
	public JButton getStartButton() {
		return startButton;
	}

	/**
	 * @param startButton the startButton to set
	 */
	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	/**************************** IDField ********************************/
	/**
	 * @return the iDField
	 */
	public JTextField getIDField() {
		return IDField;
	}

	/**
	 * @param iDField the iDField to set
	 */
	public void setIDField(JTextField iDField) {
		IDField = iDField;
	}

	/*************************** sizeTitle *******************************/
	/**
	 * @return the sizeTitle
	 */
	public Dimension getSizeTitle() {
		return sizeTitle;
	}

	/**
	 * @param sizeTitle the sizeTitle to set
	 */
	public void setSizeTitle(Dimension sizeTitle) {
		this.sizeTitle = sizeTitle;
	}

	/************************** sizeIDLabel ******************************/
	/**
	 * @return the sizeIDLabel
	 */
	public Dimension getSizeIDLabel() {
		return sizeIDLabel;
	}

	/**
	 * @param sizeIDLabel the sizeIDLabel to set
	 */
	public void setSizeIDLabel(Dimension sizeIDLabel) {
		this.sizeIDLabel = sizeIDLabel;
	}

	/**************************** date ***********************************/
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/*************************** checks ***********************************/
	/**
	 * @return the checks
	 */
	public CheckInOut getChecks() {
		return checks;
	}

	/**
	 * @param checks the checks to set
	 */
	public void setChecks(CheckInOut checks) {
		this.checks = checks;
	}

	/************************** FRAME_TITLE ******************************/
	/**
	 * @return the frameTitle
	 */
	public static String getFrameTitle() {
		return FRAME_TITLE;
	}

	/************************** PANEL_NAME ******************************/
	/**
	 * @return the panelName
	 */
	public static String getPanelName() {
		return PANEL_NAME;
	}

	/************************** BUTTON_NAME ******************************/
	/**
	 * @return the buttonName
	 */
	public static String getButtonName() {
		return BUTTON_NAME;
	}

	/*************************** ID_LABEL ********************************/
	/**
	 * @return the idLabel
	 */
	public static String getIdLabel() {
		return ID_LABEL;
	}

	/* ================================================================= */
	/****************************** METHOD *******************************/
	/*********************************************************************/

	public void build() {
		// Panel creation
		frame = new JFrame();
		panel = (JPanel) frame.getContentPane();

		// Panel configuration
		frame.setTitle(FRAME_TITLE);
		frame.setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		panel.setName(PANEL_NAME);
		panel.setLayout(null);

		// Check in/out button
		startButton = new JButton(BUTTON_NAME);
		startButton.setBounds(BUTTON_LOCATION_X, BUTTON_LOCATION_Y, BUTTON_SIZE_X, BUTTON_SIZE_Y);
		startButton.addActionListener(this);

		// Clock
		reelTimeClock = new JLabel();
		roundTime = new JLabel();

		currentDate = new Date();
		time = LocalTime.now();
		lastQuarter = time.truncatedTo(ChronoUnit.HOURS).plusMinutes(15 * (time.getMinute() / 15));
		date = LocalDate.now();

		// Other elements
		frontTitle = new JLabel();
		IDLabel = new JLabel(ID_LABEL);
		IDField = new JTextField();

		frontTitle.setText(PANEL_NAME);

		reelTimeClock.setText("Today is : " + currentDate);
		reelTimeClock.setBounds(TIME_X, TIME_Y, TIME_W, TIME_H);
		roundTime.setText("Let's say : " + lastQuarter);
		roundTime.setBounds(ROUNDED_TIME_X, ROUNDED_TIME_Y, ROUNDED_TIME_W, ROUNDED_TIME_H);

		sizeTitle = frontTitle.getPreferredSize();
		frontTitle.setBounds(FRONT_TITLE_X, FRONT_TITLE_Y, sizeTitle.width, sizeTitle.height);

		sizeIDLabel = IDLabel.getPreferredSize();
		IDLabel.setBounds(ID_LABEL_X, ID_LABEL_Y, sizeIDLabel.width, sizeIDLabel.height);
		IDField.setBounds(ID_FIELD_X, ID_FIELD_Y, ID_FIELD_W, ID_FIELD_H);

		// Add previous element to panel
		frame.add(startButton);
		panel.add(IDLabel);
		panel.add(IDField);
		panel.add(reelTimeClock);
		panel.add(roundTime);
		panel.add(frontTitle);
		frame.setVisible(true);
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();

		if (source == startButton) {
			try {
				Input inputCheck = new Input(settings);
				inputCheck.sendCheck(getIDField());
				IDField.setText("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
