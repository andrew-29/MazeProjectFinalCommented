//Andrew Peng
//Student ID: 350894135
//ICS4U - Period 1
//April 3 2023

package MazeProjectFinalCommented;
import java.io.File;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;


import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.Random;
/**
 *This class represents the "game" itself and contains the method for running the "game".
 *It sets up the game window, menu bar, and various panels for generating and reading mazes.
 *It also displays the maze panel and the mouse navigating through the maze.
 *The game is initialized by creating an instance of this class and calling the run method.
 */
public class Game extends JFrame implements ActionListener 
{
	
	/**
	 * A Random object to generate random numbers for various purposes.
	 */
	Random random = new Random();

	/**
	 * A MazePanel object to display the maze in the game.
	 */
	MazePanel MazeDisplay;

	/**
	 * A Maze object that represents the maze in the game.
	 */
	Maze maze = new Maze();

	/**
	 * A Mouse object that represents the player's mouse in the game.
	 */
	Mouse mouse;

	
	/**
	 * The menu bar for the GUI interface.
	 */
	JMenuBar Menubar = new JMenuBar();

	/**
	 * The menu item used to generate a new maze.
	 */
	JMenuItem GenerateMaze = new JMenuItem("GENERATE MAZE");

	/**
	 * The menu item used to read a maze from a file.
	 */
	JMenuItem ReadFile = new JMenuItem("READ MAZE FROM FILE");

	/**
	 * The menu item used to return to the title screen.
	 */
	JMenuItem ReturnToTitle = new JMenuItem("RETURN TO TITLE SCREEN");

	
	/**
	 * String array of maze types available for selection
	 */
	String[] MazeTypes = {"Refined Maze", "Unrefined Maze"};

	/**
	 * String array of exit types available for selection
	 */
	String[] ExitTypes = {"Guaranteed Exit", " May Or May Not Have Exit"};

	/**
	 * Panel for maze generation options
	 */
	JPanel MazeGenerationPanel = new JPanel();

	/**
	 * Panel for number of rows in maze
	 */
	JPanel RowsPanel = new JPanel();

	/**
	 * Panel for number of columns in maze
	 */
	JPanel ColumnsPanel = new JPanel();

	/**
	 * Panel for selecting the exit type
	 */
	JPanel ExitTypePanel = new JPanel();

	/**
	 * Panel for selecting the maze type
	 */
	JPanel MazeTypePanel = new JPanel();

	/**
	 * ComboBox for selecting the maze type
	 */
	JComboBox MazeTypeComboBox = new JComboBox(MazeTypes);

	/**
	 * ComboBox for selecting the exit type
	 */
	JComboBox ExitTypeComboBox = new JComboBox(ExitTypes);

	/**
	 * Text field for entering the number of columns in maze
	 */
	JTextField ColumnsField = new JTextField(20);

	/**
	 * Text field for entering the number of rows in maze
	 */
	JTextField RowsField = new JTextField(20);

	/**
	 * Label for prompting the user to enter the number of rows in maze
	 */
	JLabel RowsPrompt = new JLabel("Please enter the number of rows you would like your maze to have:");

	/**
	 * Label for prompting the user to enter the number of columns in maze
	 */
	JLabel ColumnsPrompt = new JLabel("Please enter the number of columns you would like your maze to have:");

	/**
	 * Label for providing instructions for maze generation
	 */
	JLabel MazeGenerationInstructions = new JLabel("Please specify the amount of rows and columns you would like your maze to have. Additionally, you will have to choose whether");

	/**
	 * Label for providing instructions for maze generation
	 */
	JLabel MazeGenerationInstructions2 = new JLabel("or not you want your maze to be refined and whether or not you would like a guaranteed exit to be generated. The minimum maze ");

	/**
	 * Label for providing instructions for maze generation
	 */
	JLabel MazeGenerationInstructions3 = new JLabel("size is 5 x 5. If you input dimensions less than 5 x 5, a blank screen will appear.");

	/**
	 * Label for prompting the user to select the maze type
	 */
	JLabel MazeTypePrompt = new JLabel("Please select what type of maze you would like:");

	/**
	 * Label for prompting the user to select the exit type
	 */
	JLabel ExitTypePrompt = new JLabel("Please select whether you want a guaranteed exit:");

	/**
	 * Label for the title of the maze generation panel
	 */
	JLabel MazeGenerationTitle = new JLabel("MAZE GENERATION");

	/**
	 * Label for displaying an error message when user input is invalid
	 */
	JLabel InvalidInput = new JLabel();

	/**
	 * Label for a horizontal divider line
	 */
	JLabel Divider = new JLabel("-----------------------------------------------------------------------------------------------------");

	/**
	 * Label for displaying instructions
	 */
	JLabel InstructionsDisclaimer = new JLabel("INSTRUCTIONS:");

	/**
	 * Image of a small maze for displaying in the maze generation panel
	 */
	BufferedImage SmallMazePicture = ImageIO.read(new File("SmallMaze.png"));

	/**
	 * Label for displaying the small maze image
	 */
	JLabel SmallMazeLabel = new JLabel(new ImageIcon(SmallMazePicture));

	/**
	 * Button for generating the maze with user-selected options
	 */
	JButton GenerateButton = new JButton("Generate Maze!");
	
	/**
	 * A JPanel used for reading a maze from a file.
	 */
	JPanel FileReaderPanel = new JPanel();

	/**
	 * A JPanel used for displaying the file selection option.
	 */
	JPanel FilePanel = new JPanel();

	/**
	 * A JLabel used for displaying the selected file path.
	 */
	JLabel FileLabel = new JLabel("Selected File Path: ");

	/**
	 * A JTextField used for entering the file path.
	 */
	JTextField FileField = new JTextField(30);

	/**
	 * A JLabel used for displaying the title of the file reading panel.
	 */
	JLabel FileMazeTitle = new JLabel("READ MAZE FROM FILE");

