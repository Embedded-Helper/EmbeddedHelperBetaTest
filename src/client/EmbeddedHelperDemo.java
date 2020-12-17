/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 29, 2019
  *Assignment: Personal Study, allows the user to convert an Arduino sketch into a lirbary,
  *the core of the ArduinoClass Generator project	
  *Bugs:
  *Sources:https://stackoverflow.com/questions/11109450/how-do-i-remove-a-component-from-a-jpanel-and-then-redisplay-the-frame
  *https://www.reddit.com/r/java/comments/2ok338/switching_jpanel_when_clicking_on_a_jbutton_swing/
  */
package client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cc.arduinoclassmaker.SketchParser;
import cc.arduinoclassmaker.ArduinoClassContainer;

public class EmbeddedHelperDemo extends GUI {

	// stores the Morse example
	public static String morseExample =

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

	/**
	 * @param title
	 * @param generalPrompt
	 * @param fields
	 * @param examples
	 * @param buttonName
	 */
	public EmbeddedHelperDemo(JFrame frame, String title, String generalPrompt, String[] fields, String[] examples,
			String buttonName, int x, int y, int windowWidth, int windowLength, Font font) {
		super(title, generalPrompt, fields, examples, buttonName, x, y, font);

	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// set up GUI
		String title = "Welcome to the Arduino Sketch to Library Converter";
		String generalPrompt = "please enter fields and press button";
		String[] fields = { "Desired Library Name", "Arduino Sketch" };
		String[] examples = { "Morse", morseExample };
		String buttonName = "Press to generate Arduino Library";
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		Font font = new Font("ComicSans", Font.LAYOUT_LEFT_TO_RIGHT, 20);
		EmbeddedHelperDemo d = new EmbeddedHelperDemo(null, title, generalPrompt, fields, examples, buttonName,
				width / 3, height / 4, width / 4, height, font);
		d.setVisible(true);

		// Embedded Helper Arduino Extension

		Object[] options = { "No", "Yes" };

		int result = JOptionPane.showOptionDialog(d, "Would you like to see an example program?",
				"Class Generator Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION, null, options,
				options[1]);

		// show example sketch
		if (result == 1) {

			JTextArea ta = new JTextArea(40, 90);
			ta.setText("This Sketch flashes a light on and off using Morse Code. \nCopy it into an Arduino Sketch"
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

	public void updateClass(JPanel jp, Font font) {

		// load necessary fields from gui
		String className = textBoxes[0].getText();
		String contents = textBoxes[1].getText();

		// create sketch parser from loaded file
		SketchParser parser = new SketchParser(contents);
		System.out.println(parser);
		// create ArduinoClassContainer from sketch parser
		ArduinoClassContainer cont = parser.getContainer(className, false);
		System.out.println(cont);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		//show the windows
		this.newPanel("Class .cpp file", cont.getBody(), width / 4 * 0, 0, width / 4, height / 4, font);
		this.newPanel("Class Example File", cont.getExample(), width / 4 * 1, 0, width / 4, height / 4, font);
		this.newPanel("Class Header File", cont.getHeader(), width / 4 * 2, 0, width / 4, height / 4, font);
		this.newPanel("Class keywords file", cont.getKeywords(), width / 4 * 3, 0, width / 4, height / 4, font);
		this.newPanel("Original Sketch", contents, 0, 0, width / 4, 0, font);
		
		// display message to go to beta tets form in browser
		String url = "https://forms.gle/NBxefPFHbHeJVP2w8";
		this.newPanel("Beta Test", "Please consider filling out our beta test feedback form!\n" + url,0,0,width/4,0, font);
	}
}
