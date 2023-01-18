public class Score {
    private int playerScore;
    private int compyScore;

    private Score(){}

    private static final Score score = new Score();

    public static Score getScoreInstance(){
        return score;
    }

    public void setPlayerScore(int score, int plusMinus){
        this.playerScore = (plusMinus == 1) ? + score : - score;
    }

    public int getPlayerScore(){
        return playerScore;
    }

    public void setCompyScore(int score, int plusMinus){
        this.compyScore = (plusMinus == 1) ? + score : - score; 
    }

    public int getCompyScore(){
        return compyScore;
    }
}
