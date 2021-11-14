package controller.mainapp.tcp;

import java.net.*;
import java.util.ArrayList;

import model.shared.EmployeeInfo;

import java.io.*;

/**
 * 
 * @brief Class used to build a TCPClientMainApp.
 *
 */
public class TCPClientMainAppBuilder {

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/
	
	protected InetAddress IPaddress;
	protected int numPort;
	protected Socket s;
	protected InetSocketAddress isA;
	protected ArrayList<EmployeeInfo> listEmployees;
	protected transient InputStream sIn;
	protected transient ObjectInputStream ois;
	protected transient OutputStream sOut;
	protected transient ObjectOutputStream oos;

	/*********************************************************************/
	/****************************** BUILDERS *****************************/
	/*********************************************************************/
	
	/**
	 * @brief Constructor.
	 * @param listEmployees
	 * @param IPaddress
	 * @param numPort
	 */
	TCPClientMainAppBuilder(ArrayList<EmployeeInfo> listEmployees, InetAddress IPaddress, int numPort) {
		this.IPaddress = IPaddress;
		this.numPort = numPort;
		this.listEmployees = listEmployees;
		s = null;
		isA = null;
		sOut = null;
		sIn = null;
		ois = null;
		oos = null;
	}

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/
	
	/**
	 * @brief Method used to set the connection.
	 * @throws IOException
	 */
	protected void setSocket() throws IOException {
		isA = new InetSocketAddress(IPaddress, numPort);
		s = new Socket(isA.getHostName(), isA.getPort());

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
	 * @return the listEmployees
	 */
	public ArrayList<EmployeeInfo> getListEmployees() {
		return listEmployees;
	}

	/**
	 * @param listEmployees the listEmployees to set
	 */
	public void setListEmployees(ArrayList<EmployeeInfo> listEmployees) {
		this.listEmployees = listEmployees;
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