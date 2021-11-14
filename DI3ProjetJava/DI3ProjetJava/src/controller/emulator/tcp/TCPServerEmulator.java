package controller.emulator.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.emulator.Emulator;
import controller.emulator.EmulatorSettings;
import model.shared.CheckInOut;
import model.shared.EmployeeInfo;

/**
 * 
 * @brief Class which represents a server tcp from the emulator. Extends
 *        TCPServerEmulatorBuilder.
 * @implNote Runnable.
 *
 */
public class TCPServerEmulator extends TCPServerEmulatorBuilder implements Runnable {

	public TCPServerEmulator(EmulatorSettings emulator, InetAddress IPaddress, int numPort) {
		super(emulator, IPaddress, numPort);
	}

	/**
	 * @brief Method which adds the received data in the database of the main
	 *        application.
	 * @param readChecks
	 */
	public void run() {
		try {
			// System.out.println("TCPServerEmulator launched ...");
			setSocket();
			while (true) {
				s = ss.accept();
				// System.out.println("Hello, the server Emulator accepts");
				sIn = s.getInputStream();
				ois = new ObjectInputStream(sIn);
				@SuppressWarnings("unchecked")
				ArrayList<EmployeeInfo> listEmployees = (ArrayList<EmployeeInfo>) ois.readObject();
				if (listEmployees != null) {
					Emulator.setListEmployeeInfo(listEmployees);
					for (EmployeeInfo currentEmployeeInfo : listEmployees) {
						if (Emulator.getCurrentModel().getChecksPerEmployee().get(currentEmployeeInfo) == null)
							Emulator.getCurrentModel().getChecksPerEmployee().put(currentEmployeeInfo,
									new CopyOnWriteArrayList<CheckInOut>());
					}
					System.out.println("Emulator received the list of employee's infos");
					// System.out.println("Server emulator received : " + listEmployees.toString());
					// System.out.println(Emulator.getListEmployeeInfo());
				}
				sOut = s.getOutputStream();
				oos = new ObjectOutputStream(sOut);
				oos.writeBoolean(true);
				oos.flush();
				ois.close();
				oos.close();
				s.close();
			}

		} catch (IOException e) {
			System.out.println("IOException TCPServerMainApp : " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException TCPServerMainApp : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception TCPServerMainApp : " + e.getMessage());
		}
	}
}
