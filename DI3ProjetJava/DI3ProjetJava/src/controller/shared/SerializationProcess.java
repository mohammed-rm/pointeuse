package controller.shared;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @brief Class for serialization
 *
 */
public abstract class SerializationProcess {

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	protected transient InputStream sIn;
	protected transient OutputStream sOut;
	protected transient ObjectInputStream ois;
	protected transient ObjectOutputStream oos;

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

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

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	/**************************** initialize *****************************/

	/**
	 * @param sIn
	 * @throws IOException
	 */
	public void initialize(InputStream sIn) throws IOException {
		setsIn(sIn);
		setOis(new ObjectInputStream(sIn));
	}

	/**
	 * @param sOut
	 * @throws IOException
	 */
	public void initialize(OutputStream sOut) throws IOException {
		setsOut(sOut);
		setOos(new ObjectOutputStream(sOut));
	}

	/****************************** insert *******************************/

	/**
	 * @param objectToInsert
	 * @throws IOException
	 */
	public void insert(Object objectToInsert) throws IOException {
		if (getsOut() == null || getOos() == null) {
			throw new IllegalArgumentException(
					"SerializationProcess have not been initialized to extract data from a stream");
		}

		getOos().writeObject(objectToInsert); // serialize Object
		getOos().flush();
		getOos().close();
	}

	/**
	 * @param objectToInsert
	 * @throws IOException
	 */
	public void insert(Object objectToInsert, int streamStatus) throws IOException {
		if (getsOut() == null || getOos() == null) {
			throw new IllegalArgumentException(
					"SerializationProcess have not been initialized to extract data from a stream");
		}

		getOos().writeObject(objectToInsert); // serialize Object
		getOos().flush();
		if (streamStatus < 0) {
			getOos().close();
		}
	}

	/****************************** extract ******************************/

	/**
	 * @return deserializedObject
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object extract() throws IOException, ClassNotFoundException {
		if (getsIn() == null || getOis() == null) {
			throw new IllegalArgumentException(
					"SerializationProcess have not been initialized to extract data from a stream");
		}

		Object deserializedObject = (Object) getOis().readObject();
		getOis().close();
		return deserializedObject;
	}

	/**
	 * @return deserializedObject
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object extract(int streamStatus) throws IOException, ClassNotFoundException {
		if (getsIn() == null || getOis() == null) {
			throw new IllegalArgumentException(
					"SerializationProcess have not been initialized to extract data from a stream");
		}

		Object deserializedObject = (Object) getOis().readObject();
		if (streamStatus < 0)
			getOis().close();
		return deserializedObject;
	}
}
