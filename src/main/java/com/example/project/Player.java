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
        sortAllCards();

        String highHand = "Nothing"; // Default if no other hand is found

        if(highCard(communityCards)){
            highHand = "High Card";
        }
        if(countSets(2) == 1){
            highHand = "A Pair";
        }
        if(countSets(2) == 2){
            highHand = "Two Pair";
        }
        if(countSets(3) == 1){
            highHand = "Three of a Kind";
        }
        if(straight()){
            highHand = "Straight";
        }
        if(flush()){
            highHand = "Flush";
        }

        /* A full house is one set of 3 of a kind, and a pair.
         * I test for two pairs instead of one here because the set of 3 contains a pair.
        */
        if(countSets(3) == 1 && countSets(2) == 2){
            highHand = "Full House";
        }

        if(countSets(4) == 1){
            highHand = "Four of a Kind";
        }

        if(straight() && flush()){
            highHand = "Straight Flush";
        }

        // A royal flush is a flush and a straight the lowest card of which is a 10
        if(straight() && flush() && allCards.get(0).getRank().equals("10")){
            highHand = "Royal Flush";
        }
                
        return highHand;
    }

    /**
     * Sorts the allCards ArrayList from cards of low rank to high rank
     */
    public void sortAllCards(){ // Uses selection sort
        for(int i = 0; i < allCards.size()-1; i++){
            Card min = allCards.get(i+1);
            int minIndex = i+1;
            for(int j = i + 1; j < allCards.size(); j++){
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(min.getRank())){
                    min = allCards.get(j);
                    minIndex = j;
                }

                if (Utility.getRankValue(min.getRank()) < Utility.getRankValue(allCards.get(i).getRank())){
                    allCards.set(minIndex, allCards.get(i));
                    allCards.set(i, min);
                }
            }
        }
    } 

    /**
     * Finds the frequency which each rank occurs in the player's hand
     * @return A list of ints where the value at each index is the number of times a card of the corresponding rank appears in the player's complete hand
     */
    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> frequencies = new ArrayList<Integer>();
        for (int i = 0; i < 13; i++){
            String rank = ranks[i];
            frequencies.add(0);
            for(Card card : allCards){
                if(card.getRank().equals(rank)){
                    frequencies.set(i, frequencies.get(i) + 1);
                }
            }
        }
        return frequencies; 
    }

    /**
     * Finds the frequency which each suit occurs in the player's hand
     * @return A list of ints where the value at each index is the number of times a card of the corresponding suit appears in the player's complete hand
     */
    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> frequencies = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++){
            String suit = suits[i];
            frequencies.add(0);
            for(Card card : allCards){
                if(card.getSuit().equals(suit)){
                    frequencies.set(i, frequencies.get(i) + 1);
                }
            }
        }
        return frequencies; 
    }

    /**
     * Tests if the player has a high card
     * @param communityCards The community cards
     * @return Whether the player has a high card
     */
    private boolean highCard(ArrayList<Card> communityCards){
        Card max = hand.get(0);
        boolean playerHasMax = true;
        // Checks whether the player's first of second card is greater
        if(Utility.getRankValue(hand.get(1).getRank()) > Utility.getRankValue(max.getRank())){
            max = hand.get(1);
        }

        // Checks if the player's high card is greater than any of the community cards
        for(Card card : communityCards){
            if(Utility.getRankValue(card.getRank()) > Utility.getRankValue(max.getRank())){
                playerHasMax = false;
            }
        }

        return playerHasMax;
        }

    /**
     * Counts the number of sets of (size) of a kind cards the player has
     * @param size The size of sets to search for
     * @return The number of sets found
     */
    private int countSets(int size){
        int count = 0;
        int last = -1;
        int currentSize = 1;
        
        for(Card card : allCards){
            if(Utility.getRankValue(card.getRank()) == last){ // Checks if current card matches previous
                currentSize ++;
            }
            else{
                currentSize = 1;
            }
            last = Utility.getRankValue(card.getRank());
            if (currentSize == size){ // Checks if the current streak meets the size parameter
                count ++;
                currentSize = 1;
                last = -1;
            }
        }
        return count;
    }

    /**
     * Returns the rank of the highest set of size size
     * @param size The size of the set to search for
     * @return The rank of the highest ranking set
     */
    public int highestSet(int size){
        int max = 0;
        int last = -1;
        int currentSize = 1;
        
        for(Card card : allCards){
            if(Utility.getRankValue(card.getRank()) == last){ // Checks if current card matches previous
                currentSize ++;
            }
            else{
                currentSize = 1;
            }
            last = Utility.getRankValue(card.getRank());
            if (currentSize == size){ // Checks if the current streak meets the size parameter
                if(Utility.getRankValue(card.getRank()) > max){
                    max = Utility.getRankValue(card.getRank());
                }
                currentSize = 1;
                last = -1;
            }
        }
        return max;
    }

    /**
     * @return Whether the player's hand is a straight
     */
    private boolean straight(){
        int last = Utility.getRankValue(allCards.get(0).getRank());
        for (int i = 1; i < allCards.size(); i ++){ 
            if(Utility.getRankValue(allCards.get(i).getRank()) != last + i){
                return false;
            }
        }
        return true;
    }

    /**
     * @return Whether the player's hand is a flush
     */
    private boolean flush(){
        ArrayList<Integer> freq = findSuitFrequency();
        for (Integer num: freq){
            if (num == 5){
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString(){
        return hand.toString();
    }
}
