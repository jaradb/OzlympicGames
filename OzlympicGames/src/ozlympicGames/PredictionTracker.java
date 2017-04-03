package ozlympicGames;

import games.Game;
import persons.Athlete;

public class PredictionTracker {

	private int totalCorrectPredictions;
	private Athlete predictedWinner;
	private String predictionReport;

	public PredictionTracker() {
		predictionReport = new String();
	}

	public int getTotalCorrectPredictions() {
		return totalCorrectPredictions;
	}

	// Add to the record the correct prediction, who was predicted and in which
	// game/round.
	public void recordWinningPrediction(Game game) {
		predictionReport += "You correctly predicted " + predictedWinner.getName() + " would win in "
				+ game.getGameName() + " round " + game.getRoundNumber() + "\n";
	}

	public Athlete getPredictedWinner() {
		return predictedWinner;
	}

	public void setPredictedWinner(Athlete predictedWinner) {
		this.predictedWinner = predictedWinner;
	}

	public void clearPrediction() {
		this.predictedWinner = null;
	}

	public String getPredictionReport() {
		return predictionReport;
	}

	public void setPredictionReport(String predictionReport) {
		this.predictionReport = predictionReport;
	}
}