	/**
	 * A JLabel used for displaying instructions for selecting a file.
	 */
	JLabel ReadFileInstructions = new JLabel("Using the file finder, select the file you would like to read the maze from. Click \"Open\" button to select file.");

	/**
	 * A JLabel used for displaying alternative instructions for entering the file path directly.
	 */
	JLabel ReadFileInstructions2 = new JLabel("Alternatively, you may also directly enter your file location into the file text field. Finally, press the generate button at the bottom.");

	/**
	 * A JFileChooser used for selecting a file to read the maze from.
	 */
	JFileChooser fileChooser = new JFileChooser();

	/**
	 * A JButton used for generating a maze from the selected file.
	 */
	JButton GenerateFileMaze = new JButton("Generate Maze From File!");

	/**
	 * File object used to store the current maze file being accessed.
	 */
	File MazeFile;

	/**
	 * JPanel used to display the title screen.
	 */
	JPanel TitlePanel = new JPanel();

	/**
	 * JLabel used to display the title of the game.
	 */
	JLabel Title = new JLabel("MAZE GAME");

	/**
	 * JLabel used to display a message instructing the user to use the menu bar to navigate the GUI.
	 */
	JLabel TitleMessage = new JLabel("Please use menu bar at top of the page to navigate GUI.");

	/**
	 * BufferedImage object used to store the image of the maze used in the title screen.
	 */
	BufferedImage MazePicture = ImageIO.read(new File("Maze.png"));

	/**
	 * JLabel used to display the image of the maze in the title screen.
	 */
	JLabel MazeLabel = new JLabel(new ImageIcon(MazePicture));


	/**

	Constructor for the Game class.

	Initializes the GUI components for the title screen, generate maze from file screen,

	and maze generation screen.

	@throws Exception when an error occurs during image reading.
	*/
	public Game() throws Exception {
	// Set up window size and title
	setSize(1920, 1080);
	setTitle("Menu");

	// Set up layout managers for panels
	BoxLayout layout1 = new BoxLayout(MazeGenerationPanel, BoxLayout.Y_AXIS);
	BoxLayout layout2 = new BoxLayout(FileReaderPanel, BoxLayout.Y_AXIS);
	BoxLayout layout3 = new BoxLayout(TitlePanel, BoxLayout.Y_AXIS);

	// SET UP TITLE SCREEN GUI
	TitlePanel.setLayout(layout3);

	// Set fonts for title screen components
	Title.setFont(new Font("Serif", Font.BOLD, 90));
	TitleMessage.setFont(new Font("Serif", Font.BOLD, 14));

	// Add components to title screen panel
	TitlePanel.add(Title);
	TitlePanel.add(Divider);
	TitlePanel.add(TitleMessage);
	TitlePanel.add(MazeLabel);

	// SET UP GENERATE MAZE FROM FILE GUI
	FileReaderPanel.setLayout(layout2);
	FilePanel.add(FileLabel);
	FilePanel.add(FileField);
	FileMazeTitle.setFont(new Font("Serif", Font.BOLD, 36));
	InstructionsDisclaimer.setFont(new Font("Serif", Font.BOLD, 14));

	// Add components to generate maze from file panel
	GenerateFileMaze.setBackground(Color.RED);
	GenerateFileMaze.setOpaque(true);
	FileReaderPanel.add(FileMazeTitle);
	FileReaderPanel.add(InstructionsDisclaimer);
	FileReaderPanel.add(ReadFileInstructions);
	FileReaderPanel.add(ReadFileInstructions2);
	FileReaderPanel.add(Divider);
	FileReaderPanel.add(fileChooser);
	FileReaderPanel.add(FilePanel);
	FileReaderPanel.add(GenerateFileMaze);

	// SET UP MAZE GENRATION GUI
	MazeGenerationPanel.setLayout(layout1);
	MazeGenerationTitle.setFont(new Font("Serif", Font.BOLD, 36));
	InstructionsDisclaimer.setFont(new Font("Serif", Font.BOLD, 14));

	// Add components to maze generation panel
	RowsPanel.add(RowsPrompt);
	RowsPanel.add(RowsField);
	ColumnsPanel.add(ColumnsPrompt);
	ColumnsPanel.add(ColumnsField);
	MazeTypePanel.add(MazeTypePrompt);
	MazeTypePanel.add(MazeTypeComboBox);
	ExitTypePanel.add(ExitTypePrompt);
	ExitTypePanel.add(ExitTypeComboBox);
	GenerateButton.setBackground(Color.RED);
	GenerateButton.setOpaque(true);
		
	MazeGenerationPanel.add(MazeGenerationTitle);
	MazeGenerationPanel.add(Divider);
	MazeGenerationPanel.add(InstructionsDisclaimer);
	MazeGenerationPanel.add(MazeGenerationInstructions);
	MazeGenerationPanel.add(MazeGenerationInstructions2);
	MazeGenerationPanel.add(MazeGenerationInstructions3);
	MazeGenerationPanel.add(Divider);
	MazeGenerationPanel.add(ColumnsPanel);
	MazeGenerationPanel.add(RowsPanel);
	MazeGenerationPanel.add(MazeTypePanel);
	MazeGenerationPanel.add(ExitTypePanel);
	MazeGenerationPanel.add(GenerateButton);
	MazeGenerationPanel.add(InvalidInput);
	MazeGenerationPanel.add(SmallMazeLabel);
	//SET UP ACTION LISTNERS
	GenerateMaze.addActionListener(this);
	ReadFile.addActionListener(this);
	fileChooser.addActionListener(this);
	ReturnToTitle.addActionListener(this);
	GenerateButton.addActionListener(this);
	GenerateFileMaze.addActionListener(this);
	
	//Set up menu bar
	Menubar.add(GenerateMaze);
	Menubar.add(ReadFile);
	Menubar.add(ReturnToTitle);
	
	//Add title panel
	add(TitlePanel);
	
	//ADD MENU BAR
	setJMenuBar(Menubar);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
		
	}
	/**
	 * The main method of the program that creates an instance of the Game class.
	 *
	 * @param args an array of command-line arguments passed to the program
	 * @throws Exception if any errors occur during the execution of the program
	 */
	public static void main(String[] args) throws Exception 
	{
	    // Create an instance of the Game class
	    Game frame = new Game();
	} 

