public class Score {
    private int playerScore;
    private int compyScore;

    private Score(){}

    private static final Score score = new Score();

    public static Score getScoreInstance(){
        return score;
    }

    public void setPlayerScore(int score){
        // if (plusMinus == 1){
            this.playerScore += score;
        // } else {
        //     this.playerScore -= score;
        // }
    }

    public int getPlayerScore(){
        return playerScore;
    }

    public void setCompyScore(int score){
        // if (plusMinus == 1){
            this.compyScore += score;
        // } else {
        //     this.compyScore -= score;
        // }
    }

    public int getCompyScore(){
        return compyScore;
    }
}
