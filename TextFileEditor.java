import java.io.*;		  // Using basic java IO for file reading/writing
import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces

public class TextFileEditor extends Frame implements ActionListener, WindowListener{
	//elements of the GUI
	private TextArea taEditorField;
	private Button btnLoad;
	private Button btnSave;
	private TextField tfFileName;
	
	//basic constructor that sets up the UI and event handlers
	public TextFileEditor() {
		setLayout(new FlowLayout());
		
		add(new Label("FileName: "));
		
		// construct the TextField component, adds it to superframe, sets editable so user can enter a text file name
		tfFileName = new TextField("0", 25); 
	    tfFileName.setEditable(true);
	    add(tfFileName);                     
	 
	    btnLoad = new Button("Load");   // construct the first Button component, used for loading
	    add(btnLoad);  
	    btnLoad.addActionListener(this);
	    
	    btnSave = new Button("Save");   // construct the Button component
	    add(btnSave);  // "super" Frame container adds Button component
	    btnSave.addActionListener(this);

	    addWindowListener(this); //used to handle closing the application when the red X is clicked
	    
	    taEditorField = new TextArea(10,50);
	    add(taEditorField);
	 
	    setTitle("Simple Text File Editor");  // "super" Frame sets its title
	    setSize(400, 300);        // "super" Frame sets its initial window size
	    setVisible(true);
		
	}

	public static void main(String args[]) throws IOException {
		
		  //main exists only to call the constructor for the frame
		  new TextFileEditor();
	   }
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == btnLoad)//Load button pressed
			//checks to see if the input field designates a valid filename, loads file if applicable
		{
			System.out.println("Load Button Pressed!");
			FileReader in = null;
			//String fileContents = "";
			
			try {
		         in = new FileReader(tfFileName.getText());
		         
		         int c;
		         while ((c = in.read()) != -1) {
		        	 taEditorField.append(Character.toString ((char) c));

		         }

		         
			}
			catch (FileNotFoundException e)
			{
				System.err.println("FileNotFoundException: " + e.getMessage());
			}
			catch (IOException e)
			{
				System.err.println("IOException: " + e.getMessage());
			}
			
			try { // closes file
			    if (in != null) {
		           in.close();
		        }
			}
			catch (IOException e)
			{
				System.err.println("IOException: " + e.getMessage());
			}
		}
			
		if(evt.getSource() == btnSave)
		{
			//event handling for the Save button
			System.out.println("Save Button Pressed!");
			FileWriter out = null;
			try {
		         out = new FileWriter(tfFileName.getText());
		         
		         out.write(taEditorField.getText());
		         
			}
			catch (FileNotFoundException e)
			{
				System.err.println("FileNotFoundException: " + e.getMessage());
			}
			catch (IOException e)
			{
				System.err.println("IOException: " + e.getMessage());
			}
			
			try {
			    if (out != null) {
		           out.close();
		        }
			}
			catch (IOException e)
			{
				System.err.println("IOException: " + e.getMessage());
			}
		}
	}
	
		//event handler for clicking the red X
	   public void windowClosing(WindowEvent evt) {
	      System.exit(0);  // Terminate the program
	   }
	 
	   // Not Used, but need to provide an empty body to compile.
	   public void windowOpened(WindowEvent evt) { }
	   public void windowClosed(WindowEvent evt) { }
	   public void windowIconified(WindowEvent evt) { }
	   public void windowDeiconified(WindowEvent evt) { }
	   public void windowActivated(WindowEvent evt) { }
	   public void windowDeactivated(WindowEvent evt) { }
}