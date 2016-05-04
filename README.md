# CalculatorFX
Uppsala University P2 - Lab4<br>
JavaFX-version of my calculator using MVC (model, view, controller).<p>

<p><b>Controller</b><br>
CalculatorFX.java - The main controller.

<p><b>View</b><br>
RootLayout.fxml -> CMenuController.java<br>
RootLayout set up the window and menu-bar as the program start.<br>
CNormal.fxml -> CNormalController.java<br>
CNormal is always loaded which display the normal keypad and the display<br>
CAdvanced.fxml -> CAdvancedController.java<br>
CAdvanced can be toggled on/off in the File-menu. The buttons in the advanced part are not wired yet.

<p><b>Model</b><br>
ModelBrain.java - The model of CalculatorFX.<br>
Evaluation.java - A backtracking recursive parser, called by the model to perform an evaluation.
<p>
Tooling: Java 8 and an IDE with JavaFX. For Eclipse, go here: http://www.eclipse.org/efxclipse/install.html
The program is configured to use aguafx (http://aquafx-project.com) so that the programs menubar is moved from the window into the menubar of the computer. The constructor in CalculatorFX.java has a note what to change if that is not used.
