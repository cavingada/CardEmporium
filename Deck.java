import java.util.ArrayList;

public class Deck {

  // Data Fields
  private ArrayList<Card> deck;

  // Constructor
  public Deck() {
    this.deck = new ArrayList<Card>();
    
    int[] possibleCardValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
    String[] possibleSuites = { "Clubs", "Diamonds", "Hearts", "Spades" };

    for (int i = 0; i < possibleCardValues.length; i++) {
      for (int j = 0; j < possibleSuites.length; j++) {
        Card temp = new Card(possibleCardValues[i], possibleSuites[j]);
        deck.add(temp);
      }
    }
  }
  
  public Deck(int none) { // 
    this.deck = new ArrayList<Card>();
  }
  // Methods


  /*
   * takeRandomCard randomly selects a card from the deck and does two things.
   * First, it removes the card and second, it returns the removed card object.
   */
  public Card takeRandomCard() {
    int randIndex = (int) (Math.random() * (deck.size() - 1)); // note: these are indices, that's why we want 0-51!

    Card temp = deck.get(randIndex);
    this.deck.remove(randIndex);
    return temp;
  }

  public void shuffleDeck() {
    ArrayList<Card> randomCards = new ArrayList<Card>();

    for (int i = 0; i < 52; i++) {
      randomCards.add(this.takeRandomCard());
    }

    this.deck = randomCards;
  }

  /*
   * Transfers the top card from the deck to the bottom of the other deck.
   */
   
  public void transferCard(Deck otherDeck) {
    Card temp = this.deck.get(0);
    this.deck.remove(0);
    otherDeck.deck.add(temp);
  }


/* clearDeck clears the deck. It removes 
 * all card objects from the deck.
 */

  public void clearDeck() {
    deck.clear();
  }

  public ArrayList<Card> getDeck() {
    return deck;
  }

/* toString just returns the deck arrayList, which easily 
 */

  public String toString() {
    return this.deck.toString();
  }

  public void add(Card x){
    this.deck.add(x);
  }
}