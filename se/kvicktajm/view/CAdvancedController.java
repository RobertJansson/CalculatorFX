package se.kvicktajm.view;

import javafx.fxml.FXML;
import se.kvicktajm.CalculatorFX;

public class CAdvancedController
{
	private CalculatorFX mainApp;
	private CNormalController display;

	/**
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
	public void setMainApp(CalculatorFX mainApp, CNormalController display) {
		this.mainApp = mainApp;
		this.display = display;
	}
	
	// JavaFX "action listeners"
	@FXML private void sin() { mainApp.pushConstant("sin("); }
	@FXML private void cos() { mainApp.pushConstant("cos("); }
	@FXML private void tan() { mainApp.pushConstant("tan("); }
	@FXML private void sinh() { mainApp.pushConstant("1/sin("); }
	@FXML private void cosh() { mainApp.pushConstant("1/cos("); }
	@FXML private void tanh() { mainApp.pushConstant("1/tan("); }
	@FXML private void invert() { mainApp.pushConstant("1/("); }
	@FXML private void x2() { mainApp.pushConstant("sq"); }
	@FXML private void x3() { mainApp.pushConstant("cb"); }
	@FXML private void root2() { mainApp.pushConstant("sqrt("); }
	@FXML private void root3() { mainApp.pushConstant("cbrt("); }
//	@FXML private void rootx() { mainApp.pushConstant(""); }
	@FXML private void rand() { mainApp.pushConstant("Rand("); }
	@FXML private void ex() { mainApp.pushConstant("exp("); }
	@FXML private void e() { mainApp.pushConstant("e"); }
	@FXML private void ln() { mainApp.pushConstant("ln("); }
	@FXML private void log() { mainApp.pushConstant("log("); }
	@FXML private void lP() { mainApp.pushConstant("("); }
	@FXML private void rP() { mainApp.pushConstant(")"); }
	@FXML private void pi() { mainApp.pushConstant("PI"); }
	@FXML private void EE() { mainApp.pushConstant("EE("); }
	@FXML private void abs() {
		if (display.getDisplay().substring(0,1).equals("-")){
				mainApp.evaluate(display.getDisplay().substring(1));
				System.out.println(display.getDisplay().substring(1));
		}
	}
}
