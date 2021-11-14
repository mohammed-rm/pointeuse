package controller.emulator.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.emulator.EmulatorSettings;
import model.shared.CheckInOut;

/**
 * 
 * @brief Class used to build a TCPClientEmulator.
 *
 */
public class TCPClientEmulatorBuilder {

	protected InetAddress IPaddress;
	protected int numPort;
	protected Socket s;
	protected InetSocketAddress isA;
	protected CopyOnWriteArrayList<CheckInOut> checkInOut;
	protected transient InputStream sIn;
	protected transient ObjectInputStream ois;
	protected transient OutputStream sOut;
	protected transient ObjectOutputStream oos;
	protected transient boolean isWaitingCheckList;

	/**
	 * @brief Constructor used when there is just one checkInOut to send.
	 * @param checkInOutToSend
	 * @param IPaddress
	 * @param numPort
	 */
	TCPClientEmulatorBuilder(CheckInOut checkInOutToSend, InetAddress IPaddress, int numPort) {
		this.IPaddress = IPaddress;
		this.numPort = numPort;
		s = null;
		isA = null;
		sOut = null;
		oos = null;
		isWaitingCheckList = false;
		checkInOut = new CopyOnWriteArrayList<CheckInOut>();
		checkInOut.add(checkInOutToSend);
		checkInOutToSend.setStatus(false);
	}

	/**
	 * @brief Constructor used when there the emulator start and it have to send a
	 *        list of checkInOuts.
	 * @param emulator
	 * @param checkInOutToSend
	 * @param IPaddress
	 * @param numPort
	 */
	TCPClientEmulatorBuilder(EmulatorSettings emulator, ArrayList<CheckInOut> checkInOutToSend, InetAddress IPaddress,
			int numPort) {
		this.IPaddress = IPaddress;
		this.numPort = numPort;
		s = null;
		isA = null;
		sOut = null;
		oos = null;
		isWaitingCheckList = true;
		checkInOut = new CopyOnWriteArrayList<>(checkInOutToSend);
	}

	/**
	 * @brief Method which set parameters for the connection.
	 * @throws IOException
	 */
	protected void setSocket() throws IOException {
		isA = new InetSocketAddress(IPaddress, numPort);
		s = new Socket(isA.getHostName(), isA.getPort());

	}

}
