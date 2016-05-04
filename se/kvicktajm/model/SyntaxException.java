package se.kvicktajm.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SyntaxException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SyntaxException (String msg){
//		super (msg);
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Syntax exception");
		alert.setContentText(msg);

		alert.showAndWait();

	}
}
