/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nim;

import nim.Exceptions.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author jrsullins
 */
public class NimApp extends JFrame implements ActionListener {
    
    private static final int ROWS = 3;// Number of rows in game, will be used to make sure rendering happens correctly, among other things. !MUST be updated if using different amount of rows!
    private JTextField[] gameFields;  // Where sticks for each row shown
    private JTextField rowField;      // Where player enters row to select
    private JTextField sticksField;   // Where player enters sticks to take
    private JButton playButton;       // Pressed to take sticks
    private JButton AIButton;         // Pressed to make AI's move
    
    private NimGame nim;
    
    public NimApp() {                                                           // Set up the game window
        // Build the fields for the game play 
        rowField = new JTextField(5);
        sticksField = new JTextField(5);
        playButton = new JButton("PLAYER");
        AIButton = new JButton("COMPUTER");
        playButton.addActionListener(this);
        AIButton.addActionListener(this);
        AIButton.setEnabled(false);
        
        // Create the layout
        JPanel mainPanel = new JPanel(new BorderLayout());                      // main holder
        getContentPane().add(mainPanel);
        
        JPanel sticksPanel = new JPanel(new GridLayout(3, 1));                  // holds the sticks
        mainPanel.add(sticksPanel, BorderLayout.EAST);
        
        JPanel playPanel = new JPanel(new GridLayout(3, 2));                    // holds row, sticks input and also player/computer buttons
        mainPanel.add(playPanel, BorderLayout.CENTER);
        
        // Add the fields to the play panel
        playPanel.add(new JLabel("Row: ", JLabel.RIGHT));                       
        playPanel.add(rowField);
        playPanel.add(new JLabel("Sticks: ", JLabel.RIGHT));
        playPanel.add(sticksField);
        playPanel.add(playButton);
        playPanel.add(AIButton);
        
        // Build the array of textfields to display the sticks
        gameFields = new JTextField[ROWS];
        for (int i = 0; i < ROWS; i++) {
            gameFields[i] = new JTextField(10);
            gameFields[i].setEditable(false);
            sticksPanel.add(gameFields[i]);
        }
        // put the window on the screen
        setSize(350, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 
        try {
            nim = new NimGame(new int[]{3, 5, 7});                              // create a 3 row nimgame
            draw();
        } catch (TooManyInitSticksInRowException ex) {                          // If more than 10 sticks in one row - throw an exception due to gui not showing more than 10
            JOptionPane.showMessageDialog(null, "Can't show all sticks in certain row. Please contact developer.");
        } 
    }

    private void draw() {                                                       // Utility function to redraw game
        try {
            for (int row = 0; row < ROWS; row++) {
                String sticks = "";

                    for (int j = 0; j < nim.getRow(row); j++) {
                        sticks += "|   ";                                       // Sticks "graphic"
                    }
                gameFields[row].setText(sticks);
            }
        } catch (NoSuchRowException ex) {                                       // make sure ROWS matches up with the numberOfRows in NimGame for rendering purposes
                JOptionPane.showMessageDialog(null, "Rendering Error based on rows, Please contact developer.");
        }
        rowField.setText("");
        sticksField.setText("");
    }
    
    public void actionPerformed(ActionEvent e) {                                // Game play
        
        // Player move
        if (e.getSource() == playButton) {
            

            
            // Play that move and also check if game is over.
            try {
                int row = Integer.parseInt(rowField.getText())-1;
                int sticks = Integer.parseInt(sticksField.getText());
                
                nim.play(row, sticks);
                
                // Redisplay the board and enable the AI button
                draw();
                playButton.setEnabled(false);
                AIButton.setEnabled(true);
                
                // Determine whether the game is over
                if (nim.isOver()) {
                    JOptionPane.showMessageDialog(null, "You win!");
                    playButton.setEnabled(false);
                }
            // Error checking...
            } catch (NoSuchRowException ex) {                                   // Make sure user input 1-ROWS
                JOptionPane.showMessageDialog(null, "Row must be between 1 and " + ROWS + "!");
            } catch (IllegalSticksException ex) {                               // Make sure user is taking valid amount of sticks
                JOptionPane.showMessageDialog(null, "Sticks must be between 1 and " + ROWS + "!");
            } catch (NotEnoughSticksException ex) {                             // Make sure user isnt taking too many sticks from a row
                JOptionPane.showMessageDialog(null, "Not enough sticks in that row!");
            } catch (NumberFormatException ex) {                                // Makesure we are getting ints
                JOptionPane.showMessageDialog(null, "Inputs MUST be an number!");
            } catch (NullPointerException ex) {                                 // GUI rendering error
                JOptionPane.showMessageDialog(null, "Can't play game if all sticks cant be shown. Please contact developer.");
            }

        }
        
        // Computer move
        if (e.getSource() == AIButton) {
            
            // Determine computer move
            nim.AIMove();
            
            // Redraw board
            draw();
            AIButton.setEnabled(false);
            playButton.setEnabled(true);
            
            // Is the game over?
            if (nim.isOver()) {
                JOptionPane.showMessageDialog(null, "You win!");
                playButton.setEnabled(false);
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {                                    
        
        NimApp a = new NimApp();
    }
}
