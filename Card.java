import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.Collections;
import java.util.HashMap;
public class Card implements Comparable<Card> {
	private Card() {}
	
	public enum CardNumber{
		TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), 
		EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);  
		
		private int ord;
		private CardNumber(int i) {
			this.ord = i;
		}
		
		public int GetOrd() {
			return ord;
		}
	}
	
	public enum CardType{
		CLUBS, DIAMONDS, HEARTS, SPADES;
	}
	
	private CardNumber cardNumber;
	private CardType cardType;
	
	public CardNumber GetCardNumber() {
		return cardNumber;
	}
	
	public CardType GetCardType() {
		return cardType;
	}
	
	public static List<Card> getPack(){
		List<Card> cardList = new ArrayList<>();
		
		for (CardType type : CardType.values()) {
			for (CardNumber cardNumbers : CardNumber.values()) {
				Card card = new Card();
				card.cardNumber = cardNumbers;
				card.cardType = type;
				cardList.add(card);
			}
		}
		return cardList;
	}
	
	public static void Shuffler(List<Card> cards) {
		Collections.shuffle(cards);
	}
	
	@Override
	public String toString() {
		return cardNumber + " of " + cardType;
	}
	
	@Override
	public int compareTo(Card sel) {
		if (this.GetCardNumber() == sel.GetCardNumber()) {
			return 0;
		} else if (this.GetCardNumber().GetOrd() > sel.GetCardNumber().GetOrd()) {
			return 1;
		} else {
			return -1;	
		}
	}
	
	public static void main(String[] args) {
		List<Card> pack = getPack();
		Shuffler(pack);
		
		List<Card> playerHand = new ArrayList<>();
		
		for (int i = 0; i <5; i++) {
			playerHand.add(pack.get(i));
			System.out.println(playerHand.get(i));
		}
		System.out.println("");
		
		Shuffler(pack);
		
		List<Card> compyHand = new ArrayList<>();
		
		for (int i = 0; i <5; i++) {
			compyHand.add(pack.get(i));
			System.out.println(compyHand.get(i));
		}
		
		System.out.println();
		
		
		Map<String, List<Card>> playerCards = new HashMap<>();
		playerCards.put("Player", playerHand);
		
		Map<String, List<Card>> compyCards = new HashMap<>();
		compyCards.put("Compy", compyHand);
		
		Card a = playerHand.get(0);
		System.out.println(a);
		Card b = compyHand.get(0);
		System.out.println(b);
		
		if (a.compareTo(b) > 0) {
			System.out.println("yep");
		} else {
			System.out.println("Nope");
		}
		
		System.out.println(playerHand.get(0).GetCardNumber());
		int g = playerHand.get(0).GetCardNumber().GetOrd();
	
		System.out.println(g);
		

		List<Integer> tots = new ArrayList<>();
		for (int i = 0; i < 5; i ++) {
			tots.add(playerHand.get(i).GetCardNumber().GetOrd());
		}
		
		Stream<Integer> totStream = tots.stream();

		

//		Optional<Integer> asda = totStream.reduce((ab, ba) -> ab + ba);
		int asda = totStream.reduce(Integer::sum).orElse(0);
//		int asda = totStream.mapToInt(e -> e.reduce(totStream::sum));
		
		System.out.println(asda);
		
		
		
		
	}
}
