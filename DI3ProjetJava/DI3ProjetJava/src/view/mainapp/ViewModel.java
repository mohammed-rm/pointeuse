package view.mainapp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @brief Abstract View used to build all the main view of the main application.
 * @implNote ActionListener.
 *
 */
public abstract class ViewModel implements ActionListener {

	/*********************************************************************/
	/***************************** ATTRIBUTES ****************************/
	/*********************************************************************/

	protected JPanel panel;
	protected Integer arraySize;
	protected ArrayList<JLabel> labelArray;
	protected ArrayList<JTextField> textFieldArray;
	protected JButton submitButton;
	protected HashMap<String, JTextField> submitMap;

	/*********************************************************************/
	/***************************** GETS/SETS *****************************/
	/*********************************************************************/

	/******************************** panel ******************************/

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/****************************** arraySize ****************************/

	/**
	 * @return the arraySize
	 */
	public Integer getArraySize() {
		return arraySize;
	}

	/**
	 * @param arraySize the arraySize to set
	 */
	public void setArraySize(Integer arraySize) {
		this.arraySize = arraySize;
	}

	/****************************** labelArray ***************************/

	/**
	 * @return the labelArray
	 */
	public ArrayList<JLabel> getLabelArray() {
		return labelArray;
	}

	/**
	 * @param labelArray the labelArray to set
	 */
	public void setLabelArray(ArrayList<JLabel> labelArray) {
		this.labelArray = labelArray;
	}

	/**************************** textFieldArray *************************/

	/**
	 * @return the textFieldArray
	 */
	public ArrayList<JTextField> getTextFieldArray() {
		return textFieldArray;
	}

	/**
	 * @param textFieldArray the textFieldArray to set
	 */
	public void setTextFieldArray(ArrayList<JTextField> textFieldArray) {
		this.textFieldArray = textFieldArray;
	}

	/***************************** submitButton **************************/

	/**
	 * @return the submitButton
	 */
	public JButton getSubmitButton() {
		return submitButton;
	}

	/**
	 * @param submitButton the submitButton to set
	 */
	public void setSubmitButton(JButton submitButton) {
		this.submitButton = submitButton;
	}

	/****************************** submitMap ****************************/

	/**
	 * @return the submitMap
	 */
	public HashMap<String, JTextField> getSubmitMap() {
		return submitMap;
	}

	/**
	 * @param submitMap the submitMap to set
	 */
	public void setSubmitMap(HashMap<String, JTextField> submitMap) {
		this.submitMap = submitMap;
	}

	/*********************************************************************/
	/*************************** OTHER METHODS ***************************/
	/*********************************************************************/

	/**
	 * @brief Method used to build panel.
	 */
	protected void buildContentPanel() {

		panel = new JPanel(new GridBagLayout());
		Integer iterator;

		for (iterator = 0; iterator < arraySize; iterator++) {

			// Label
			GridBagConstraints constraintsLabel = new GridBagConstraints();
			constraintsLabel.gridx = 0;
			constraintsLabel.gridy = iterator;
			panel.add(labelArray.get(iterator), constraintsLabel);

			// TextField
			GridBagConstraints constraintsTextField = new GridBagConstraints();
			constraintsTextField.gridx = 1;
			constraintsTextField.gridy = iterator;
			textFieldArray.get(iterator).setColumns(10);
			panel.add(textFieldArray.get(iterator), constraintsTextField);

		}

		// SubmitButton
		submitButton = new JButton("Submit");
		GridBagConstraints constraintsSubmitButton = new GridBagConstraints();
		constraintsSubmitButton.gridx = 2;
		constraintsSubmitButton.gridy = iterator;
		submitButton.addActionListener(this);
		panel.add(submitButton, constraintsSubmitButton);

	}

	/*************************** undefined methods *************************/

	/**
	 * @brief Method used to initialize attributes.
	 */
	protected abstract void initializeAttributes();

	@Override
	public abstract void actionPerformed(ActionEvent event);

}
