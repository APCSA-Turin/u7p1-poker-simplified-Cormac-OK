package com.example.project;
import java.util.ArrayList;


public class Game{

    public static void main(String[] args) {
        play();
    }

    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        return "Error";
    }

    public static void play(){ //simulate card playing
        Player player = new Player();
        player.addCard(new Card("7", "♠"));
        player.addCard(new Card("7", "♦"));
      
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("8", "♣"));
        communityCards.add(new Card("8", "♥"));
        communityCards.add(new Card("A", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
    }
        
        

}