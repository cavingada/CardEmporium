class Card {

  // Data Fields
  private String suit;
  private int cardNo; 

  // Constructor 
  public Card(int cardNo, String suit){
    this.cardNo = cardNo;
    this.suit = suit;
  }

  // Methods

/*
getCardNo returns the number of the card. 
*/

  public int getCardNo() {
    return cardNo;
  }
/*
getSuit returns the suit of the card. 
*/
  public String getSuit() {
    return suit;
  }

/*
toString Makes the card object readable when printed. 
*/
  public String toString(){
    return cardNo + " of " + suit;
  } 

}