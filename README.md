# CalculatorFX
Uppsala University P2 - Lab4<br>
JavaFX8-version of my calculator using MVC and FXML<p>
CalculatorFX has been compiled into a program:<br>
OS X: <A HREF=https://kvicktajm.se/apps/osx/CalculatorFX.dmg>CalculatorFX.dmg</A><br>

<p><b>Controller</b><br>
CalculatorFX.java - The main controller.

<p><b>View</b><br>
RootLayout.fxml -> CMenuController.java<br>
RootLayout set up the window and menu-bar as the program start.<br>
CNormal.fxml -> CNormalController.java<br>
CNormal is always loaded with its normal keypad and display<br>
CAdvanced.fxml -> CAdvancedController.java<br>
CAdvanced can be toggled on/off in the File-menu. (The buttons in the advanced part are not wired yet, layout may also change).

<p><b>Model</b><br>
ModelBrain.java - The model of CalculatorFX.<br>
Evaluation.java - A backtracking recursive descent, called by the model to perform an evaluation.
<p>
Note: Evaluations start at CNormalController <-> CNormalController <-> ModelBrain <-> Evaluation (where dive begins)
<p>
Tooling: Java 8 and an IDE with JavaFX. For Eclipse, update everything and go here for e(fx)clipse toolset:<br> http://www.eclipse.org/efxclipse/install.html<br>
You would also want to install SceneBuilder. Oracle messed up the distribution but here is a better source:<br>
http://gluonhq.com/open-source/scene-builder/<p>
CalculatorFX is also using aguafx (http://aquafx-project.com). That move the programs menubar from its window into the menubar of the computer. The constructor in CalculatorFX.java (or FX8-start method) has a note what to change if that is not used. Menu bar height of 29 pixels must be added to viewer.
