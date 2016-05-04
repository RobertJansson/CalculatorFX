package se.kvicktajm.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import se.kvicktajm.CalculatorFX;

public class CNormalController
{
	// Display of the calculator
	@FXML
	private Label display;

	// Reference to the main application.
	private CalculatorFX mainApp;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public CNormalController() {
		display = new Label("");
	}

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

		// Initialize a start value of display.
		// Last session from a plist (or XML as this is Java) would be a nice feature (Controller prepared)
		updateDisplay("0");
	}
	
	private void updateDisplay(String str){
		if (str.equals("")){
			display.setText("");
		} else {
			display.setText(str);
		}
	}
	
	private void button(String s) {
		if (display.getText().equals("0"))
			display.setText(s);
		else
			display.setText(display.getText() + s);
	}
	
	private void add(String s){
		display.setText(display.getText() + s);
	}
	
	/**
	 * Called when the user clicks button 1.
	 */
	@FXML private void b0() { button("0"); }
	@FXML private void b1() { button("1"); }
	@FXML private void b2() { button("2"); }
	@FXML private void b3() { button("3"); }
	@FXML private void b4() { button("4"); }
	@FXML private void b5() { button("5"); }
	@FXML private void b6() { button("6"); }
	@FXML private void b7() { button("7"); }
	@FXML private void b8() { button("8"); }
	@FXML private void b9() { button("9"); }
	@FXML private void divide()		{ add("/"); }
	@FXML private void multiply()	{ add("*"); }
	@FXML private void subtract()	{ add("-"); }
	@FXML private void add()		{ add("+"); }
	@FXML private void comma() 		{ add("."); }
	@FXML private void clear()		{ display.setText("0"); }
	@FXML private void plusMinus()	{ display.setText("-" + display.getText()); }
	@FXML private void percent()	{ compute (display.getText() + "*100");}
	@FXML private void compute()	{ compute (display.getText()); }

	private void compute(String toEvaluate) {
		Double result = mainApp.evaluate(toEvaluate);
		if (Double.isFinite(result))
			if (result.toString().equals("0.0"))
				display.setText("0");
			else
				display.setText(result.toString());
	}
}