	/**
	 * Implements the ActionListener interface for the GUI. It defines
	 * the actionPerformed() method that is called when any of the 
	 * buttons are clicked.
	 * 
	 * @param e an ActionEvent object
	 */
	public void actionPerformed(ActionEvent e) 
	{
	    // get the command from the ActionEvent
	    String command = e.getActionCommand();
	    
	    // if the "GENERATE MAZE" button is clicked
	    if (command.equals("GENERATE MAZE"))
	    {
	        // clear the content pane and add the MazeGenerationPanel
	        this.getContentPane().removeAll();
	        this.repaint();
	        this.add(MazeGenerationPanel);
	        
	       
	        
	        
	        // revalidate the panel
	        this.revalidate();
	    }
	    // if the "RETURN TO TITLE SCREEN" button is clicked
	    else if (command.equals("RETURN TO TITLE SCREEN"))
	    {
	        // clear the content pane and add the TitlePanel
	        this.getContentPane().removeAll();
	        this.repaint();
	        this.add(TitlePanel);
	        
	        // revalidate the panel
	        this.revalidate();
	    }
	    // if the "READ MAZE FROM FILE" button is clicked
	    else if (command.equals("READ MAZE FROM FILE"))
	    {
	        // clear the content pane and add the FileReaderPanel
	        this.getContentPane().removeAll();
	        this.repaint();
	        this.add(FileReaderPanel);
	        
	        // revalidate the panel
	        this.revalidate();
	    }
	    // if the "ApproveSelection" button in the file chooser is clicked
	    else if (command.equals(JFileChooser.APPROVE_SELECTION))
	    {
	        // set the text of the FileField to the path of the selected file
	        FileField.setText(fileChooser.getSelectedFile().getPath());
	    }
	    // if the "Generate Maze From File!" button is clicked
	    else if (command.equals("Generate Maze From File!"))
	    {
	        // clear the content pane and repaint it
	        this.getContentPane().removeAll();
	        this.repaint();
	        
	        // read the maze from the selected file and solve it
	        MazeFile = new java.io.File(FileField.getText());
	        try {
	            maze.ReadFile(MazeFile);
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	        mouse = new Mouse();
	        mouse.SolveMaze();
	        
	        // create the MazePanel with the maze map and the shortest solution
	        try {
	            MazeDisplay = new MazePanel(maze.map, mouse.ShortestMazeSolution);
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	        
	        // add the MazePanel and revalidate the panel
	        this.add(MazeDisplay);
	        this.revalidate();
	    }
	    // if the "Generate Maze!" button is clicked
	    else if (command.equals("Generate Maze!"))
	    {
	        // clear the content pane and repaint it
	        this.getContentPane().removeAll();
	        this.repaint();
	        
	        // generate a maze of the selected type, size and exit type
	        boolean ExitExists;
	        int columns = Integer.parseInt(ColumnsField.getText());
	        int rows = Integer.parseInt(RowsField.getText());
	        
	        //check if columns or rows are invalid
	        if(columns <5 || rows <5)
	        {
	        	InvalidInput.setText("MAZE MUST BE AT LEAST 5X5");
	        	MazeGenerationPanel.revalidate();
	        	this.revalidate();
	        }
	        // Check if the selected maze type is Refined Maze
	        else if(MazeTypeComboBox.getSelectedItem().equals("Refined Maze"))
			{
				// Generate a refined maze with the given number of rows and columns
				maze.GenerateMaze(rows, columns);
				
				// Check if the selected exit type is Guaranteed Exit
				if(ExitTypeComboBox.getSelectedItem().equals("Guaranteed Exit"))
				{
				    // Generate an exit for the maze
				    maze.GenerateExit();
				}
				else
				{
				    // Generate a random boolean value to decide whether or not to generate an exit for the maze
				    ExitExists = random.nextBoolean();
				    if(ExitExists)maze.GenerateExit();
				}

				// Create a new mouse object and solve the maze
				mouse = new Mouse();
				mouse.SolveMaze();

				// Try to create a new MazePanel with the maze and the mouse's shortest path as arguments
				try {
				    MazeDisplay = new MazePanel(maze.map,mouse.ShortestMazeSolution);
				} catch (Exception e1) {
				    // If an exception occurs, print the stack trace
				    e1.printStackTrace();
				}

				// Add the MazePanel to the current JPanel and revalidate it
				this.add(MazeDisplay);
				this.revalidate();

        		
			}
			else
			{
			    // Check if the selected exit type is Guaranteed Exit
			    if(ExitTypeComboBox.getSelectedItem().equals("Guaranteed Exit"))
			    {
			        // Generate an unrefined maze with the given number of rows and columns and a guaranteed exit
			        do
			        {
			            maze.GenerateUnrefinedMaze(rows, columns);
			            maze.GenerateExit();
			            mouse = new Mouse();
			            mouse.SolveMaze();
			        } while(mouse.ShortestMazeSolution.size()==0);
			    }
			    else
			    {
			        // Generate a random boolean value to decide whether or not to generate an exit for the maze
			        ExitExists = random.nextBoolean();
			        maze.GenerateUnrefinedMaze(rows, columns);
			        if(ExitExists)maze.GenerateExit();
			        mouse = new Mouse();
			        mouse.SolveMaze();
			    }
			

        		
			 // Try to create a new MazePanel with the maze and the mouse's shortest path as arguments
			    try {
			        MazeDisplay = new MazePanel(maze.map,mouse.ShortestMazeSolution);
			    } catch (Exception e1) {
			        // If an exception occurs, print the stack trace
			        e1.printStackTrace();
			    }

			    // Add the MazePanel to the current JPanel and revalidate it
			    this.add(MazeDisplay);
			    this.revalidate();
        		
			}
		}
		
	}
}
/**
 * Creates and sets up the maze panel that displays the maze and the mouse.
 * Uses GridLayout to create a grid of cells, and adds a cell to the panel for each cell in the maze. 
 * Displays mouse navigation when user clicks button.
 */
class MazePanel extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	/**
	 * A static instance of the Random class used for generating random values.
	 */
	static Random random = new Random();
	
	
	/**
	 * An ArrayList of Coords objects representing the path out of the maze.
	 */
	ArrayList<Coords> PathOutOfMaze;
	
	/**
	 * A static instance of the Mouse class used for solving the maze.
	 */
	static Mouse mouse;
	
	
	/**
	 * A JPanel used to display the maze.
	 */
	JPanel MazePanel = new JPanel();
	
	/**
	 * A 2D array of JPanels representing the blocks in the maze.
	 */
	JPanel[][] blocks;
	
	/**
	 * A JButton used to start the game and find the exit.
	 */
	JButton StartGame = new JButton("Find Exit");
	
	/**
	 * A JLabel used to display a disclaimer if the maze is impossible to solve.
	 */
	JLabel MazeImpossibleDisclaimer = new JLabel();
	
	/**
	 * A JLabel used to display the legend for the maze.
	 */
	JLabel Legend = new JLabel("LEGEND: Black = barrier, Red = Mouse Start, Blue = Mouse Path, Green = Exit");
	
	/**
	 * A BufferedImage representing the image of a mouse used in the maze game.
	 */
	BufferedImage myPicture = ImageIO.read(new File("Mouse.png"));
	
	/**
	 * An ImageIcon representing the image of a mouse used in the maze game.
	 */
	ImageIcon MouseIcon = new ImageIcon(myPicture);
	
	/**
	 * A JLabel used to display the image of a mouse in the maze game.
	 */
	JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	

	/**
	 *Constructor for MazePanel class.
	 *Takes a 2D character array representing the maze and an ArrayList of Coords representing the path out of the maze as parameters.
     *Sets up a layout and adds the appropriate color-coded blocks to the panel based on the contents of the maze array.
     *
     *@param maze a 2D character array representing the maze
     *@param PathOutOfMaze an ArrayList of Coords representing the path out of the maze
     *@throws Exception if an error occurs while reading the Mouse image file
	*/
	public MazePanel(char[][] maze, ArrayList<Coords> PathOutOfMaze)throws Exception
	{
		
		
		this.PathOutOfMaze = PathOutOfMaze;
		blocks = new JPanel[maze.length][maze[0].length];
		
		//set up layouts for panels
		GridLayout layout = new GridLayout(maze.length,maze[0].length);//create a gridlayout instance with 3 columns and 3 rows and horizontal and vertical gaps of 50 pixels
		
		BoxLayout layout1 = new BoxLayout(this,BoxLayout.Y_AXIS);//create a new box layout instance where compnents are organized vertically
		BorderLayout layout2 = new BorderLayout();
		
		//Apply Layouts
		MazePanel.setLayout(layout);
		MazePanel.setPreferredSize(getPreferredSize());
		setLayout(layout1);
		
		//Set up action listener
		StartGame.addActionListener(this);
		//Set up maze
		for(int x = 0; x < maze.length; x++)//loop through rows of maze
		{
			for(int y = 0; y < maze[0].length; y++)//loop through columns of maze
			{
				blocks[x][y] = new JPanel();
				
				blocks[x][y].setLayout(layout2);//set to borderlayout
				
				//make barriers black
				if(maze[x][y] == Maze.barrier)
					blocks[x][y].setBackground(Color.BLACK);
				
				//make starting point red
				else if(maze[x][y]==Maze.start)
				{
					blocks[x][y].setBackground(Color.RED);
					blocks[x][y].add(picLabel);
					
				}
				//make exit green
				else if(maze[x][y]==Maze.exit)
					blocks[x][y].setBackground(Color.GREEN);
				
				MazePanel.add(blocks[x][y]);//add blocks to panel
			}
		}
		
		//make button more obvious
		StartGame.setBackground(Color.RED);
		StartGame.setOpaque(true);
		//make maze impossible disclaimer more obious
		MazeImpossibleDisclaimer.setFont(new Font("Serif",Font.BOLD,24));
		MazeImpossibleDisclaimer.setForeground(Color.RED);
		//Add JComponents to MAIN JPANEL
		add(StartGame);
		add(Legend);
		add(MazeImpossibleDisclaimer);
		add(MazePanel);
		
		//make it visible
		setVisible(true);
		
	}
	
	/**
	*This method is s an implementation of the ActionListener interface 
	*and is used to handle button click events.
	*If the command is "Find Exit", it displays the path from the starting 
	*point to the exit point in the maze, if a path exists.
	*
	*@param event an ActionEvent representing the event that occurred
	*/
	public void actionPerformed(ActionEvent event) 
	{
		// Get the action command from the event object
		String command = event.getActionCommand();
		
		// Check if the command is to find the exit of the maze
		if(command.equals("Find Exit"))
		{
			// Check if there is a path out of the maze
			if(PathOutOfMaze.size()>0)
			{
				// Traverse the path out of the maze
				for(int x =0; x < PathOutOfMaze.size(); x++)
				{
					//Check that the block is not the starting block
					if(x>1)
					{
						// Update the block color and remove the image icon from the previous block
						blocks[PathOutOfMaze.get(x-1).x][PathOutOfMaze.get(x-1).y].remove(picLabel);
						blocks[PathOutOfMaze.get(x-1).x][PathOutOfMaze.get(x-1).y].setBackground(Color.BLUE);
						blocks[PathOutOfMaze.get(x-1).x][PathOutOfMaze.get(x-1).y].repaint();//normally background colour updates automatically. However, when the maze becomes really big, java starts lagging and the it might take multiple button clicks to  ge the path to show. This repaint will fix that issue.
						
						
						
						blocks[PathOutOfMaze.get(x).x][PathOutOfMaze.get(x).y].add(picLabel);
						blocks[PathOutOfMaze.get(x).x][PathOutOfMaze.get(x).y].setBackground(Color.GREEN);
						blocks[PathOutOfMaze.get(x).x][PathOutOfMaze.get(x).y].repaint();//normally background colour updates automatically. However, when the maze becomes really big, java starts lagging and the it might take multiple button clicks to  ge the path to show. This repaint will fix that issue.
						
						picLabel.revalidate();
						picLabel.repaint();
						
					}
					
					
				}
				//Update JFrame and Mouse Label
				this.revalidate();
				this.repaint();
				picLabel.revalidate();
				picLabel.repaint();
				
			}
			else
			{
				//Display a message indicating that the maze is impossible
				MazeImpossibleDisclaimer.setText("Maze Is Impossible!");
			}
			
		}
	}

}

/**
 * Maze class represents the maze and contains maze information such as the layout, exit, and start
 * Maze class also generates mazes and extracts mazes from files.
 */
class Maze 
{
	 
	
	 
