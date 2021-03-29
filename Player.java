public class Player{

  // Data Fields
  private String name; 
  public Deck hand;  
  
  // Constructer 
  public Player(String name) {
    this.name = name;
    this.hand = new Deck(0);
  }

  
  // Methods
  
  public void setName(String newName){
    this.name = newName;
  }

  public String getName() {
    return name;
  }

  public Deck getHand() {
    return hand; 
  }


}