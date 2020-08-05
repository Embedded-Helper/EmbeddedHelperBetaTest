/**Jacob Smith Embedded Helper 8/4/20
 * A plugin for the Arduino IDE using the Embedded Helper Source Code
 * (not included here but imported)
 * Allows user to generate class, see an example, and see a reminder
 * to fill out the beta test form
 * 
 */
package com.embeddedhelper;

import processing.app.Editor;
import processing.app.tools.Tool;

//classes to do.input/output with arduino IDE
import processing.app.SketchController;
import processing.app.EditorTab;
import processing.app.SketchFile;
import static processing.app.I18n.tr;

import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cc.arduinoclassmaker.ArduinoClassContainer;
import cc.arduinoclassmaker.SketchParser;
import cc.arduinoclassmaker.otherClassFileMaker;

public class EmbeddedHelper implements Tool {
	Editor editor;

	public void init(Editor editor) {
		this.editor = editor;
	}

	public String getMenuTitle() {
		return "Generate Class";
	}

	public void run() {
		launchClassGenTool(editor.getSketchController(), editor.getCurrentTab(), editor.getCurrentTab().getSketchFile(),
				editor.getTabs(), editor);

	}

	/**
	 * Reads the file name and contents of the current sketch, and creates a body
	 * file, header file, keywords, and example file
	 */
	public static void launchClassGenTool(SketchController controller, EditorTab tab, SketchFile sketchFile,
			List<EditorTab> tabs, Editor editor) {
		// create private LibHandler Class so graphics will be updated
		class LibHandler implements Runnable {
			@Override
			/**
			 * generates library files and display status messages
			 */
			public void run() {

				// show options dialogue
				Object[] options = { "Convert this Sketch", "See an Example" };
				int result = JOptionPane.showOptionDialog(editor,
						"Would you like to convert this program into a class or see an example?",
						"Class Generator Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);

				boolean classGenerated = false;
				if (result == 0) {
					// generate class option 0
					classGenerated = generateClass(editor, tab, sketchFile, controller);

					// wait for x seconds
					try {
						Thread.sleep(15000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (classGenerated) {
						// display message to go to beta tets form in browser
						String url = "https://forms.gle/NBxefPFHbHeJVP2w8";
						Object[] testOptions = { "Yes, please \n(will launch browser)", "No, thanks" };
						int testResult = JOptionPane.showOptionDialog(editor,
								"Please consider filling out our beta test feedback form!",
								"Class Generator Feedback Form", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, testOptions, testOptions[0]);

						//show beta test  form in browser or at least a link to it
						if (testResult == JOptionPane.YES_OPTION) {
							//launch beta test form in browser
							try {
								Desktop desktop = Desktop.getDesktop();
								desktop.browse(new URI(url));
								// if can't launch in browser, just display message with link
							} catch (Exception e) {
								JTextArea ta = new JTextArea(1, 42);
								ta.setText("Please go to \t" + url + "\t in your browser");
								ta.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
								ta.setWrapStyleWord(true);
								ta.setLineWrap(true);
								ta.setCaretPosition(0);
								ta.setEditable(false);
								JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Class Generator Example",
										JOptionPane.INFORMATION_MESSAGE);
							}

						}
					}

				// show example sketch
				} else if (result == 1) {
						
					JTextArea ta = new JTextArea(40, 90);
					ta.setText(
							"This Sketch flashes a light on and off using Morse Code. \nCopy it into an Arduino Sketch"
									+ "\nThen select tools--> Generate Class--> Convert This Sketch and \nchose a file location in your libraries folder\n\n"
									+ morseExample);
					ta.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
					ta.setWrapStyleWord(true);
					ta.setLineWrap(true);
					ta.setCaretPosition(0);
					ta.setEditable(false);

					JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Class Generator Example",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		// see private handleRun method of Editor class, try line 1632
		new Thread(new LibHandler()).start();
	}

	/**
	 * Converts the program into a generated class
	 * 
	 * @param editor     Arduino object used to save file
	 * @param tab        Arduino Object used to get the contents of this tab
	 * @param sketchFile Arduino Object used to get file name
	 * @param controller Arduino object used to display error message
	 * @return whether the class was generated
	 */
	private static boolean generateClass(Editor editor, EditorTab tab, SketchFile sketchFile,
			SketchController controller) {

		// save the library to the correct location, also gives user chance to name
		// class
		editor.handleSaveAs();

		// get the file name and contents of the sketch
		String[] sketchInfo = getNameContentsPath(tab, sketchFile);
		String className = sketchInfo[0];
		String contents = sketchInfo[1];
		String parentPath = sketchInfo[3];

		// compile sketch to check for errors
		editor.statusNotice(tr("Compiling sketch..."));
		if (failedToCompile(editor, controller)) {
			return false;
		}
		// parse the sketch into format used to generate arduino classes
		editor.statusNotice(tr("Creating Library Files..."));
		SketchParser parser = new SketchParser(contents);
		ArduinoClassContainer cont = parser.getContainer(className, false);
		// create the files with strings
		editor.statusNotice(tr("Creating Tabs..."));
		createNewFile(parentPath, className + ".cpp", cont.getBody());
		createNewFile(parentPath, className + ".h", cont.getHeader());

		// create other class files that won't be opened in the IDE
		otherClassFileMaker.createClassFiles(className, parentPath, cont.getExample(), cont.getKeywords());
		// clear status
		editor.statusNotice("The " + className + " Class was Generated!");

		System.out.println("CLASSGEN: The class files were created, please close this window");
		System.out.println("\t and go to " + parentPath + " to see them");
		return true;

	}

	/**
	 * compiles the sketch before arduino class is generated to force the user to
	 * submit a correct class
	 * 
	 * @return true if class failed to compile
	 */
	private static boolean failedToCompile(Editor editor, SketchController controller) {
		System.out.println("CLASSGEN: Compiling sketch to check for errors...");
		String output = "failed";
		try {
			output = controller.build(true, false);
		} catch (Exception e) {
		}
		if ("failed".equals(output)) {
			editor.statusError("CLASSGEN: Error, the sketch failed to compile, exiting sketch generator");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Reads a sketch for Arduino Class Generation
	 * 
	 * @return the name, contents, file path of the sketch, and file path of parent
	 */
	private static String[] getNameContentsPath(EditorTab currentTab, SketchFile sketchFile) {

		// create array to return sketch information in
		String[] sketchInfo = new String[4];
		// load sketch file name
		String fileName = sketchFile.getFile().getName();
		// remove extension from fileName
		sketchInfo[0] = fileName.replaceAll(".ino", "");
		// load sketch contents
		sketchInfo[1] = currentTab.getText();
		// load sketch path
		sketchInfo[2] = sketchFile.getFile().getPath();
		// load parent path
		sketchInfo[3] = sketchFile.getFile().getParentFile().getPath();
		return sketchInfo;
	}

	/**
	 * Helper method to create a new file, used for the body and header files
	 * 
	 * @param parentPath the path the file will be created out
	 * @param name       the name of the new file
	 * @param contents   the contents of the new file
	 */
	private static void createNewFile(String parentPath, String name, String contents) {
		// create the File using Java File class
		File test = new File(parentPath, name);
		FileWriter writer = null;
		try {
			test.createNewFile();
			// Populate the file with the contents string
			writer = new FileWriter(test);
			writer.write(contents);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// demonstrate the class's functionality by feeding it a sample file name and
		// contents strnig
		String className = "Blank";
		String contents = "void setup() {" + "// put your setup code here, to run once:\n\n" + "}\n" + "void loop() {\n"
				+ "// put your main code here, to run repeatedly:\n" + "}";

		// generate the classes from the sample file name and contents string
		SketchParser parser = new SketchParser(contents);
		ArduinoClassContainer cont = parser.getContainer(className, false);

		// print the created files
		System.out.println("NAME: " + className);
		System.out.println("ORIGINAL FILE:\n" + cont.getHeader());
		System.out.println("HEADER FILE:\n" + cont.getHeader());
		System.out.println("BODY FILE:\n" + cont.getBody());
		System.out.println("EXAMPLE FILE:" + cont.getExample());
		System.out.println("KEYWORDS FILE:\n" + cont.getKeywords());

		// launch the applications to rebuild and test the extension of the Arduino IDE
		Runtime.getRuntime().addShutdownHook(new Thread());
		try {
			// launch the terminal so I can recompile the .jar file
			Runtime.getRuntime().exec("cmd /c start C:\\cygwin64\\Cygwin.bat \n l \n");
			Thread.sleep(7000);
			// launch the Arduino IDE
			Runtime.getRuntime().exec("cmd /c start C:\\\"Program Files (x86)\"\\Arduino\\arduino.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// stores the Morse example
	private static String morseExample =

			"/*Written by Jacob Smith for Brandeis University.\n" + "Allows arduino to cmmunicate with morse code*/\n\n"
					+ "//the pin to flash on\n" + "int pin = 13;\n\n" + "//runs once, sets up pins\n"
					+ "void setup(){\n" + "  pinMode(pin, OUTPUT);\n" + "}\n\n"
					+ "//runs many times, flashes a message\n" + "void loop(){\n" + "  dot(); dot(); dot();\n"
					+ "  dash(); dash(); dash();\n" + "  dot(); dot(); dot();\n" + "  //wait 3 seconds...\n"
					+ "  delay(3000);\n\n" + "}\n\n" + "//plays a dot on the pin\n" + "void dot(){\n"
					+ "  digitalWrite(pin, HIGH);\n" + "  //wait quarter second...\n" + "  delay(250);\n"
					+ "  digitalWrite(pin, LOW);\n" + "  delay(250);\n" + "}\n\n" + "//plays a dash on the pin\n"
					+ "void dash()\n" + "{\n" + " digitalWrite(pin, HIGH);\n" + "  //wait one second...\n"
					+ "  delay(1000);\n" + "  digitalWrite(pin, LOW);\n" + "  delay(250);\n" + "}\n\n";
}