	/**
	 * A static character variable representing a barrier in the maze.
	 */
	static char barrier = 'B';

	/**
	 * A static character variable representing an open path in the maze.
	 */
	static char openPath = 'O';

	/**
	 * A static character variable representing the exit point in the maze.
	 */
	static char exit = 'X';

	/**
	 * A static character variable representing the starting point in the maze.
	 */
	static char start = 'S';

	/**
	 * A private static Coords variable representing the starting coordinates of the maze.
	 */
	static private Coords StartCoords;

	

	/**
	 * A private static ArrayList of Coords objects representing the maze generation record.
	 */
	static private ArrayList<Coords> MazeGenerationRecord = new ArrayList<Coords>();

	/**
	 * A private static array of Coords objects representing the possible directions to move from a given point. Used during maze generation.
	 */
	static private Coords[] Directions = {new Coords(1,0), new Coords(-1,0), new Coords(0,1), new Coords(0,-1)};

	/**
	 * A two-dimensional character array representing  the maze.
	 */
	static char[][] map;

	/**
	 * A static instance of the Random class used for generating random numbers.
	 */
	static Random random = new Random();

	 /**
	 *Determines if a given cell is within the bounds of the maze map array.
	 *
	 *@param x The x-coordinate of the cell
	 *@param y The y-coordinate of the cell
	 *@return True if the cell is within bounds, false otherwise.
	 */
	static private boolean WithinBounds(int x, int y)//Function Method: Returns true if cell is within bounds of the array
	{
		return(x > 0 && x < map.length - 1 && y > 0 && y < map[0].length - 1);//if the coordinate is within the bounds of the 2d array, execute the block that follows
		
	}
	 
