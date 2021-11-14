package controller.emulator.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import controller.emulator.Emulator;
import controller.emulator.EmulatorSettings;
import model.emulator.SearchInEmulator;
import model.shared.CheckInOut;

/**
 * 
 * @brief Class which represents a client tcp from the emulator. Extends
 *        TCPClientEmulatorBuilder.
 * @implNote Runnable.
 *
 */
public class TCPClientEmulator extends TCPClientEmulatorBuilder implements Runnable {

	public TCPClientEmulator(CheckInOut checkInOutToSend, InetAddress IPaddress, int numPort) {
		super(checkInOutToSend, IPaddress, numPort);
	}

	public TCPClientEmulator(EmulatorSettings emulator, ArrayList<CheckInOut> checkInOutToSend, InetAddress IPaddress,
			int numPort) {
		super(emulator, checkInOutToSend, IPaddress, numPort);
	}

	/**
	 * @apiNote When the emulator start it try to send the list of checkInOuts until
	 *          success. When it is just one check to send the emulator try to send
	 *          just once.
	 */
	public void run() {
		boolean dataSent = false;
		do {
			try {
				// System.out.println("TCPClientEmulator launched ...");
				setSocket();
				// System.out.println("Hello, the client emulator is connected");
				System.out.println("Emulator -> Mainapp");
				sOut = s.getOutputStream();
				oos = new ObjectOutputStream(sOut);
				oos.writeObject(checkInOut);
				oos.flush();
				sIn = s.getInputStream();
				ois = new ObjectInputStream(sIn);
				// while data have been sent the client wait for a response from the server
				if (ois.readBoolean()) {
					dataSent = true;
				}
				oos.close();
				ois.close();
				s.close();
			} catch (IOException e) {
				System.out.println("IOException TCPClientEmulator : " + e.getMessage());
				if (this.s != null && s.isConnected()) {
					try {
						s.close();
					} catch (IOException e1) {
						System.out.println("IOException TCPClientEmulator : " + e1.getMessage());
					}
				}
				try {
					Thread.sleep(2 * 1000);
				} catch (InterruptedException e1) {
					System.out.println("InterruptedException TCPClientEmulator : " + e1.getMessage());
				}
			}
		} while (isWaitingCheckList && !dataSent);
		for (CheckInOut sentCheck : checkInOut) {
			checkInOut.remove(sentCheck);
			EmulatorSettings.getWaitingChecks().remove(sentCheck);
			sentCheck.setStatus(true);
			Emulator.getCurrentModel().addToHistory(sentCheck,
					SearchInEmulator.searchEmployee(Emulator.getCurrentModel(), sentCheck.getEmployeeID()));
		}
	}

}
