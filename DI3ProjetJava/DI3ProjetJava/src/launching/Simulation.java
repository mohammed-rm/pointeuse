package launching;

import controller.emulator.Emulator;
import controller.mainapp.Mainapp;

/**
 * @brief Simulation class
 *
 */
public abstract class Simulation {

	public static void main(String[] args) {
		String[] fileNameMainapp = { "backupMainapp/serializedData.ser" };
		String[] fileNameEmulator = { "backupEmulator/serializedData.ser" };

		// in this simulation, emulator must be closed first by the user so its history can saved
		Emulator.main(fileNameEmulator);
		Mainapp.main(fileNameMainapp);
	}

}
