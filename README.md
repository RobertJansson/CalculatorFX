# CalculatorFX
Uppsala University P2 - Lab4

JavaFX-version of my calculator using MVC (model, view, controller).

<p><b>
CalculatorFX.java
</b><br>
The main controller.

<p><b>
CGFXModel.java
</b><br>
Model that load the graphics package, convert that to icons and store those in as ImageIcons in an array.
Load the normal- and the advanced background images and also prepare them as ImageIcons so they are ready for the CViewer as Swing components.
The model also compose all buttons with ImageIcons so they are ready for the Viewer. There are getter methods for everything in the Controller.

<p><b>
RootLayout -> CMenuController.java
CNormal -> CNormalController.java
CAdvanced -> CAdvancedController.java
</b><br>
The viewer for the calculator has a root layout to set up the window and menu-bar. CNormal is always loaded and CAdvanced can be toggled on/off in the menu. The buttons in the advanced part are not wired yet.

<p><b>
ModelBrain.java
</b><br>
The model of CalculatorFX. Submit evaluations to Evaluation which is a backtracking recursive parser.
