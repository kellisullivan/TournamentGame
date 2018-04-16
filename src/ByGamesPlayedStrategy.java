import java.util.ArrayList;

public class ByGamesPlayedStrategy implements TournamentWinnerStrategy {

	public Team determineWinner(Team firstTeam, Team secondTeam) {
		int firstTeamGamesPlayedTotal = 0;
		int secondTeamGamesPlayedTotal = 0;
		ArrayList<Players> firstTeamMembers = firstTeam.getTeamMembersList();
		ArrayList<Players> secondTeamMembers = secondTeam.getTeamMembersList();
		for (int i = 0; i < firstTeam.getTeamSize(); i++) {
			firstTeamGamesPlayedTotal += firstTeamMembers.get(i).getGamesPlayed();
			secondTeamGamesPlayedTotal += secondTeamMembers.get(i).getGamesPlayed();
		}
		firstTeam.setLastRoundAverage(firstTeamGamesPlayedTotal/5);
		secondTeam.setLastRoundAverage(secondTeamGamesPlayedTotal/5);
		return RunTournament.determineWinningAverage(firstTeam, secondTeam);
	}
	
	public String getName() {
		return "Greatest Average Games Played Winner";
	}

}