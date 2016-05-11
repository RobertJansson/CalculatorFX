package se.kvicktajm.view;

import javafx.fxml.FXML;
//import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextArea;

public class CTapeController
{
	// Simple debug flag:
	private static final boolean LOG = false;

	// Display of the calculator
	@FXML
	private TextArea tape;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public CTapeController() {
	}

	/**
	 * Private method for simple debugging
	 */
	private void log(String s) {if (LOG) System.out.println(s); }

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {	}

	/**
	 * Called when the tape should be updated
	 */
	public void updateTape(String str){
		log("Update tape: " + str);
		this.tape.appendText(str);
//		this.tape.setTextFormatter(new TextFormatter());
	}
}
