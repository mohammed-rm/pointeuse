package controller.mainapp;

import java.io.IOException;

import controller.mainapp.tcp.TCPClientMainApp;
import controller.mainapp.tcp.TCPServerMainApp;
import view.mainapp.ViewMainapp;

/**
 * @brief Main class to launch the main application
 *
 */
public class Mainapp extends MainappSettings {

	private static final long serialVersionUID = -6925599295800017000L;

	/****************************** BUILDERS *****************************/

	public Mainapp(MainappSettings mainappSettingsSaved, MainappBackup mainappRestorationProcess) {
		super(mainappSettingsSaved, mainappRestorationProcess);
	}

	public Mainapp(String backupFileName) {
		super(backupFileName);
	}

	/**************************** MAIN METHOD ****************************/

	public static void main(String[] args) {
		String target;
		if (args.length == 1)
			target = args[0];
		else
			target = "backupMainapp/serializedData.ser";
		
		MainappBackup restorationProcess = new MainappBackup();
		MainappSettings mainappSaved = null;
		Mainapp current = null;

		/**
		 * To comment until first "else" to make the build of a simulation data possible
		 */
		try {
			mainappSaved = (MainappSettings) restorationProcess.restore(lastModifiedFileRelatedTo(target), 1);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassCastException e) {
			System.out.println("Information : backup did not contain settings data.");
		}

		if (mainappSaved != null)
			current = new Mainapp(mainappSaved, restorationProcess);
		else
			current = new Mainapp(lastModifiedFileRelatedTo(target));

		/**
		 * Can be uncommented to build a simulation data
		 */
		/*
		Company companyToSave = null;
		try {
			companyToSave = new Company();
			Department A = new Department("PolyGame");
			Department B = new Department("JavaTech", new Employee("default", "RH"));
			companyToSave.addDepartment(A);
			companyToSave.addDepartment(B);

			// add few checks to A
			CheckInOut exempleCheck1 = new CheckInOut();
			exempleCheck1.setEmployeeID(1);
			SearchInMainapp.searchEmployee(A, 1).getListChecks().add(exempleCheck1);
			CheckInOut exempleCheck2 = new CheckInOut();
			SearchInMainapp.searchEmployee(A, 1).getListChecks().add(exempleCheck2);
			exempleCheck2.setEmployeeID(1);
			CheckInOut exempleCheck3 = new CheckInOut();
			SearchInMainapp.searchEmployee(A, 1).getListChecks().add(exempleCheck3);
			exempleCheck3.setEmployeeID(1);

			// add few employees to B B.addEmployee(new Employee("test", "default"));
			B.addEmployee(new Employee("default3", "test"));

			SearchInMainapp.searchEmployee(B, "default", 0).get(0).setFirstname("Theo");
			SearchInMainapp.searchEmployee(B, "Theo", "RH").get(0).setLastname("Boisseau");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		setCurrentModel(companyToSave);
		*/
		
		new Thread(new TCPServerMainApp(current.getIPaddressServer(), current.getNumPortServer())).start();

		new ViewMainapp(current);

		try {
			new Thread(new TCPClientMainApp(current.getEmployeeInfo(), current.getIPaddressClient(),
					current.getNumPortClient())).start();
		} catch (Exception e) {
			System.out.println("Exception in Mainapp main : " + e.getMessage());
		}
	}
}
