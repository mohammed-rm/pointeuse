package controller.shared;

import java.io.IOException;
import java.net.Socket;

/**
 * @brief Class for communication using sockets
 *
 */
public class ExchangeViaSocket extends SerializationProcess {

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	/******************************* send ********************************/

	/**
	 * @param thisMachine
	 * @param dataToSave
	 * @throws IOException
	 */
	public void send(Socket thisMachine, Object dataToSave) throws IOException {
		initialize(thisMachine.getOutputStream());
		insert(dataToSave);
	}

	/**
	 * @param thisMachine
	 * @param dataToSave
	 * @param streamStatus
	 * @throws IOException
	 */
	public void send(Socket thisMachine, Object dataToSave, int streamStatus) throws IOException {
		if (streamStatus > 0)
			initialize(thisMachine.getOutputStream());
		else
			initialize(getsOut());
		insert(dataToSave);
	}

	/****************************** receive ******************************/

	/**
	 * @param remoteMachine
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object receive(Socket remoteMachine) throws ClassNotFoundException, IOException {
		initialize(remoteMachine.getInputStream());
		return extract();
	}

	/**
	 * @param remoteMachine
	 * @param streamStatus
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object receive(Socket remoteMachine, int streamStatus) throws ClassNotFoundException, IOException {
		if (streamStatus > 0)
			initialize(remoteMachine.getInputStream());
		else
			initialize(getsIn());
		return extract();
	}
}
