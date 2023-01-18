import java.util.LinkedList;
import java.util.Collections;

public class Card implements Comparable<Card> {
    private Card() {};

    public enum CardNumber {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), 
		EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

        private int ord;
        private CardNumber(int i){
            this.ord= i;
        }

        public int GetOrd(){
            return ord;
        }
    }

    public enum CardType{
        DIAMONDS, HEARTS, CLUBS, SPADES;
    }

    private CardNumber cardNumber;
    private CardType cardType;

    public CardNumber GetCardNumber(){
        return cardNumber;
    }

    public CardType GetCardType(){
        return cardType;
    }

    public static LinkedList<Card> GetPack(){
        LinkedList<Card> cardList = new LinkedList<>();

        for (CardType type : CardType.values()){
            for (CardNumber num : CardNumber.values()){
                Card card = new Card();
                card.cardNumber = num;
                card.cardType = type;
                cardList.add(card);
            }
        }
        return cardList;
    }

    public static void Shuffler(LinkedList<Card> cards){
        Collections.shuffle(cards);
    }

    @Override 
    public String toString() {
        return cardNumber + " of " + cardType;
    }

    @Override
    public int compareTo(Card select){
        if (this.GetCardNumber() == select.GetCardNumber()){
            return 0;
        } else if (this.GetCardNumber().GetOrd() > select.GetCardNumber().GetOrd()){
            return 1;
        } else {
            return -1;
        }
    }

    public static int GetDifference(Card played, Card cardInPlay){
        int playedCard = played.GetCardNumber().GetOrd();
        int deckCard = cardInPlay.GetCardNumber().GetOrd();

        if (playedCard == deckCard) {
            return 0;
        } else if (playedCard > deckCard){
            return playedCard - deckCard;
        } else {
            return deckCard - playedCard;
        }
    }

    public static boolean CheckSuitMatch(Card played, Card cardInPlay){
        boolean isAMatch = false;
        if (played.GetCardType() == cardInPlay.GetCardType()){
            isAMatch = true;
        }
        return isAMatch;
    }
}