	/**
	 *Checks whether the given cell has already been visited in the maze generation.
	 *This method compares the x and y values of the given cell with each cell in the ArrayList individually.
	 *
	 *@param record The ArrayList containing the cells already visited
	 *@param x The x-coordinate of the cell being checked
	 *@param y The y-coordinate of the cell being checked
	 *@return true if the cell is already in the ArrayList, false otherwise
	 */
	static private boolean contains(ArrayList<Coords> record, int x, int y) //checks wether the given cell has already been visited in the maze generation
	 //ArrayList.contains doesn't work for my class Coords
	 //You must compare the Coords.x and Coords.y individually
	{
	        for (Coords cell : record) //loop through cells in list
	        {
	            if (cell.x == x && cell.y == y) //check if cell is in list
	            {
	            	
	                return true;
	            }
	        }
	        return false;
	}
	
	/**
	 *Generates a maze stored in a 2D using depth first search algorithm.
	 *Uses recursive backtracking to carve paths through the maze.
	 *
	 *@param x the x-coordinate of the current cell being visited
	 *@param y the y-coordinate of the current cell being visited
	 *@return void
	 */

	private static void GenerateMazeUtil(int x, int y) //generates binary maze using depth first search
	{
		/**
		 * The NextX variable represents the x-coordinate of the next cell to be visited during the maze generation process.
		 * Its value is calculated by adding the x-component of the current direction multiplied by 2 to the x-coordinate of the current cell.
		 */
		int NextX;
		 
		/**
		 * The NextY variable represents the y-coordinate of the next cell to be visited during the maze generation process.
		 * Its value is calculated by adding the y-component of the current direction multiplied by 2 to the y-coordinate of the current cell.
		 */
		int NextY;
        
      //shuffle the directions into random order
      	//the shuffling prevents bias in a certain direction during maze generation
        Collections.shuffle(Arrays.asList(Directions));
        
        for (Coords direction : Directions) //loop through all the possible directions: N, E, S, W
        {
        	//calculate coordinates of next cell
            NextX = x + direction.x * 2;
            NextY = y + direction.y * 2;
            
            
            if (WithinBounds(NextX,NextY)) //check if next cell is within bounds of 2D array
            {
                
                // Check if the next cell has not been visited
                if (!contains(MazeGenerationRecord, NextX, NextY)) 
                {
                    
                	//record that cell has been visited
                	MazeGenerationRecord.add(new Coords (NextX, NextY));
                    
                	//Remove barrier between current cell and the next cell
                    map[NextX][NextY] = openPath;
                    map[x + direction.x][y + direction.y] = openPath;
                    
                    // call method recursively
                    GenerateMazeUtil(NextX, NextY);
                }
            }
        }
    }
	/**
	 *Generates an unrefined maze with the specified number of rows and columns. 
	 *The maze is initialized with random blocks or empty spaces. 
	 *The possible blocks are stored in an ArrayList which includes open paths and barriers.
	 *
	 *@param rows the number of rows in the maze
	 *@param cols the number of columns in the maze
	 */

