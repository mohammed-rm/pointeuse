package controller.mainapp.tcp;

import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;

import model.shared.EmployeeInfo;

/**
 * 
 * @brief Class which represents a client tcp from the main application. Extends
 *        TCPClientMainAppBuilder.
 * @implNote Runnable.
 *
 */
public class TCPClientMainApp extends TCPClientMainAppBuilder implements Runnable {

	/**
	 * @brief Constructor
	 * @param listEmployees
	 * @param IPaddress
	 * @param numPort
	 */
	public TCPClientMainApp(ArrayList<EmployeeInfo> listEmployees, InetAddress IPaddress, int numPort) {
		super(listEmployees, IPaddress, numPort);
	}

	/**
	 * @brief Send method
	 * @apiNote Try to send data until success.
	 */
	public void run() {
		boolean dataSent = false;
		while (!dataSent) {
			try {
				// System.out.println("The client mainApp launched...");
				setSocket();
				// System.out.println("Hello, the client mainApp is connected");
				System.out.println("Emulator <- Mainapp");
				sOut = s.getOutputStream();
				oos = new ObjectOutputStream(sOut);
				oos.writeObject(listEmployees);
				oos.flush();
				sIn = s.getInputStream();
				ois = new ObjectInputStream(sIn);
				/*
				 * while data have been sent the client wait for a response from the server
				 */
				if (ois.readBoolean()) {
					dataSent = true;
				}
				oos.close();
				ois.close();
				s.close();
			} catch (IOException e) {
				System.out.println("IOException TCPClientMainApp : " + e.getMessage());
				if (this.s != null && s.isConnected()) {
					try {
						s.close();
					} catch (IOException e1) {
						System.out.println("IOException TCPClientMainApp : " + e1.getMessage());
					}
				}
				try {
					Thread.sleep(2 * 1000);
				} catch (InterruptedException e1) {
					System.out.println("InterruptedException TCPClientMainApp : " + e1.getMessage());
				}
			}
		}
	}

}
