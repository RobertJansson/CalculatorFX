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

	@FXML private void pi() { mainApp.pushConstant("PI"); }
	@FXML private void speedOfLight() { mainApp.pushConstant("SOL"); }
	@FXML private void ultimateAnswer() { mainApp.pushConstant("DEEPTHOUGHT"); }

	@FXML private void mps2kmph() { mainApp.display("*3.6");}
	@FXML private void kmph2mps() { mainApp.display("/3.6");}
	@FXML private void m2feet() { mainApp.display("*3");}
	@FXML private void feet2m() { mainApp.display("/3");}
	@FXML private void inch2mm() { mainApp.display("*25,4");}
	@FXML private void mm2inch() { mainApp.display("/25,4");}
	@FXML private void m2yard() { mainApp.display("*0.9");}
	@FXML private void yard2m() { mainApp.display("/0.9");}
	@FXML private void c2f() { mainApp.display("*2+35");}
	@FXML private void f2c() { mainApp.display("/2-35");}

}
