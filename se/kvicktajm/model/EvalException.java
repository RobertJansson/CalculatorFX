package se.kvicktajm.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EvalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EvalException (String msg){
//		super (msg);
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Evaluation exception");
		alert.setContentText(msg);

		alert.showAndWait();
	}
}
