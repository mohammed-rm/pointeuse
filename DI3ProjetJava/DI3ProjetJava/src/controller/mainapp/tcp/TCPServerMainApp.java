package controller.mainapp.tcp;

import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.mainapp.Mainapp;
import model.shared.CheckInOut;
import model.mainapp.*;

/**
 * 
 * @brief Class which represents a server tcp from the main application. Extends
 *        TCPServerMainAppBuilder.
 * @implNote Runnable.
 *
 */
public class TCPServerMainApp extends TCPServerMainAppBuilder implements Runnable {

	public TCPServerMainApp(InetAddress IPaddress, int numPort) {
		super(IPaddress, numPort);
	}

	/**
	 * @brief Method which add the received data in the database of the main
	 *        application.
	 * @param readChecks
	 */
	public static void addChecksToMainApp(ArrayList<CheckInOut> readChecks) {
		if (readChecks != null) {

			for (CheckInOut currentReadCheck : readChecks) {
				Employee employee = SearchInMainapp.searchEmployee(Mainapp.getCurrentModel(),
						currentReadCheck.getEmployeeID());
				if (employee != null) {
					try {
						Mainapp.getCurrentModel().getDepartment(employee.getDepartment()).getListEmployees()
								.get(employee.getID()).getListChecks().add(currentReadCheck);
					} catch (Exception e) {
						System.out.println("Exception TCPServerMainApp : " + e.getMessage());
					}
					currentReadCheck.setStatus(true);
					System.out.println("Mainapp saved : " + currentReadCheck.toString());
				}
			}

		} else {
			System.out.println("No checks to save");
		}
	}

	/**
	 * @apiNote The server always run, until there is an exception.
	 */
	public void run() {
		try {
			// System.out.println("TCPServerMainApp launched ...");
			setSocket();
			while (true) {
				s = ss.accept();
				// System.out.println("Hello, the server mainapp accepts");
				sIn = s.getInputStream();
				ois = new ObjectInputStream(sIn);
				@SuppressWarnings("unchecked")
				ArrayList<CheckInOut> readChecks = new ArrayList<CheckInOut>(
						(CopyOnWriteArrayList<CheckInOut>) ois.readObject());
				// System.out.println("Server mainapp received : " + readChecks.toString());
				/*
				 * if (!readChecks.isEmpty())
				 * System.out.println("Server mainapp received checks");
				 */
				addChecksToMainApp(readChecks);
				sOut = s.getOutputStream();
				oos = new ObjectOutputStream(sOut);
				/*
				 * while data have been received the server send a response to the client
				 */
				oos.writeBoolean(true);
				oos.flush();
				ois.close();
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