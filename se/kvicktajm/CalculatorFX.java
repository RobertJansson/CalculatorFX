package se.kvicktajm;

import com.aquafx_project.AquaFx;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
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

public class CalculatorFX extends Application
{
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ModelBrain model;
	private CNormalController display;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Caclulator");
		this.model = new ModelBrain();
		AquaFx.style();					// OS X adaption - comment this out and you must
		initRootLayout();				// ...change height of root window: 360 -> 389
		showNormalView();
	}

	/**
	 * Initializes the root layout.
	 */
	private void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CalculatorFX.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the menu controller access to the main app.
			// Require controller to be set in FXML
			CMenuController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showNormalView() {
		try {
			// Load normal calculator view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CalculatorFX.class.getResource("view/CNormal.fxml"));
			AnchorPane normalView = (AnchorPane) loader.load();

			// Set normal calculator view into the center of root layout.
			rootLayout.setCenter(normalView);

			// Give the controller access to the main app.
			// Require controller to be set in FXML
			CNormalController controller = loader.getController();
			controller.setMainApp(this);
			display = controller;	// From now we use it to update the display

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
			// Load normal calculator view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CalculatorFX.class.getResource("view/CAdvanced.fxml"));
			AnchorPane advancedView = (AnchorPane) loader.load();

			// Set normal calculator view into the center of root layout.
			primaryStage.setWidth(800);
			rootLayout.setLeft(advancedView);

			// Give the controller access to the main app.
			// Require controller to be set in FXML
			CAdvancedController controller = loader.getController();
			controller.setMainApp(this);

			this.primaryStage.setTitle("Advanced caclulator");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// This is called from viewers
	public String evaluate(String s){
		// throws SyntaxException, EvalException, IOException{
		// System.out.println("String to evaluate: '" + s + "'");
		Double result = model.evaluate(s);
		String rs = result.toString();
		if (rs.substring(rs.length()-2, rs.length()).equals(".0")) // Trunc .0
			rs = rs.substring(0, rs.length()-2);
		return rs;
	}
	
	public <T> void pushConstant(T t){
		display.push(t.toString());
	}
	
	public void display(String expression){
		display.eval(display.push(expression));
	}

	public static void main(String[] args) {
		launch(args);
	}

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
