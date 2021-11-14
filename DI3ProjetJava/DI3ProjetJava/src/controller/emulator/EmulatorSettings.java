package controller.emulator;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import controller.emulator.tcp.TCPEmulatorSettings;
import model.emulator.History;
import model.shared.CheckInOut;
import model.shared.EmployeeInfo;

/**
 * @brief Settings Class to manage the emulator
 *
 */
public class EmulatorSettings extends TCPEmulatorSettings {

	private static final long serialVersionUID = -786389681881788698L;

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	private static History currentModel;
	private static ArrayList<EmployeeInfo> listEmployeeInfo;
	private static ArrayList<CheckInOut> waitingChecks;
	private transient EmulatorBackup backupData;
	private LocalDateTime dateTime;
	private String backupFileName;
	private long[] timersForBackup = { 40 * 1000, 2 * 60 * 1000 }; // in milliseconds

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/

	/**
	 * @brief Default constructor
	 */
	public EmulatorSettings() {
		currentModel = new History();
		backupData = new EmulatorBackup();
	}

	/**
	 * @brief One argument constructor
	 */
	public EmulatorSettings(String backupFileName) {
		setWaitingChecks(new ArrayList<CheckInOut>());
		setListEmployeeInfo(new ArrayList<EmployeeInfo>());
		setBackupFileName(backupFileName);
		setBackupData(new EmulatorBackup());

		// Scanner input = new Scanner(System.in);
		do {
			try {
				setCurrentModel((History) getBackupData().restoreData(getBackupFileName()));
			} catch (FileNotFoundException e) {
				try {
					// handleInvalidFileName(getBackupFileName(), input);
					File newFile = new File(getBackupFileName());
					newFile.createNewFile();
				} catch (IOException e1) {
					System.out.println("Backup file for Emulator is being creating");
				}
			} catch (EOFException e) {
				try {
					setCurrentModel(new History());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (getCurrentModel() == null);
		/*
		 * if (input != null) input.close();
		 */

		Timer timer = new Timer();
		timer.schedule(new PeriodicSave(this), timersForBackup[0], timersForBackup[1]);
	}

	/**
	 * @brief Two arguments constructor
	 * @param mainappSettingsSaved
	 */
	@SuppressWarnings("unchecked")
	public EmulatorSettings(EmulatorSettings mainappSettingsSaved, EmulatorBackup mainappRestorationProcess) {
		setWaitingChecks(new ArrayList<CheckInOut>());
		setListEmployeeInfo(new ArrayList<EmployeeInfo>());
		mainappSettingsSaved.copiesIn(this);
		setBackupData(mainappRestorationProcess);

		Scanner input = new Scanner(System.in);
		do {
			try {
				setListEmployeeInfo((ArrayList<EmployeeInfo>) getBackupData().restoreData(getBackupFileName(), 0));
				setWaitingChecks((ArrayList<CheckInOut>) getBackupData().restoreData(getBackupFileName(), 0));
				setCurrentModel((History) getBackupData().restoreData(getBackupFileName(), -1));
			} catch (FileNotFoundException e) {
				try {
					handleInvalidFileName(getBackupFileName(), input);
				} catch (IOException | ClassNotFoundException e1) {
					System.out.println(e1.getMessage());
				}
			} catch (EOFException e) {
				try {
					setCurrentModel(new History());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (getCurrentModel() == null);
		if (input != null)
			input.close();

		Timer timer = new Timer();
		timer.schedule(new PeriodicSave(this), timersForBackup[0], timersForBackup[1]);
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/************************** BackupFileName ***************************/
	/**
	 * @return the fileName
	 */
	public String getBackupFileName() {
		return backupFileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setBackupFileName(String backupFileName) {
		if (!backupFileName.substring(backupFileName.length() - 4, backupFileName.length()).equals(".ser"))
			throw new IllegalArgumentException("The backup file name must end by .ser");

		this.backupFileName = backupFileName;
	}

	/*************************** currentModel ****************************/
	/**
	 * @return the currentModel
	 */
	public static History getCurrentModel() {
		return currentModel;
	}

	/**
	 * @param currentModel the currentModel to set
	 */
	public static void setCurrentModel(History currentModel) {
		EmulatorSettings.currentModel = currentModel;
	}

	/**************************** backupData *****************************/
	/**
	 * @return the backupData
	 */
	public EmulatorBackup getBackupData() {
		return backupData;
	}

	/**
	 * @param backupData the backupData to set
	 */
	public void setBackupData(EmulatorBackup backupData) {
		this.backupData = backupData;
	}

	/************************** listEmployeeID ***************************/
	/**
	 * @return the listEmployeeID
	 */
	public static ArrayList<EmployeeInfo> getListEmployeeInfo() {
		return listEmployeeInfo;
	}

	/**
	 * @param listEmployeeID the listEmployeeID to set
	 */
	public static void setListEmployeeInfo(ArrayList<EmployeeInfo> listEmployeeID) {
		EmulatorSettings.listEmployeeInfo = listEmployeeID;
	}

	/*************************** waitingChecks ***************************/
	/**
	 * @return the waitingChecks
	 */
	public static ArrayList<CheckInOut> getWaitingChecks() {
		return waitingChecks;
	}

	/**
	 * @param waitingChecks the waitingChecks to set
	 */
	public static void setWaitingChecks(ArrayList<CheckInOut> waitingChecks) {
		EmulatorSettings.waitingChecks = waitingChecks;
	}

	/***************************** dateTime ******************************/
	/**
	 * @return the dateTime
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	/************************** timersForBackup **************************/
	/**
	 * @return the timersForBackup
	 */
	public long[] getTimersForBackup() {
		return timersForBackup;
	}

	/**
	 * @param timersForBackup the timersForBackup to set
	 */
	public void setTimersForBackup(long[] timersForBackup) {
		this.timersForBackup = timersForBackup;
	}

	/*********************************************************************/
	/**************************** INTERN CLASS ***************************/
	/*********************************************************************/

	private class PeriodicSave extends TimerTask implements Serializable {
		private static final long serialVersionUID = 4275212943329005505L;

		private EmulatorSettings settingsData;

		public PeriodicSave(EmulatorSettings settingsData) {
			this.settingsData = settingsData;
		}

		@Override
		public void run() {
			if (getCurrentModel() != null) {
				LocalDateTime nowTime = LocalDateTime.now();
				File file = new File(getBackupFileName());

				if (file.length() > 0) {
					// create the new backup file name
					String newBackupFileName = getBackupFileName();

					int evidenceRecentBackup = newBackupFileName.indexOf("-");
					if (evidenceRecentBackup >= 0)
						newBackupFileName = newBackupFileName.substring(0, evidenceRecentBackup);
					else
						newBackupFileName = newBackupFileName.substring(0, newBackupFileName.length() - 4);

					newBackupFileName += nowTime.format(DateTimeFormatter.ofPattern("-HH.mm-yyyy.MM.dd")) + ".ser";
					setBackupFileName(newBackupFileName);

					// create the new backup file
					try {
						File newFile = new File(getBackupFileName());
						newFile.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				try {
					getBackupData().saveData(getBackupFileName(), settingsData, 1);
					getBackupData().saveData(getBackupFileName(), new ArrayList<EmployeeInfo>(getListEmployeeInfo()),
							0);
					getBackupData().saveData(getBackupFileName(), new ArrayList<CheckInOut>(getWaitingChecks()), 0);
					getBackupData().saveData(getBackupFileName(), getCurrentModel(), -1);
					System.out.println("(Backup of emulator made on " + nowTime.format(DateTimeFormatter.ISO_LOCAL_DATE)
							+ " at " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	/**
	 * @brief Make a save manually
	 */
	public void save() {
		PeriodicSave newSave = new PeriodicSave(this);
		newSave.run();
	}

	/**
	 * @brief Method to verify file name
	 * @param fileName
	 * @param input
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void handleInvalidFileName(String fileName, Scanner input) throws IOException, ClassNotFoundException {
		System.out.print("This file does not exist, are you sure you want to use " + fileName + " (y/n) ? ");
		String answer = input.next();
		if (answer.equals("y")) {
			File newFile = new File(fileName);
			newFile.createNewFile();
		} else if (answer.equals("n")) {
			System.out.print("Enter the name of the file you want : ");
			setBackupFileName(input.next());
		}
	}

	/**
	 * @brief Method to manage serialized files
	 * @param fileName
	 * @return
	 */
	public static String lastModifiedFileRelatedTo(String fileName) {
		if (new File(fileName).exists()) {
			File directory = new File(fileName).getParentFile();
			File[] listFiles = directory.listFiles();
			if (listFiles.length > 0) {
				for (Integer iterator1 = 0; iterator1 < listFiles.length; iterator1++) {
					// eliminate the files that does not end by ".ser"
					String currentFileName = listFiles[iterator1].getName();
					if (!currentFileName.substring(currentFileName.length() - 4, currentFileName.length())
							.equals(".ser")) {
						for (Integer iteratorDel = iterator1 + 1; iteratorDel < listFiles.length; iteratorDel++)
							listFiles[iteratorDel - 1] = listFiles[iteratorDel];
					}

					// sort by last modified
					for (Integer iterator2 = iterator1; iterator2 < listFiles.length; iterator2++) {
						if (listFiles[iterator1].lastModified() < listFiles[iterator2].lastModified()) {
							File fileTemp = listFiles[iterator1];
							listFiles[iterator1] = listFiles[iterator2];
							listFiles[iterator2] = fileTemp;
						}
					}
				}
				return directory.getName() + "/" + listFiles[0].getName();
			}
		}
		return fileName;
	}

	/**
	 * @brief Method to copy copy element into file
	 * @param receiving
	 */
	public void copiesIn(EmulatorSettings receiving) {
		((TCPEmulatorSettings) this).copiesIn(receiving);
		receiving.setBackupFileName(this.getBackupFileName());
		receiving.setTimersForBackup(this.getTimersForBackup());
	}
}
