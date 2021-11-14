package controller.mainapp.tcp;

import java.net.*;

import java.io.*;

/**
 * 
 * @brief Class used to build a TCPServerMainApp.
 *
 */
public class TCPServerMainAppBuilder {

	protected InetAddress IPaddress;
	protected int numPort;
	protected ServerSocket ss;
	protected Socket s; // the passive and active sockets
	protected InetSocketAddress isA; // the address
	protected transient InputStream sIn;
	protected transient ObjectInputStream ois;
	protected transient OutputStream sOut;
	protected transient ObjectOutputStream oos;

	/**
	 * @brief Constructor.
	 * @param IPaddress
	 * @param numPort
	 */
	TCPServerMainAppBuilder(InetAddress IPaddress, int numPort) {
		this.IPaddress = IPaddress;
		this.numPort = numPort;
		ss = null;
		s = null;
		isA = null;
		sIn = null;
		ois = null;
	}

	/**
	 * @brief Method used to set the connection.
	 * @throws IOException
	 */
	public void setSocket() throws IOException {

		isA = new InetSocketAddress(IPaddress, numPort);
		ss = new ServerSocket(isA.getPort());

	}

	/**
	 * @return the iPaddress
	 */
	public InetAddress getIPaddress() {
		return IPaddress;
	}

	/**
	 * @param iPaddress the iPaddress to set
	 */
	public void setIPaddress(InetAddress iPaddress) {
		IPaddress = iPaddress;
	}

	/**
	 * @return the numPort
	 */
	public int getNumPort() {
		return numPort;
	}

	/**
	 * @param numPort the numPort to set
	 */
	public void setNumPort(int numPort) {
		this.numPort = numPort;
	}

	/**
	 * @return the ss
	 */
	public ServerSocket getSs() {
		return ss;
	}

	/**
	 * @param ss the ss to set
	 */
	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}

	/**
	 * @return the s
	 */
	public Socket getS() {
		return s;
	}

	/**
	 * @param s the s to set
	 */
	public void setS(Socket s) {
		this.s = s;
	}

	/**
	 * @return the isA
	 */
	public InetSocketAddress getIsA() {
		return isA;
	}

	/**
	 * @param isA the isA to set
	 */
	public void setIsA(InetSocketAddress isA) {
		this.isA = isA;
	}

	/**
	 * @return the sIn
	 */
	public InputStream getsIn() {
		return sIn;
	}

	/**
	 * @param sIn the sIn to set
	 */
	public void setsIn(InputStream sIn) {
		this.sIn = sIn;
	}

	/**
	 * @return the ois
	 */
	public ObjectInputStream getOis() {
		return ois;
	}

	/**
	 * @param ois the ois to set
	 */
	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}

	/**
	 * @return the sOut
	 */
	public OutputStream getsOut() {
		return sOut;
	}

	/**
	 * @param sOut the sOut to set
	 */
	public void setsOut(OutputStream sOut) {
		this.sOut = sOut;
	}

	/**
	 * @return the oos
	 */
	public ObjectOutputStream getOos() {
		return oos;
	}

	/**
	 * @param oos the oos to set
	 */
	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}
	
	

}