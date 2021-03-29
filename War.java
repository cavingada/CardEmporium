import java.util.ArrayList;

public class War{

  // Data Fields
  public Deck mainPile;
  
  private int noOfPlayers;

  public ArrayList<Player> playerList;

  // Constructer
  public War(int noOfPlayers){
    
    this.mainPile = new Deck();
    this.noOfPlayers = noOfPlayers;
    mainPile.shuffleDeck();

    playerList = new ArrayList<Player>();     
    
    for(int i = 0; i < noOfPlayers; i++){
      Player player = new Player("Player " + i+1);
      playerList.add(player);
    }
    
  }


  // Methods
  public ArrayList<Player> getArrayList() {
    return playerList;
  }



}