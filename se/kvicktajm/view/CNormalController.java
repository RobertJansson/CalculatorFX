package se.kvicktajm.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import se.kvicktajm.CalculatorFX;

public class CNormalController
{
	// Simple debug flag:
	private static final boolean LOG = false;

	@FXML private Label display;		// Display of the calculator
	private CalculatorFX mainApp;		// Reference to the main application.
	private static boolean cmd;

	/**
	 * The constructor is called before the initialize() method.
	 */
	public CNormalController() { display = new Label(""); }
	

	/**
	 * Private method for debugging.
	 * @param s is the String to print to console
	 */
	private void log(String s){ if (LOG) System.out.println(s); }

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML private void initialize() {	}

	/**
	 * Called by the main application to give a reference back to itself.
	 * @param mainApp
	 */
	public void setMainApp(CalculatorFX mainApp) {
		this.mainApp = mainApp;
		push("0"); 		// Initialize a start value of display.
						// Last session from a plist (or XML as this is Java)
						// would be a nice feature (Controller prepared)
	}
	
	// Getters:
	public String getDisplay(){ return display.getText(); }
	
	/**
	 * Called when the user type on keyboard
	 */
	@FXML
	private void keyPressed(KeyEvent ke){
		log("Key getCode: " + ke.getCode());
		log("Key getText: " + ke.getText());
		
		switch (ke.getText()){
		case "c": if (!cmd) push("cos("); break;
		case "e": if (!cmd) push("exp("); break;
		case "l": if (!cmd) push("log("); break;
		case "P": if (!cmd) push("PI"); break;
		case "s": if (!cmd) push("sin("); break;
		case "t": if (!cmd) push("tan("); break;
		case "0": if (!cmd) push("0"); break;
		case "1": if (!cmd) push("1"); break;
		case "2": if (!cmd) push("2"); break;
		case "3": if (!cmd) push("3"); break;
		case "4": if (!cmd) push("4"); break;
		case "5": if (!cmd) push("5"); break;
		case "6": if (!cmd) push("6"); break;
		case "7": if (!cmd) push("7"); break;
		case "8": if (!cmd) push("8"); break;
		case "9": if (!cmd) push("9"); break;
		case "/": if (!cmd) push("/"); break;
		case "*": if (!cmd) push("*"); break;
		case "-": if (!cmd) push("-"); break;
		case "+": if (!cmd) push("+"); break;
		case ",": if (!cmd) push("."); break;
		case ".": if (!cmd) push("."); break;
		case "(": if (!cmd) push("("); break;
		case ")": if (!cmd) push(")"); break;
		default:
			switch (ke.getCode().toString()){
				case "ENTER": eval (display.getText()); break;
				case "BACK_SPACE": deleteLast(); break;
				case "ESCAPE": display.setText("0"); break;
				case "SPACE": display.setText("0"); break;
				default: break;
			}
		}
		if (ke.getCode().toString().equals("COMMAND")){
			cmd = true;					log("Command");
		} else {
			cmd = false;				log("No command");
		}
	}

	/**
	 * Called when the user clicks a button
	 */
	@FXML private void b0() { push("0"); }
	@FXML private void b1() { push("1"); }
	@FXML private void b2() { push("2"); }
	@FXML private void b3() { push("3"); }
	@FXML private void b4() { push("4"); }
	@FXML private void b5() { push("5"); }
	@FXML private void b6() { push("6"); }
	@FXML private void b7() { push("7"); }
	@FXML private void b8() { push("8"); }
	@FXML private void b9() { push("9"); }
	@FXML private void divide()		{ append("/"); }
	@FXML private void multiply()	{ append("*"); }
	@FXML private void subtract()	{ append("-"); }
	@FXML private void add()		{ append("+"); }
	@FXML private void comma() 		{ append("."); }
	@FXML private void clear()		{ display.setText("0"); }
	@FXML private void plusMinus()	{ display.setText("-" + display.getText()); }
	@FXML private void percent()	{ eval(display.getText() + "*100");}
	@FXML private void compute()	{ eval(display.getText()); }

	/**
	 * Tell controller to evaluate our expression
 	 * Public: To let mainApp start an evaluation (menubar conversions)
	 */
	public void eval(String expression) {
		display.setText(mainApp.evaluate(expression));
	}

	/**
	 * Used when a leading 0 should be kept
	 * ie "0-.."
	 */
	private void append(String expression){
		display.setText(display.getText() + expression);
	}

	/**
	 * Used when a leading 0 should be replaced
	 * ie "01.."
	 * Public: To let mainApp add variables
	 */
	public String push(String expression) {
		if (display.getText().equals("0"))
			display.setText(expression);
		else
			display.setText(display.getText() + expression);
		return new String(display.getText());
	}

	/**
	 * Eraser (backspace)
	 */
	private void deleteLast(){
		if (2 > (display.getText().length()))
			display.setText("0");
		else
			display.setText(display.getText().substring(0, display.getText().length()-1));
	}
}
