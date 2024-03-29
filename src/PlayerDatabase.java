
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;

/**
 * This class will create a database of athletes/players and their 
 * statistics to be used by the tournament strategies to determine
 * the winner of the tournament. Specific statistics and players
 * can be altered by any potential administrator of the game to fit
 * their desired tournament. 
 * For this project, Colorado College athletes were used. Their statistics and 
 * information was gathered from the Colorado College Athletics page.
 * @author Nicole
 * @author Kelli
 * @author Emma
 *
 */
public class PlayerDatabase {
	
	public static final String PORT_NUMBER = "3306";
	
	private static ArrayList<Player> players = new ArrayList<Player>();
	
	/**
	 * Constructor is empty.
	 */
	public PlayerDatabase() {
		
	}
	
	/**
	 * This method creates our database using my SQL 
	 */
	private static void createDatabase() {
		try (
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/?user=root&password=root");
			Statement statement = conn.createStatement();
		) {		
			//create the database
			String database = "create database if not exists AthletePlayers";
			statement.execute(database);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Creates the athlete table containing the name, gender, sport, height, games played
	 * team wins, team loss and class years of athletes
	 */
	private static void createTable() {
		try (
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:" + PORT_NUMBER + 
					"/AthletePlayers?user=root&password=root"); // MySQL

			Statement statement = conn.createStatement();
		) {
			//create a table of all the players
			String playerTable = "create table if not exists Players ( " +
				"Name varchar(50), " +
				"Sport varchar(50), " +
				"Gender varchar(50), " +
				"Height int, " +
				"GamesPlayed int, " +
				"TeamWins int, " +
				"TeamLosses int, " +
				"ClassYear int, " +
				"primary key (Name));";
			statement.execute(playerTable);
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * This method add players to the table we have created
	 * in our new database by reading in the file that contains
	 * all the information about our athletes
	 */
	private static void addPlayersToDatabase() {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:" + PORT_NUMBER + 
				"/AthletePlayers?user=root&password=root");
			Statement statement = conn.createStatement();
		) {
			File athletesFile = new File("athletes.txt");
			int countInserted = 0;
			//add all of the players from the file to the table
			try {
				Scanner scan = new Scanner(athletesFile);
				while (scan.hasNextLine()) {
					String currentAthlete = scan.nextLine();
					String insertPlayer = "insert ignore into Players (Name, Sport, Gender, Height, GamesPlayed, TeamWins, TeamLosses, ClassYear) value "
							+ "("+currentAthlete+")";
					countInserted += statement.executeUpdate(insertPlayer);
				}
			} 
			catch (FileNotFoundException exception) {
				System.out.println(exception);
			}
			if (countInserted > 0) {
				System.out.println(countInserted + " players inserted.\n");
			}
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
		}	
	}
	/**
	 * Gets all the players from our database and their attributes
	 * to store them in an arraylist of players
	 */
	private static void getPlayersFromDatabase() {
		try (
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:" + PORT_NUMBER + 
					"/AthletePlayers?user=root&password=root");
				Statement statement = conn.createStatement();
		) {
			String getEverything = "select Name, Sport, Gender, Height, GamesPlayed, TeamWins, TeamLosses, ClassYear from Players";
	
			ResultSet allPlayers;
			allPlayers = statement.executeQuery(getEverything);
	
			while (allPlayers.next()) {
				String name = allPlayers.getString("Name");
				String sport = allPlayers.getString("Sport");
				String gender = allPlayers.getString("Gender");
				int heightInches = allPlayers.getInt("Height");
				int gamesPlayed = allPlayers.getInt("GamesPlayed");
				int wins = allPlayers.getInt("TeamWins");
				int losses = allPlayers.getInt("TeamLosses");
				int classYear = allPlayers.getInt("ClassYear");
				players.add(new Player(name, sport, gender, heightInches, gamesPlayed, wins, losses, classYear));
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}	
	}
	
	/**
	 * Gets all of the players entered into the database
	 * @return list of players
	 */
	public static ArrayList<Player> getPlayersList() {
		return players;
	}
	
	/**
	 * Finds a particular player based on their primary key - their name
	 * @param playerName - name of the player we want to find
	 * @return player - all the attributes of the player
	 */
	public static Player findPlayer(String playerName) {
		for (Player player : players) {
			if (playerName.equalsIgnoreCase(player.getName())) {
				return player;
			}
		}
		return null;
	}
	
	/**
	 * Gets the number of players in the database
	 * @return number of players
	 */
	public static int getNumPlayers() {
		return players.size();
	}
	
	
	/**
	 * 
	 * Creates the database and table of athletes and 
	 * their information. This information will be used by the 
	 * tournament strategies.
	 */
	public static void generateDatabase() {
		createDatabase();
		createTable();
		addPlayersToDatabase();		
		getPlayersFromDatabase();
	}

	
}
