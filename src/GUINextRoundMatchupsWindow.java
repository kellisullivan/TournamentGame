import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;


/**
 * This method is creating the frame to show the next round of matchups for our tournament
 *depending on the number of teams that are left in the tournament at this point it displays
 *the number of teams.
 * @author Kelli
 *@author Emma
 *@author Nicole
 */
public class GUINextRoundMatchupsWindow {

	private JFrame frame;
	private static final int FRAME_WIDTH = 1500;
	private static final int FRAME_HEIGHT = 1000;
	private ActionListener nextListener;
	
	private Tournament tournament;
	private ArrayList<Team> nextRoundTeams;

	/**
	 * Constructor is going to take in all the information that we need for this tournament and then
	 *take in an arraylist of the teams that are in the current round
	 @param tournament - current tournament

	 *@param arraylist of teams - teams that are currently in this round

	 *@throws IOException - makes sure the jpg files are in the correct folder to read them in
	 */
	public GUINextRoundMatchupsWindow(Tournament tournament, ArrayList<Team> nextRoundTeams) throws IOException {
		this.tournament = tournament;
		this.nextRoundTeams = nextRoundTeams;
		initialize();
	}

	/**
	 * Initialize the contents of the frame that will display the teams that are
	 * currently in the tournament still and let the user know who is going up 
	 *against each other in the next round.
	 */
	private void initialize() throws IOException {
		//create the basic frame
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
			

		//creates a label that states the current tournament bracket
		JLabel lblNewLabel = new JLabel("Tournament Bracket");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setBounds(491, 118, 450, 54);
		frame.getContentPane().add(lblNewLabel);
		
		//get the name of the first team in the round
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setText(nextRoundTeams.get(0).getName());
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(150, 298, 327, 48);
		frame.getContentPane().add(lblNewLabel_1);
		frame.setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//puts in the topbar image
		String topBar = "TopLine.jpg";
	    File topbar_file = new File(topBar);
	    BufferedImage top_bar = ImageIO.read(topbar_file);
	    JLabel top_graphic = new JLabel(new ImageIcon(top_bar));
	    top_graphic.setBounds(100, 0, 1200, 100);
	    frame.getContentPane().add(top_graphic);
	    
	    //states the competing
	    JLabel lblNameYourTeam = new JLabel("Competing Teams:");
	    lblNameYourTeam.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
	    lblNameYourTeam.setForeground(Color.ORANGE);
	    lblNameYourTeam.setBounds(531, 184, 373, 54);
	    frame.getContentPane().add(lblNameYourTeam);
	    
	    //versus 
	    JLabel lblVs = new JLabel("VS.");
	    lblVs.setForeground(Color.ORANGE);
	    lblVs.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
	    lblVs.setBounds(288, 382, 67, 48);
	    frame.getContentPane().add(lblVs);
	    
	    //get the second team that is competing
	    JLabel label = new JLabel("");
	    label.setText(nextRoundTeams.get(1).getName());
	    label.setForeground(Color.WHITE);
	    label.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
	    label.setBounds(150, 455, 327, 48);
	    frame.getContentPane().add(label);
	    
	    //continues on if there are more than two teams
	    if (nextRoundTeams.size() > 2) {
	    	//get the third team in the tournament
	    	 JLabel label_1 = new JLabel((String) null);
	 	    label_1.setText(nextRoundTeams.get(2).getName());
	 	    label_1.setForeground(Color.WHITE);
	 	    label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
	 	    label_1.setBounds(150, 569, 327, 48);
	 	    frame.getContentPane().add(label_1);
	 	    
	 	    //versus
	 	    JLabel label_3 = new JLabel("VS.");
		    label_3.setForeground(Color.ORANGE);
		    label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		    label_3.setBounds(288, 635, 67, 48);
		    frame.getContentPane().add(label_3);
	 	    
		    //get fourth team in the tournament
	 	    JLabel label_2 = new JLabel("");
	 	    label_2.setText(nextRoundTeams.get(3).getName());
	 	    label_2.setForeground(Color.WHITE);
	 	    label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
	 	    label_2.setBounds(150, 695, 327, 48);
	 	    frame.getContentPane().add(label_2);
	    }
	    
	    //if there are more than four teams left in the round
	    if (nextRoundTeams.size() > 4) { 
	    	//get the fifth team
	    	JLabel label_4 = new JLabel((String) null);
	 	    label_4.setText(nextRoundTeams.get(4).getName());
	 	    label_4.setForeground(Color.WHITE);
	 	    label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
	 	    label_4.setBounds(942, 310, 327, 48);
	 	    frame.getContentPane().add(label_4);
	 	    
	 	    //versus
	 	   JLabel label_8 = new JLabel("VS.");
		    label_8.setForeground(Color.ORANGE);
		    label_8.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		    label_8.setBounds(1077, 647, 67, 48);
		    frame.getContentPane().add(label_8);
	 	    
		    //get the sixth team
	 	    JLabel label_5 = new JLabel((String) null);
	 	    label_5.setText(nextRoundTeams.get(5).getName());
	 	    label_5.setForeground(Color.WHITE);
	 	    label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
	 	    label_5.setBounds(942, 455, 327, 48);
	 	    frame.getContentPane().add(label_5);
	    }
	   //if there are more than six teams in the round
	    if (nextRoundTeams.size() > 6) { 
	    	//get the seventh team
	    	JLabel label_6 = new JLabel((String) null);
		    label_6.setText(nextRoundTeams.get(6).getName());
		    label_6.setForeground(Color.WHITE);
		    label_6.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		    label_6.setBounds(942, 554, 327, 48);
		    frame.getContentPane().add(label_6);
		    
		    //versus
		    JLabel label_7 = new JLabel((String) null);
		    label_7.setText(nextRoundTeams.get(7).getName());
		    label_7.setForeground(Color.WHITE);
		    label_7.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		    label_7.setBounds(942, 695, 327, 48);
		    frame.getContentPane().add(label_7);
		    
		    //get the eighth and final team
		    JLabel label_9 = new JLabel("VS.");
		    label_9.setForeground(Color.ORANGE);
		    label_9.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		    label_9.setBounds(1077, 395, 67, 48);
		    frame.getContentPane().add(label_9);
		    
	    }
	    //label the option to continue the tournament
	    JLabel lblTournament = new JLabel("Continue Tournament");
	    lblTournament.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
	    lblTournament.setForeground(Color.ORANGE);
	    lblTournament.setBounds(538, 403, 378, 27);
	    frame.getContentPane().add(lblTournament);
	    
	    //button that will take the user to the next round
	    JButton btnNext = new JButton("Next Round");
	    nextListener = new NextButton();
	    btnNext.addActionListener(nextListener);
	    btnNext.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
	    btnNext.setBounds(633, 459, 167, 48);
	    frame.getContentPane().add(btnNext);
	}
	/**
	 * returns the JFrame that is being built in this class
	 * @return frame - current JFrame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Class that extends JFrame and implements the ActionListener
	 * to produce actions when the JButton denoting "Next" is pressed
	 * in our see tournament round matchups information JFrame
	 *@author Kelli
	 *@author Emma
	 *@author Nicole
	 */
	class NextButton extends JFrame implements ActionListener {
		
		private static final long serialVersionUID = 1L;

		/**
		 * Constructor is empty
		 */
		public NextButton() {
			
		}
		
		/**
		 * When a user presses the button to continue the tournament and get the next round
		 * this method creates the next window based upon how many teams are left in the current round
		 * if only two are left then it takes the user to tournament winner screen if not then 
		 * it simply goes back through the rounds.
		 */
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			
			//open next window
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						if (nextRoundTeams.size() == 2) {
							GUITournamentWinnerWindow tournamentWinnerWindow = new GUITournamentWinnerWindow(tournament, nextRoundTeams);
							tournamentWinnerWindow.getFrame().setVisible(true);
						}
						else {
							GUIDisplayNextRoundWindow displayNextRoundWindow = new GUIDisplayNextRoundWindow(tournament, nextRoundTeams);
							displayNextRoundWindow.getFrame().setVisible(true);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
	}
}

