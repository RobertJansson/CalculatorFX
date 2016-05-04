package se.kvicktajm.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import se.kvicktajm.CalculatorFX;

public class CNormalController
{
	// Simple debug flag:
	private static final boolean LOG = false;

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
	
	private void log(String s){
		if (LOG) System.out.println(s);
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
		display.setText("0");
	}
	
	/**
	 * Eraser used with backspace-key
	 */
	private void deleteLast(){
		if (2 > (display.getText().length()))
			display.setText("0");
		else	// Dot-notaion-freakshow:
			display.setText(display.getText().substring(0, display.getText().length()-1));
	}

	/**
	 * button(s) is used when a leading 0 should be replaced
	 * ie "01.."
	 */
	private void button(String s) {
		if (display.getText().equals("0"))
			display.setText(s);
		else
			display.setText(display.getText() + s);
	}

	/**
	 * Add is used when a lonely 0 in display is valid
	 * ie "0-..."
	 */
	private void add(String s){
		display.setText(display.getText() + s);
	}

	/**
	 * Called when the user type on keyboard
	 */
	@FXML
	private void keyPressed(KeyEvent ke){
		log("Key getCode: " + ke.getCode());
		log("Key getText: " + ke.getText());
		
		switch (ke.getText()){
		case "0": button("0"); break;
		case "1": button("1"); break;
		case "2": button("2"); break;
		case "3": button("3"); break;
		case "4": button("4"); break;
		case "5": button("5"); break;
		case "6": button("6"); break;
		case "7": button("7"); break;
		case "8": button("8"); break;
		case "9": button("9"); break;
		case "/": button("/"); break;
		case "*": button("*"); break;
		case "-": button("-"); break;
		case "+": button("+"); break;
		case ",": button("."); break;
		case ".": button("."); break;
		default:
			switch (ke.getCode().toString()){
				case "ENTER": evaluate (display.getText()); break;
				case "BACK_SPACE": deleteLast(); break;
				case "ESCAPE": display.setText("0"); break;
				default: break;
			}
		}
	}

	/**
	 * Called when the user clicks a button
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
	@FXML private void percent()	{ evaluate (display.getText() + "*100");}
	@FXML private void compute()	{ evaluate (display.getText()); }

	private void evaluate(String toEvaluate) {
		Double result = mainApp.evaluate(toEvaluate);
		if (Double.isFinite(result))
			if (result.toString().equals("0.0"))
				display.setText("0");
			else
				display.setText(result.toString());
	}
}
