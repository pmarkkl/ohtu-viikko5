package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private int scoreNeededForWinOrAdvantage = 4;
    private int numberOfPlayers = 2;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        boolean playersAreTied = player1Score == player2Score;
        boolean oneOfPlayersCloseToVictory = player1Score >= scoreNeededForWinOrAdvantage || player2Score >= scoreNeededForWinOrAdvantage;
        if (playersAreTied) {
            return scoreForTiedScore();
        } else if (oneOfPlayersCloseToVictory) {
            return scoreForWinAndAdvantage();
        } else {
            return scoreNotTiedOrPlayerCloseToVictory();
        }
    }

    private String scoreForTiedScore() {
        switch (player1Score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }
    
    private String scoreForWinAndAdvantage() {
        int scoreDifference = player1Score - player2Score;
            if (scoreDifference == 1) {
                return "Advantage player1";
            } else if (scoreDifference == -1) {
                return "Advantage player2";
            } else if (scoreDifference >= 2) {
                return "Win for player1";
            } else {
                return "Win for player2";
            }
    }
    
    private String scoreNotTiedOrPlayerCloseToVictory() {
        String score = "";
        int selectedPlayersScore = 0;
        for (int whichPlayer = 1; whichPlayer < numberOfPlayers+1; whichPlayer++) {
            if (whichPlayer == 1) {
                selectedPlayersScore = player1Score;
            } else {
                score += "-";
                selectedPlayersScore = player2Score;
            }
            switch (selectedPlayersScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
            }
        }
        return score;
    }
}