	void GenerateUnrefinedMaze( int rows, int cols)
	{
		map = new char[rows][cols];
		/**
		 * PossibleBlock array list consists of possible block types that can be used to generate the maze. It contains more open paths so that more open paths will be generated.
		 */
		ArrayList<Character> PossibleBlocks = new ArrayList<Character>();
		PossibleBlocks.add(openPath);
		PossibleBlocks.add(openPath);
		PossibleBlocks.add(openPath);
		PossibleBlocks.add(barrier);
		
        Random rand = new Random();
        

        // Initialize the maze with random blocks
        for (int i = 0; i < rows; i++) //loop through rows of maze
        {
            for (int j = 0; j < cols; j++) //loop through columns of maze
            {
                if(i == 0 || j == 0 || i == rows - 1 || j == cols - 1)
                {
                	// Create a border around the maze
                	map[i][j]=barrier;
                }
                else 
                {
                	// Add a random block or empty space to the maze
                    map[i][j] = PossibleBlocks.get(rand.nextInt(PossibleBlocks.size())); // Random block or empty space
                }
            }
        }

       GenerateStart();//generate a starting point
        
        
	  

        
    
	}
	/**
	 *Initializes 2d maze array with barriers and performs set up before calling maze generation algorithm
	 *
	 *@param rows the number of rows in the maze array
	 *@param columns the number of columns in the maze array
	 */
	static void GenerateMaze(int rows, int columns)//Procedure Method: fills the maze array with barriers and occasional open path; this acts as set up for the recursive maze generation algorithm
	{
		
		
		
		
		
		map = new char[rows][columns];
		
		//This arraylist is for the generation process
		//they will never be accessed outside this class
		MazeGenerationRecord.clear();//reset visted record
		
		//Initialize array filled with barriers
		for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = barrier;
            }
        }
		
		
	
		
			
		
		//Add starting coordinates to generation record
		MazeGenerationRecord.add(new Coords(1,1));
		
		//call maze generation algorithm with initial starting coordinates (1,1)
		GenerateMazeUtil(1, 1);
		
		//generate a start position
		GenerateStart();
			
			
		
			
	}
	
	/**
	 *Checks if the given coordinates are valid starting points for the maze.
	 *A valid starting point is an open path surrounded by at least one barrier.
	 *
	 *@param x the x-coordinate of the starting point
	 *@param y the y-coordinate of the starting point
	 *@return true if the given coordinates are valid starting points, false otherwise
	 */
	static boolean StartCheck(int x, int y)//function method: returns true if start position is valid
	{
		if(map[x][y]!=openPath) return false;
		if(map[x-1][y]==barrier && map[x+1][y]==barrier && map[x][y-1] == barrier && map[x][y+1]==barrier) return false;
		else return true;
	}
	
	/**
	 *Generates a starting point for the maze by randomly selecting a location and checking if it is a valid starting point.
	 *A valid starting point is an open path and has at least one adjacent open path.
	 *If a valid starting point is found, the location is marked with a start symbol.
	 *
	 *@throws NoException if the maze does not have any valid starting point
	 */
	static void GenerateStart()
	{
		/**
		 * the x coordinate of the starting point
		 */
		int StartX;
		/**
		 * the y coordinate of the starting point
		 */
		int StartY;
		
	    // loop until a valid start position is found
		do
		{
			// Generate random x and y positions within the map boundaries
			StartX = random.nextInt(map.length);
			StartY = random.nextInt(map[0].length);
		}while(!StartCheck(StartX,StartY));// keep looping until the position passes the StartCheck condition
		
		map[StartX][StartY] = start;// set the position on the map to the 'start' character
	}
	/**
	 *This method checks if maze sides are two blocks thick and returns valid exit sides.
	 *A maze side that is two blocks thick cannot serve as an exit side.
	 *
	 *@return ArrayList of valid exit sides
	 */
	static ArrayList<String> CheckSide()//function method: checks if sides are two blocks thick and returns valid exit sides
	//sometimes maze algorithmn generates a side that 2 blocks thick if the user  given dimensions are awkward
	//if a maze side is two blocks thick it cannot serve as an exit side
	{
		/**
		 * Arraylist of valid exit sides
		 */
		ArrayList<String> Sides = new ArrayList<String>(Arrays.asList("North","South","East","West"));//arraylist  of sides of the maze
		
		/**
		 * Arraylist of blocks in second last column
		 */
		ArrayList<Character> EastSide = new ArrayList<Character>();
		
		/**
		 * Arraylist of blocks in second column
		 */
		ArrayList<Character> WestSide = new ArrayList<Character>();
		
		/**
		 * Arraylist of blocks in second row
		 */
		ArrayList<Character> NorthSide = new ArrayList<Character>();
		
		/**
		 * Arraylist of blocks in second last row
		 */
		ArrayList<Character> SouthSide = new ArrayList<Character>();
		
		//fill east and west side arraylists
		for(int x = 0; x < map.length; x++)
		{
			WestSide.add(map[x][1]);
			EastSide.add(map[x][map[0].length-2]);
		}
		
		//fill north and south side arraylists 
		for(int y = 0; y < map[0].length; y++)
		{
			NorthSide.add(map[1][y]);
			SouthSide.add(map[map.length-2][y]);
		}
		
		
		if(!NorthSide.contains(openPath))//check if north side is double thick
		{
			Sides.remove("North");//remove north from valid exit sides
		}
		if(!SouthSide.contains(openPath))//check if south side is double thick
		{
			Sides.remove("South");//remove south from valid exit sides
		}
		
		if(!EastSide.contains(openPath))//check if east side is double thick
		{
			Sides.remove("East");//remove east from valid exit sides
		}
		if(!WestSide.contains(openPath))//check if west side is double thick
		{
			Sides.remove("West");//remove west from valid exit sides
		}
		
		return Sides;//return arraylist of valid sides
		
		
	}
	/**
	 *This method generates the exit for the maze. It selects a random valid exit side from the CheckSide() method and generates an exit on that side. If the exit is on the north or south side, it is placed on the top or bottom row, respectively. If the exit is on the west or east side, it is placed on the left or right column, respectively.
	 *
	 *@param No parameters
	 *@throws Nothing is thrown.
	 *@return Nothing is returned.
	 */
	static void GenerateExit()//Procedural method: Generates Exit
	{
		/**
		 * x coordinate of exit block
		 */
		int ExitX = 0;
		
		/**
		 * y coordinate of exit block
		 */
		int ExitY = 0;
		
		/**
		 * shift is used to check if adjacent block is an open block
		 */
		int Shift;
		String side;
		ArrayList<String> ExitSides = CheckSide();//arraylist of valid exit sides
		
		side = ExitSides.get(random.nextInt(ExitSides.size()));//choose random valid exit side
		
		
		if(side.equals("North")  || side.equals("South"))//if the chose exit side is north or south, run the following code
		{
			if(side.equals("North"))//check if north side was chosen
			{
				ExitX = 0;//if exit side is north, the exit should be on the top row
				Shift = 1;
			}
			else
			{
				ExitX = map.length-1;//if exit side is south, the exit should be on the bottom row
				Shift = -1;
			}
			do
			{
				
				
				ExitY = random.nextInt(map[0].length);//choose random column for exit to be on
				
				if(map[ExitX+Shift][ExitY] == 'O')//check that exit is possible to access
					map[ExitX][ExitY] = exit;//mark block as exit
				
			}while(map[ExitX][ExitY] != exit);//loop code until a possible exit is generated 
			
		}
		else//if the exit is not on the north or south side, it is on the west or east side
		//execute the block that follows
		{

			if(side.equals("East"))//check if east side was chosen
			{
				ExitY = map[0].length-1;//if exit side is east, the exit should be on the right column
				Shift = -1;
			}
			else
			{
				ExitY = 0;//if exit side is west, the exit should be on the left column
				Shift = 1;
			}
			do
			{
				
				ExitX = random.nextInt(map.length);//choose random row for exit to be on
				
				if(map[ExitX][ExitY+Shift] == 'O')//check that exit is possible to access
					map[ExitX][ExitY] = exit;
				
			}while(map[ExitX][ExitY] != exit);//loop code until a possible exit is generated 
			
		}
	}
	
	/**
	 *Reads maze data from a text file and extracts it into a 2D char array.
	 *
	 *@param file the text file containing the maze data
	 *@throws Exception if an error occurs while reading the file
	 */
	static void ReadFile(File file) throws Exception//procedure method:extracts maze array data from txt files
	{
		/**
		 * Scanner instance that reads data from a file for the maze extraction.
		 */
		Scanner input = new Scanner(file);//new instance of scanner class that reads file
		
		/**
		 * The amount of rows in the maze file.
		 */
		int rows;
		
		/**
		 * The amount of columns in the maze file.
		 */
		int columns;
		
		
		//Input Section
		//Read amount of rows and columns from file
		//Identify the characters that represent the parts of the maze and store them in class variables
		rows = input.nextInt();
		columns = input.nextInt();
		barrier = input.next().charAt(0);
		openPath = input.next().charAt(0);
		start = input.next().charAt(0);
		exit = input.next().charAt(0);
		
		map = new char[rows][columns];//create amount new array with rows and columns as specified in the file
		
		
		for(int x =0; x< map.length;x++)//loop through the rows of the array
		{
			map[x] = input.next().toCharArray();//read entire row of maze, convert it into a 1D array of chars, and store it as a row in the map
			
		}
		input.close();//close the scanner instance
		
	}
	
	
	
	
	

}

