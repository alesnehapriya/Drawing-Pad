# Drawing-Pad
Use Case: Drawing Pad
Primary Actor: End User who wishes to use the drawing pad to draw shapes, text, import background image, save to file, open the saved file.
Stakeholders and Interest:
End User: who desires to use the drawing Pad to draw any shapes.
Programmer: executes without any errors and fully functional.
Preconditions:
1. Drawing Pad is shown and default stroke is selected.
2. User can make use of Fill and size of stroke to draw.
3. Also, User can select the size of the eraser to erase.
Success Guarantee – Post Conditions:
1. User can create new paint or load any history paint saved.
2. User can start with stroke and use any shape from the Tool Box on the left.
3. Tool Box contains –
 Text
 Eraser
 Line
 Circle/ Ellipse
 Rectangle
 Stroke
 Select Color
 Fill Background Color
 Background Image
 Erase selected area
 Variable size for stroke and Eraser
4. Any time the shapes can be performed Undo action.
5. Has Color ToolBox
6. Has Font ToolBox
Main Success Scenario (Basic Flow): Input Events from Actor: End User System Events and Responses
Open the application.
Displays the DrawingPad.
Selects New from File Menu
New Canvas is displayed.
Selects Open from File Menu
Load any previous file.
Selects Save from File Menu
Save current Paint to the file.
Selects Save As from File Menu
Saved as new File.
Selects Font from Option menu
Font can be selected.
Selects Color from Option menu
Color can be selected.
Selects Undo from Option menu
Undo is performed.
Selects Text from ToolBox
Entered Text is printed with the selected Font.
Selects Eraser from ToolBox
Can be erased with the size selected.
Selects Line from ToolBox
Line is drawn after checking fill checkbox.
Selects Circle from ToolBox
Circle is drawn after checking fill checkbox.
Selects Rectangle from ToolBox
Rectangle is drawn after checking fill checkbox.
Selects Stroke from ToolBox
Stroke can be drawn.
Selects Color from ToolBox
Color can be selected.
Selects Background Color from ToolBox
Background Color is filled.
Selects Image from ToolBox
Image is printed to Canvas Background.
Selects “Select text Area” to erase
Selected text area is erased.
Fill CheckBox
Can be checked and unchecked to draw Shapes.
Extensions or Alternative Scenario:
1. Error Message is generated for the following exceptions:
a. If no file is selected.
b. If nothing is drawn and undo is performed.
