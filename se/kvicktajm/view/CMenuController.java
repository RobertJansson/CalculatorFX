package se.kvicktajm.view;

import javafx.fxml.FXML;
import se.kvicktajm.CalculatorFX;

public class CMenuController {

	private CalculatorFX mainApp;
		
	/**
	 * Called by the main application to give a reference back to itself.
	 * @param mainApp
	 */
	public void setMainApp(CalculatorFX mainApp) {
		this.mainApp = mainApp;
	}
//    @FXML private void toggleAdvanced() { mainApp.toggleAdvanced();}
	@FXML public void toggleAdvanced(){ mainApp.toggleAdvanced(); }
	@FXML private void quit() { System.exit(0); }
	@FXML private void pi() { mainApp.insertConstant("PI"); }
	@FXML private void speedOfLight() { mainApp.insertConstant("SoL"); }
	@FXML private void ultimateAnswer() { mainApp.insertConstant("DEEPTHOUGHT"); }
	
}
