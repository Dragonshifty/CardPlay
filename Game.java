import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
public class Game {

    private LinkedList<Card> deck = new LinkedList<>();
    private List<Card> playerHand = new ArrayList<>();
    private List<Card> compyHand = new ArrayList<>();
    private LinkedList<Card> cardPile = new LinkedList<>();
    private Card cardPlayed;
    private Card cardInPlay;

    public void GetNewDeck(){
        deck = Card.GetPack();
    }

    public void GetPlayerHand(){
        for (int i = 0; i < 5; i++){
            playerHand.add(deck.removeLast());
        }
        Collections.sort(playerHand);
    }

    public void GetCompyHand(){
        for (int i = 0; i < 5; i++){
            compyHand.add(deck.removeLast());
        }
    }

    public void GetNewCard(String whoIsPlaying){
        if (whoIsPlaying == "Player" && deck.size() > 0){
            playerHand.add(deck.removeLast());
        } else if (whoIsPlaying == "Compy" && deck.size() > 0){
            compyHand.add(deck.removeLast());
        }
    }

    public void showPlayerHand(){
        int ind = 1;
        for (int i = 0; i < playerHand.size(); i++){
            System.out.println((ind++) + ": " + playerHand.get(i));
        }
        System.out.println();
    }

    public void showCompyHand(){
        System.out.println("Compy hand:\n");
        for (int i = 0; i < compyHand.size(); i++){
            System.out.println(compyHand.get(i));
        }
        System.out.println();
    }

    public void showCardInPlay(){
        System.out.println("Card in play: " + cardPile.getFirst());
        System.out.println();
    }

    public void DisplayScores(){
        Score score = Score.getScoreInstance();
        System.out.println("Player: " + score.getPlayerScore() + "\nComputer: " + score.getCompyScore() + "\n" + "Cards Left: " + deck.size() + "\n");
    }

    public void InitialiseGame(){
        GetNewDeck();
        Card.Shuffler(deck);

        GetPlayerHand();
        GetCompyHand();
        cardPile.add(deck.removeLast());
        // showPlayerHand();
        // showCardInPlay();
        
    }
    
    public void Play(){
        System.out.println("Let's Begin!\n");

        int selection = 0;
        boolean proceed = false;
        InitialiseGame();

        do {
         
            while (!proceed){
                showPlayerHand();
                showCardInPlay();
                DisplayScores();

                try {
                System.out.println("Which card do you wish to play? ");
                Scanner input = new Scanner(System.in);
                selection = input.nextInt() - 1;
                if (selection >= 0 && selection < playerHand.size()){
                    proceed = true;
                    // input.close();
                } else {
                    System.out.println("\nMust be a number between 1 and 5.\n");
                }
                } catch (InputMismatchException e) {
                    System.out.println("Must be a number between 1 and 5");
                }                
            }
            proceed = false;

            // Get card in play from top of card pile
            cardInPlay = cardPile.getFirst();

            // Get played from player hand
            cardPlayed = playerHand.get(selection);

            // Send player's card to top of the card pile
            cardPile.addFirst(playerHand.remove(selection));
            
            // Player gets new card and sorts order
            GetNewCard("Player");
            Collections.sort(playerHand);

            //Compare player card to card in play
            int HighLowDraw = cardPlayed.compareTo(cardInPlay);
            int difference = Card.GetDifference(cardPlayed, cardInPlay);
            boolean isAMatch = Card.CheckSuitMatch(cardPlayed, cardInPlay);

            // System.out.println(HighLowDraw);

            // Set up score instance
            Score score = Score.getScoreInstance();

            // Score player
            switch(HighLowDraw){
                case 0:
                    System.out.println("No Score change");
                    break;
                case 1:
                    if (isAMatch){
                        difference *= 2;
                        System.out.println("\nMatching suit, double score: " + difference + "\n");
                        score.setPlayerScore(difference);
                    } else {
                        System.out.println("\nScore: +" + difference + "\n");
                        score.setPlayerScore(difference);
                    }
                    break;
                case -1:
                    if (isAMatch){
                        System.out.println("\nSuit match, no score change");
                    } else {
                        System.out.println("\nScore: " + -difference + "\n");
                        score.setPlayerScore(-difference);
                    }
                    break;
            }

            //Get card in play from top of the card pile
            cardInPlay = cardPile.getFirst();

            // Compy turn
            Compy compy  = new Compy();
            compy.sortHand(compyHand);
            compy.getCardInPlay(cardInPlay);
            // compy.showCompyHand();

            // Receive card played index and score
            int[] compyCardPlayed = compy.possibleScores();

            // Remove card played and add to top of card pile
            cardPile.addFirst(compyHand.remove(compyCardPlayed[0]));

            // Get new card from deck
            GetNewCard("Compy");

            // Score compy
            score.setCompyScore(compyCardPlayed[1]);





        } while (deck.size() > 0);

    }

}
