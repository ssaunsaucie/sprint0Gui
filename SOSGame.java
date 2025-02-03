package sosGame_project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SOSGame extends JFrame 
{
	private JPanel topPanel;			// references top panel
	private JPanel middlePanel;			//references middle panel
	private JPanel bottomPanel;			// references bottom panel
	private JPanel bluePlayerPanel;		// references blue player panel
	private JPanel redPlayerPanel;		// references red player panel
	private JLabel boardSizeLabel;		// references board size label
	private JLabel redPlayerLabel;		// references red player
	private JLabel sosGame;				// references title of game
	private JLabel bluePlayerLabel;		// references blue player
	private JTextField boardSizeText;	// references text field for user input
	private JRadioButton redPlayerS;	// references red player's S token
	private JRadioButton redPlayerO;	// references red player's O token
	private JRadioButton bluePlayerS;	// references blue player's S token
	private JRadioButton bluePlayerO;	// references blue player's O token
	private JRadioButton redHuman, blueHuman;	//references human radio button for both players
	private JRadioButton redComputer, blueComputer;	// references computer RB for both players
	private ButtonGroup redTokenGroup;		// group for red player token radio buttons
	private ButtonGroup blueTokenGroup;		// group for blue player token radio buttons
	private ButtonGroup blueOptionGroup;	// group for blue player human or computer options
	private ButtonGroup redOptionGroup;		// group for red player human or computer options
	private JButton replayButton, newGameButton;	// references replay and new game buttons
	private JCheckBox recordGame;		// references check box for record
	private final int WINDOW_WIDTH = 800;	// window width
	private final int WINDOW_HEIGHT = 600;	// window height
	private JPanel gridPanel;		// references panel for the grid layout
	private int boardSize = 3;		// references automatic starting size
	
	// Constructor
	
	public SOSGame()
	{
		// set the title of the game
		setTitle("SOS Simple Game");
		
		// set the size of the window
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		// close window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		// set layout for the game
		setLayout(new BorderLayout());
		
		// initialize panels
		createTopPanel();
		createMiddlePanel();
		createBottomPanel();
		
		// make the frame visible
		setVisible(true);
		
		// initial grid setup
		updateGrid();
		
	}
		
	// create top panel and components for top section of game and add to top panel
	private void createTopPanel() 
	{
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));	// sets top panel components in the center
		sosGame = new JLabel("SOS Simple Game");
		boardSizeLabel = new JLabel("Board Size");
		boardSizeText = new JTextField("3", 3);		// creates with a width of 3 and the default 3
		
		// add an action listener to update the grid from user input
		boardSizeText.addActionListener(new TextFieldListener());
		
		// add components to top panel
		topPanel.add(sosGame);
		topPanel.add(boardSizeLabel);
		topPanel.add(boardSizeText);
		add(topPanel, BorderLayout.NORTH);	// positions panel to be located on top of window
	}
		
	// create middle panel where players and grid will be located 
	private void createMiddlePanel()
	{
		middlePanel = new JPanel(new BorderLayout());
		
		// create blue player panel
		bluePlayerPanel = new JPanel();
		bluePlayerPanel.setLayout(new BoxLayout(bluePlayerPanel, BoxLayout.Y_AXIS));	//structures blue player panel to be 
		setupBluePlayerPanel();															// displayed vertically and from top to bottom
		
		
		// create red player panel 
		redPlayerPanel = new JPanel();
		redPlayerPanel.setLayout(new BoxLayout(redPlayerPanel, BoxLayout.Y_AXIS));
		setupRedPlayerPanel();		// allows for better organization for creating and adding components
		
		// create grid panel
		gridPanel = new JPanel();
		updateGrid();		// must create this to update grid based on user input
		
		// add panels red and blue player panels to the middle panel
		middlePanel.add(bluePlayerPanel, BorderLayout.WEST);		// blue player options on the left
		middlePanel.add(redPlayerPanel, BorderLayout.EAST);			// red player options on the right
		middlePanel.add(gridPanel, BorderLayout.CENTER);			// grid is located in the center
		add(middlePanel, BorderLayout.CENTER);						// adding middle panel to the center
	}
	
	// set up the blue player panel and create components and add them to panel
	private void setupBluePlayerPanel()
	{
		bluePlayerLabel = new JLabel("Blue Player");
		blueHuman = new JRadioButton("Human", true);	// automatically chooses this option
		blueComputer = new JRadioButton("Computer");
		bluePlayerS = new JRadioButton("S", true);	// automatically chooses this token
		bluePlayerO = new JRadioButton("O");
				
		// add active listener for radio buttons
		blueHuman.addActionListener(new RadioButtonListener());
		blueComputer.addActionListener(new RadioButtonListener());
		bluePlayerS.addActionListener(new RadioButtonListener());
		bluePlayerO.addActionListener(new RadioButtonListener());
		
		// group the blue player radio button options 
		blueOptionGroup = new ButtonGroup();
		blueOptionGroup.add(blueHuman);
		blueOptionGroup.add(blueComputer);
		
		// group the blue player radio button tokens
		blueTokenGroup = new ButtonGroup();
		blueTokenGroup.add(bluePlayerS);
		blueTokenGroup.add(bluePlayerO);
		
		// add components to the blue player panel for organization
		bluePlayerPanel.add(bluePlayerLabel);
		bluePlayerPanel.add(blueHuman);
		bluePlayerPanel.add(blueComputer);
		bluePlayerPanel.add(Box.createVerticalStrut(20));	// creates space (20 characters) for tokens under objects
		bluePlayerPanel.add(bluePlayerS);
		bluePlayerPanel.add(bluePlayerO);
	}
		
	// set up red player panel and create components and add them to red player panel
	private void setupRedPlayerPanel()
	{
		redPlayerLabel = new JLabel("Red Player");
		redHuman = new JRadioButton("Human", true);	// automatically sets option for user
		redComputer = new JRadioButton("Computer");
		redPlayerS = new JRadioButton("S", true);	// automatically sets token for user
		redPlayerO = new JRadioButton("O");
		
		// add active listeners for the red player radio buttons
		redHuman.addActionListener(new RadioButtonListener());
		redComputer.addActionListener(new RadioButtonListener());
		redPlayerS.addActionListener(new RadioButtonListener());
		redPlayerO.addActionListener(new RadioButtonListener());
		
		// group the red player radio button options
		redOptionGroup = new ButtonGroup();
		redOptionGroup.add(redHuman);
		redOptionGroup.add(redComputer);
		
		// group the red player radio button tokens
		redTokenGroup = new ButtonGroup();
		redTokenGroup.add(redPlayerS);
		redTokenGroup.add(redPlayerO);
		
		// add components to the red player panel for organization
		redPlayerPanel.add(redPlayerLabel);
		redPlayerPanel.add(redHuman);
		redPlayerPanel.add(redComputer);
		redPlayerPanel.add(Box.createVerticalStrut(20));	// creates space for tokens under objects
		redPlayerPanel.add(redPlayerS);
		redPlayerPanel.add(redPlayerO);
		
	}
	
	// create bottom panel and components and add them to the bottom panel
	private void createBottomPanel()
	{
		bottomPanel = new JPanel(new FlowLayout());
		recordGame = new JCheckBox("Record Game");
		replayButton = new JButton("Replay");
		newGameButton = new JButton("New Game");
		
		// add active listeners for check box and buttons 
		recordGame.addActionListener(new CheckBoxListener());
		replayButton.addActionListener(new ButtonListener());
		newGameButton.addActionListener(new ButtonListener());
		
		// add components to bottom panel
		bottomPanel.add(recordGame);
		bottomPanel.add(replayButton);
		bottomPanel.add(newGameButton);
		add(bottomPanel, BorderLayout.SOUTH);	// adding panel which is to be located at the bottom of window
	}
	
	// update grid based on user input for the board size and create grid to 
	// be interactive using buttons for game
	private void updateGrid()
	{
		gridPanel.removeAll(); 		// clears the previous grid layout
		gridPanel.setLayout(new GridLayout(boardSize, boardSize));	// sets a new layout, rows and columns are the same size
		
		// create interactive grid by adding buttons 
		for (int i = 0; i < boardSize * boardSize; i++)		// loop for cell size 
		{
			JButton gridButton = new JButton();		// empty button for each cell to detect a token
			gridButton.setPreferredSize(new Dimension(60, 60));		// set the size of the buttons/cells to be uniform 60px
			gridButton.addActionListener(new GridButtonListener());		// add active listener to detect user interaction
			gridPanel.add(gridButton);		// add button to the grid panel
		}
		
		gridPanel.revalidate();		// update the grid layout after changes
		gridPanel.repaint();		// draw the new grid or refresh the layout 
	}
	
	//private inner listener classes that handles the event when the
	// user clicks the check box, inserts text, or clicks a button
	
	// listener class for the radio button components 
	private class RadioButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JRadioButton source = (JRadioButton) e.getSource();		// creating method to get the radio button when clicked
				
			System.out.println("Radio button" + source.getText());		// after getting clicked, prints "radio button" to console
		}
	}
	
	// listener class for the button components 
	private class ButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == newGameButton)		// if user selects new game button
			{
				System.out.println("New Game");		// the console will print out "new game"
			}
			else if (e.getSource() == replayButton)		// otherwise, if user selects replay button
			{
				System.out.println("Replay");		// the console will print out "replay"
			}
				
		}

	}
	
	// Listener class for the check box component
	private class CheckBoxListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Record game" + recordGame.isSelected());	// prints to console "record game" when box is selected
		}
	}
	
	// listener class for board size text field
	private class TextFieldListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try 
			{
				int newSize = Integer.parseInt(boardSizeText.getText());	// must translate string to an integer and create container for user input
				if (newSize >= 3 && newSize <= 11)		// setting reasonable restrictions on size of game board
				{
					boardSize = newSize;	// board size is what user inputs
					updateGrid();		// must update grid after correct (3-11) number is input
				}
			
				else 
				{
					JOptionPane.showMessageDialog(null, 
							"Please enter a number between 3 and 11");	// user must input number between 3 and 11
					boardSizeText.setText(String.valueOf(boardSize));	// reset the text field to current board size ensuring valid input
				}
			}
			
			catch (NumberFormatException exception)
			{
				JOptionPane.showMessageDialog(null, "Please enter a vaild number");	// user must input valid integer, not strings or characters
				boardSizeText.setText(String.valueOf(boardSize));
			}
		}		
	}
	
	// listener class for grid cells
	private class GridButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton) e.getSource();	// gets the button that is selected
			System.out.println("Grid button");		// prints "Grid button" to console
			
		}
	}
	
	public static void main(String[] args)
	{
		new SOSGame();		// main class executes game 
	}

}
