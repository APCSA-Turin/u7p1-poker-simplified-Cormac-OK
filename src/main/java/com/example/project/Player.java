package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){      
        allCards = new ArrayList<>();
        for(Card c : hand){
            allCards.add(c);
        }
        for(Card c : communityCards){
            allCards.add(c);
        }

        System.out.println(findRankingFrequency());
        
        return "Nothing";
    }

    public void sortAllCards(){} 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> frequencies = new ArrayList<Integer>();
        for (int i = 0; i < 13; i++){
            String rank = Utility.getRanks()[i];
            frequencies.add(0);
            for(Card card : allCards){
                if(card.getRank().equals(rank)){
                    frequencies.set(i, frequencies.get(i) + 1);
                }
            }
        }
        return frequencies; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> frequencies = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++){
            String suit = Utility.getSuits()[i];
            frequencies.add(0);
            for(Card card : allCards){
                if(card.getSuit().equals(suit)){
                    frequencies.set(i, frequencies.get(i) + 1);
                }
            }
        }
        return frequencies; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
