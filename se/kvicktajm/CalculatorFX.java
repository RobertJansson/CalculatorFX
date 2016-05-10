package se.kvicktajm;

import com.aquafx_project.AquaFx;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import se.kvicktajm.CalculatorFX;
import se.kvicktajm.model.ModelBrain;
import se.kvicktajm.view.CAdvancedController;
import se.kvicktajm.view.CMenuController;
import se.kvicktajm.view.CNormalController;
import se.kvicktajm.view.CTapeController;

public class CalculatorFX extends Application
{
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ModelBrain model;
	private CNormalController display;
	private CTapeController tape;
//	private ObservableList<String> list;

	// Initiate the JavaFX launch-sequence
	public static void main(String[] args) {
		launch(args);
	}

	// Come back and finish the JavaFX launch-sequence
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Caclulator");
		this.model = new ModelBrain();
//		list = FXCollections.observableArrayList();
		AquaFx.style();				// OS X adaption - comment this out and you must
		initRootLayout();			// ...change height of root window: 360 -> 389
		showNormalView();
	}

	/**
	 * Initializes the root layout.
	 */
	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CalculatorFX.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			CMenuController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the root layout.
	 */
	private void showNormalView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CalculatorFX.class.getResource("view/CNormal.fxml"));
			AnchorPane normalView = (AnchorPane) loader.load();
			rootLayout.setCenter(normalView);
			CNormalController controller = loader.getController();
			controller.setMainApp(this);
			display = controller;			// Save reference to display

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Advanced view */
	
	public void toggleAdvanced() {
		if (rootLayout.getLeft() == null)
			showAdvancedView();
		else
			hideAdvancedView();
	}

	private void hideAdvancedView() {
		rootLayout.setLeft(null);
		primaryStage.setWidth(320);
		this.primaryStage.setTitle("Caclulator");
	}

	private void showAdvancedView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CalculatorFX.class.getResource("view/CAdvanced.fxml"));
			AnchorPane advancedView = (AnchorPane) loader.load();
			primaryStage.setWidth(800);
			rootLayout.setLeft(advancedView);
			CAdvancedController controller = loader.getController();
			controller.setMainApp(this, display);
			this.primaryStage.setTitle("Advanced caclulator");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Tape view -should be converted into an ObservableList */

	public void toggleTapeView() {
		if (rootLayout.getBottom() == null)
			showTapeView();
		else
			hideTapeView();
	}

	private void hideTapeView() {
		rootLayout.setBottom(null);
		primaryStage.setHeight(382);
	}

	private void showTapeView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CalculatorFX.class.getResource("view/CTape.fxml"));
			AnchorPane tapeView = (AnchorPane) loader.load();
			primaryStage.setHeight(582);
			rootLayout.setBottom(tapeView);
			tape = loader.getController();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*							*/
	/*	Business intelligense	*/
	/*							*/
	
	public String evaluate(String s){
		Double result = model.evaluate(s);
		String rs = result.toString();
		if (rs.substring(rs.length()-2, rs.length()).equals(".0")) // Trunc .0
			rs = rs.substring(0, rs.length()-2);
		if (tape != null){
			tape.updateTape(s + " = " + rs + "\n");
		}
		return rs;
	}
	
	public <T> void pushConstant(T t){
		display.push(t.toString());
	}
	
	public void display(String expression){
		display.eval(display.push(expression));
	}

	/* File management - not yet implemented */
	
	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getCalculatorPlistPath() {
		Preferences prefs = Preferences.userNodeForPackage(CalculatorFX.class);
		String filePath = prefs.get("filePath", null);
		System.out.println(filePath);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * @param file the file or null to remove the path
	 */
	public void setCalculatorPlistPath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(CalculatorFX.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
			// Update the stage title.
			primaryStage.setTitle("Calculator - " + file.getName());
		} else {
			prefs.remove("filePath");
			// Update the stage title.
			primaryStage.setTitle("Calculator");
		}
	}
}