/**
 * The mouse class represents the mouse that is trapped in the maze.
 * The mouse class can solve maze and contains information such as the shortest path out of the maze.
 */
class Mouse extends Maze
{
	
	/**
     *ArrayList of type Coords that stores the directions out of the maze.
	 */
	ArrayList<Coords> MazeSolution = new ArrayList<Coords>();//stores directions out of the maze
	
	/**
	 *ArrayList of type Coords that stores the shortest directions out of the maze.
	 */
	ArrayList<Coords> ShortestMazeSolution = new ArrayList<Coords>();
	
	/**
	 * Private integer representing the length of the shortest path out of the maze.
	 */
	private int ShortestPathLength;
	
	/**
	 * Two-dimensional integer array that stores the maze solution. Used during recursive backtracking algorithm to prevent mouse from going back.
	 */
	int[][]MazeSolutionMap;
	
	/**
	 * Private two-dimensional integer array that stores the memoized integer lengths of previously calculated maze paths. The shortest path to every block in maze is stored in the array.
	 */
	private int[][] MemoizationArray;
	
	/**
	 * Private object of type Coords that represents the exit of the maze.
	 */
	private Coords Exit;
	
	/**
	 * Constructor method of Mouse class.
	 * Initializes maze solution map by filling it with 0's that represent unvisited cells.
	 */
	public Mouse()//constructor method
	{
		
		MazeSolutionMap = new int[map.length][map[0].length];//make solution map with same dimensions as map
		
		//Initialize Solution Array
		//0's represent unused cell
		//1's represent visted cells
		for(int x = 0; x< MazeSolutionMap.length; x++)//loop through rows of solution array
		{
			for(int y =0; y < MazeSolutionMap[0].length;y++)//loop through rows of solution array
			{
				MazeSolutionMap[x][y] = 0;
				
			}
		}
	}
	
	/**
	 *Locates the coordinates of the exit cell in the maze by looping through the maze array and comparing each cell to the exit character.
	 *If the exit cell is found, the method creates a new instance of the Coords class to store the coordinates of the exit cell.
	 *
	 *@param Nothing
	 */
	void FindExit()//Procedure Method: Locates coordinates of exit
	{
		
		
		for(int x = 0; x < map.length; x++ )//loop through rows of array
		{
			for(int y = 0; y < map[0].length; y++)//loop through columns of array
			{
				if(map[x][y] == exit)//if start cell, execute block that follows
				{
					Exit = new Coords(x,y);//get coordinates of exit cell
					break;
				}

			}
		}
		
	}
	
