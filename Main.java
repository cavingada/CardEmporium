import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.*;
import java.util.concurrent.TimeUnit;

class Main {
  public static Deck cumCards = new Deck(0);
  
  /*
   * updatePlayerNo will return the new number of players, given the original
   * number of players. The way it does this is by checking if the hands of any
   * players are empty. If so, the playerlist is updated (by removing the player
   * from the game). Then the remaining number of players are returned.
   */

  public static int updatePlayerNo(War warGame) {
    int finNum = warGame.playerList.size();
    boolean flag = false;
    
    for (Player player : warGame.playerList) {
      if (player.getHand().getDeck().size() == 0) {
        System.out.println("++++++++++++++++++++++++++++++++\n" + player.getName() + " JUST GOT KNOCKED OUT...\n++++++++++++++++++++++++++++++++");
        warGame.playerList.remove(player);
        finNum--;
        flag = true;
        break;
      }
    }
    if(flag){
      updatePlayerNo(warGame);
    }
    return finNum;
  }

  /*
   * Iterates through each card in deck, giving them "equally" throughout each
   * player. If there are remainders, it gives the extra cards, one at a time, to
   * each player (starting at player 1)
   */
  public static void dealCards(War warGame, int numOfPlayers) {
    int currentPlayer;

    for (int i = 0; i < 52; i++) {
      currentPlayer = i % numOfPlayers; // 4 DISCRETE STRUCTURES!!

      // take top card in deck, then transfer to player hand.
      // Card topCard = warGame.mainPile.getDeck().get(0);

      warGame.mainPile.transferCard(warGame.playerList.get(currentPlayer).hand);

    }
  }
   
  public static int winnerOfRound(War warGame) {
    // This segment will arrange a deck of cards that consists of the topcard from each player.
    System.out.println("");
    for (int i = 0; i < warGame.playerList.size(); i++) {
      System.out.println(warGame.playerList.get(i).getName() + " drew a " + warGame.playerList.get(i).getHand().getDeck().get(0));

      warGame.playerList.get(i).hand.transferCard(Main.cumCards); // transfers the top card to the cumCards deck.
    }
    
    //System.out.println("CumCards is" + cumCards);

    // find the max and which ONES (plural!) have the max
    ArrayList<Integer> cardNums = new ArrayList<Integer>(); 
    
    // need to create an arraylist of NUMBERS, not type Cards.
    for (int i = 0; i < Main.cumCards.getDeck().size(); i++) {
      cardNums.add(Main.cumCards.getDeck().get(i).getCardNo());
    }
    int max = Collections.max(cardNums);

    ArrayList<Integer> winningPlayers = new ArrayList<Integer>();

    for (int i = 0; i < cardNums.size(); i++) {
      if (cardNums.get(i) == max) {
        winningPlayers.add(i);
      }
    }
    
    System.out.println("");


    int winner = 0;
    if(winningPlayers.size() > 1){
      int winnerIndex = (int)(Math.random() * (winningPlayers.size() - 1));
      winner = winningPlayers.get(winnerIndex);
      
    }else{
      winner = winningPlayers.get(0); //ONLY valid if not toWar
    }

    //System.out.println(winner + "\n");
    System.out.println(warGame.playerList.get(winner).getName() + " won the round!!");

    

    Deck temp = new Deck(0);
    for (Card x: cumCards.getDeck()) { // transfers the cards to the winner.
      temp.add(x);
    }
    
    for (Card t: cumCards.getDeck()) {
      temp.transferCard(warGame.playerList.get(winner).getHand());
    }


    System.out.println("");
    for(int i = 0; i < warGame.playerList.size(); i++){
      //  System.out.println("Player " + (i+1) + " is: " + warGame.playerList.get(i).getHand().getDeck() + "\n");
      System.out.println((warGame.playerList.get(i).getName() + " has " + warGame.playerList.get(i).getHand().getDeck().size()) + " cards left.");
    }

    System.out.println("----------------------------------");
    cumCards.clearDeck();

    return winner;

  }

  public static void playWar(int noOfPlayers) {

    War newGame = new War(noOfPlayers);

    // RECIEVING PLAYER DATA AND UPDATING WAR PLAYER LIST!
    Scanner playerName = new Scanner(System.in);
    System.out.println("\n");
    
    for (int i = 0; i < noOfPlayers; i++) {
      System.out.println("Enter Player " + (i + 1) + "'s Name: ");

      String temp = playerName.nextLine(); // Read user input
      newGame.playerList.get(i).setName(temp);
    }
    //playerName.close();

    // START THE GAME!

    dealCards(newGame, noOfPlayers); // DEALING THE CARDS

    Scanner shouldContinue = new Scanner(System.in);

    System.out.println("\n" + "INSTRUCTIONS: Press 'c' to start!, Press 'q' to quit!, and Press 's' to speed through the game!");

    String isContinue = shouldContinue.nextLine();
    
    while (noOfPlayers > 1) {
      winnerOfRound(newGame);
      noOfPlayers = updatePlayerNo(newGame); 
      
      System.out.println("\nPress 'c' to continue, 's' to speed up the game or 'q' to quit: ");
      isContinue = shouldContinue.nextLine();


      while(!isContinue.equals("q") && !isContinue.equals("c") && !isContinue.equals("s")){
        System.out.println("Enter a valid command please. ");
        isContinue = shouldContinue.nextLine();
      }  
      
        /*
        Quitting the game. The player(s) with most cards wins. 
        */
      if (isContinue.equals("q")) {
        ArrayList<String> winners = new ArrayList<String>();
        int currCardNo = 0;

        for (int i = 0; i< newGame.playerList.size(); i++) {
          if (newGame.playerList.get(i).getHand().getDeck().size() > currCardNo) {
            winners.clear();
            winners.add(newGame.playerList.get(i).getName());
            currCardNo = newGame.playerList.get(i).getHand().getDeck().size();
          }
          else if (newGame.playerList.get(i).getHand().getDeck().size() == currCardNo) {
            winners.add(newGame.playerList.get(i).getName());
            currCardNo = newGame.playerList.get(i).getHand().getDeck().size();
          }
        }
        for(String player: winners){
          System.out.print(player + ", ");
        }
        System.out.print("wins the game - thank you for playing!\n---------------------------");
        break;
      }
      if(isContinue.equals("s")){
        while(noOfPlayers>1){
          winnerOfRound(newGame);
          wait(1000);
          noOfPlayers = updatePlayerNo(newGame); 
        }
      }   
    }
  }
  public static void wait(int ms)
{
    try
    {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
}
  public static void main(String[] args) {
    System.out.print("Welcome to the Card Emporium, here you can select from a handful of card games in cyberspace!!So, let's begin by my all time favorite card game... WAR! War was populilzed during WW2 as a form of entertainment for the soliders. Now it's commonname around households everywhere in the U.S. How many players do you want to play with? ");
    Scanner readNoPlayers = new Scanner(System.in);
    int noPlayers = readNoPlayers.nextInt();
    playWar(noPlayers);
  }

}
