package controller.emulator.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import controller.emulator.EmulatorSettings;

/**
 * 
 * @brief Class used to build a TCPServerEmulator.
 *
 */
public class TCPServerEmulatorBuilder {

	protected InetAddress IPaddress;
	protected int numPort;
	protected ServerSocket ss;
	protected Socket s; // the passive and active sockets
	protected InetSocketAddress isA; // the address
	protected EmulatorSettings emulator;
	protected transient InputStream sIn;
	protected transient ObjectInputStream ois;
	protected transient OutputStream sOut;
	protected transient ObjectOutputStream oos;

	/**
	 * @brief Constructor.
	 * @param emulator
	 * @param IPaddress
	 * @param numPort
	 */
	TCPServerEmulatorBuilder(EmulatorSettings emulator, InetAddress IPaddress, int numPort) {
		this.IPaddress = IPaddress;
		this.numPort = numPort;
		this.emulator = emulator;
		ss = null;
		s = null;
		isA = null;
		sIn = null;
		sOut = null;
		ois = null;
		oos = null;
	}

	/**
	 * @brief Method used to set the connection.
	 * @throws IOException
	 */
	public void setSocket() throws IOException {

		isA = new InetSocketAddress(IPaddress, numPort);
		ss = new ServerSocket(isA.getPort());
	}

}