	/**
	 *This method locates and returns the coordinates of the starting cell in the maze.
	 *
     *@return Start a Coords object representing the coordinates of the starting cell.
	 */
	Coords FindStart()//Function Method: Locates and returns coordinates of starting cell
	{
		Coords Start = new Coords(-1,-1);
		
		for(int x = 0; x < map.length; x++ )//loop through rows of array
		{
			for(int y = 0; y < map[0].length; y++)//loop through columns of array
			{
				if(map[x][y] == this.start)//if start cell, execute block that follows
				{
					Start = new Coords(x,y);//get coordinates of start cell
					return Start;
				}

			}
		}
		
		return Start;
	
	}
	
	/**
	 *Checks if the given cell is unsafe for the mouse to travel to.
	 *
	 *@param x The row index of the cell being checked.
	 *@param y The column index of the cell being checked.
	 *@return True if the cell is unsafe (out of bounds, barrier, or already visited), false otherwise.
	 */
	boolean CellCheck(int x, int y)//Function Method: Returns true if cell is unsafe for mouse to travel to
	{
		
		return(x < 0 || x >= map.length || y < 0 || y >= map[0].length || map[x][y] == barrier || MazeSolutionMap[x][y] ==1);//return true if cell is unsafe
	}
	
	/**
	*Sets up the initial arguments of the recursive method to solve the maze.
	*Finds the exit, initializes shortest path length to maximum integer value, creates a memoization array to store previously calculated paths.
	*Calls SolveMazeUtil method to find the shortest path from starting cell to exit.
	*
	*@param Nothing
	*/
	void SolveMaze()//Procedure Method: sets up the initial arguement of the recrusive method
	{
		// Find the coordinates of exit
		this.FindExit();
		
		// Initialize shortest path length to maximum integer value 
		ShortestPathLength = Integer.MAX_VALUE;
		
		//Initialize Memoization Array
		MemoizationArray = new int[map.length][map[0].length];
		for(int x =0; x < MemoizationArray.length;x++)//loop through rows
		{
			for(int y =0; y < MemoizationArray[0].length;y++)//loop through columns
			{
				MemoizationArray[x][y]=0;//
			}
		}
		// If exit is not found in the maze, do nothing
		if(Exit==null)
		{
			//do nothing
		}
		else
		{
			//call recursive algorithm to find shortest path out of maze
			SolveMazeUtil(this.FindStart().x,this.FindStart().y, 0);
		}
		
		
	}
	/**
	 *This method is a recursive procedure that finds the shortest path out of a maze.
	 *It uses a depth-first search algorithm that explores all possible paths from the starting cell until it reaches the exit.
	 *If the cell is safe, it adds the current coordinates to the MazeSolution array and marks it as visited in the MazeSolutionMap.
	 *If the current cell is the exit cell, it compares the length of the current solution path with the length of the shortest solution path found so far.
	 *If the current path is shorter, it updates the ShortestMazeSolution array with the current path and sets the ShortestPathLength to the length of the current path.
	 *Method also records length of shortest path and uses memoization to improve efficiency and reduce redundant recursive calls.
	 *
	 *@param x the x-coordinate of the current cell
	 *@param y the y-coordinate of the current cell
	 *@param PathLength the length of the current solution path
	 *@return void
	 */
	void SolveMazeUtil(int x, int y, int PathLength)//procedure method: finds shortest path out of the maze
	{
		// If the current cell is unsafe to travel to, return without adding it to the solution path
        if (CellCheck(x,y))//base case
        {
            return;//return without recursive call
        }
        
        // Add the current cell to the solution path and mark it as visited
        MazeSolution.add(new Coords(x,y));
        MazeSolutionMap[x][y] = 1;
        
     
        //Check if current cell is the exit cell
        if (x == Exit.x && y == Exit.y) //base case
        {
        	//Check if current solution path is the shortest one found so far
            if (ShortestMazeSolution.isEmpty() || PathLength < ShortestMazeSolution.size()) 
            {
            	// If the current solution path is the shortest one found so far, 
            	//update the shortest path and its length
            	ShortestMazeSolution.clear();
            	ShortestMazeSolution.addAll(MazeSolution);
                ShortestPathLength= ShortestMazeSolution.size();
            }
            
            //base case as method runs without recursive call
        } 
        
        // If the current cell is not the exit cell, and the current path is shorter than the shortest path found so far, explore the neighboring cells
        else if(PathLength < ShortestPathLength)//recursive case
        {
        	// If the current cell has not been memoized yet, or the current path is shorter than the previously memoized path, explore its neighboring cells
        	if(MemoizationArray[x][y]==0 || PathLength < MemoizationArray [x][y])
        	{
        		MemoizationArray[x][y] = PathLength;
	            SolveMazeUtil(x + 1, y, PathLength + 1);//recursive call
	            SolveMazeUtil(x - 1, y, PathLength + 1);//recursive call
	            SolveMazeUtil(x, y + 1,PathLength + 1);//recursive call
	            SolveMazeUtil(x, y - 1, PathLength + 1);//recursive
        	}
        }
        
        //BACKTRACK
        //Remove (x,y) from solution path
        MazeSolution.remove(MazeSolution.size() - 1);
        MazeSolutionMap[x][y] = 0;
    }
}

/**
 *The Coords class represents a pair of coordinates with an x and y value. 
 *This class is used to keep track of coordinate pairs in the maze solving and generation algorithms.
 */
class Coords//coords class helps keep track of coordinate pairs
{
    /** 
	 *The x-coordinate of the coordinate pair. 
	 */
	int x;
	
	/**
	 *The y-coordinate of the coordinate pair.
	 */
    int y;
  
    /**
     * Constructor method.
     * Instance of new class will have given x and y coordinates
     * 
     * @param x The x-coordinate of the coordinate pair
     * @param y The y-coordinat of the coordinate pair
     */
    public Coords(int x, int y)//constructor method
    {
    	setCoords(x, y);
    } 
    
    /**
	 *Sets the value of the x and y coordinate pair.
	 *
     *@param x The x-coordinate of the coordinate pair.
     *@param y The y-coordinate of the coordinate pair.
     */
    public void setCoords(int x, int y)//procedure method: function sets the value of the x and y coordinate pair
    {
	    this.x = x;
	    this.y = y;
   
    }
}
