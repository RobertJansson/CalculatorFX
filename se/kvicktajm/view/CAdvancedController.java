package se.kvicktajm.view;

import javafx.fxml.FXML;
import se.kvicktajm.CalculatorFX;

public class CAdvancedController
{
	// Reference to the main application.
	@SuppressWarnings("unused")
	private CalculatorFX mainApp;

	/**
	* The constructor.
	* The constructor is called before the initialize() method.
	*/
	public CAdvancedController() { }

	/**
	* Initializes the controller class. This method is automatically called
	* after the fxml file has been loaded.
	*/
	@FXML
	private void initialize() {	}

	/**
	* Called by the main application to give a reference back to itself.
	* @param mainApp
	*/
	public void setMainApp(CalculatorFX mainApp) {
		this.mainApp = mainApp;
	}
}
