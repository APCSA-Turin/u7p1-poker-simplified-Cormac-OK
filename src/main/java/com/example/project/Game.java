package com.example.project;

import java.util.ArrayList;


public class Game{

    public static void main(String[] args) {
        play();
    }

    /**
     * Determines which player has the better hand
     * @param p1 Player 1
     * @param p2 Player 2
     * @param p1Hand Player 1's best hand
     * @param p2Hand Player 2's best hand
     * @param communityCards The community cards
     * @return A string stating which player won, or if there was a tie
     */
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){

        // If the hands aren't the same, the better hand wins
        if(!p1Hand.equals(p2Hand)){
            if(Utility.getHandRanking(p1Hand) > Utility.getHandRanking(p2Hand)){
                return "Player 1 wins!";
            }
            else{
                return "Player 2 wins!";
            }
        }
        // If the hands are tied:
        else{
            // In pair based hands, the highest pair wins
            if(p1Hand.equals("Pair") || p1Hand.equals("Two Pair")){
                if(p1.highestSet(2) > p2.highestSet(2)){
                    return "Player 1 wins!";
                }
                else if (p2.highestSet(2) > p1.highestSet(2)){
                    return "Player 2 wins!";
                }
                else{
                    return "Tie!";
                }
            }
            // The highest three set wins
            else if (p1Hand.equals("Three of a Kind")){
                if(p1.highestSet(3) > p2.highestSet(3)){
                    return "Player 1 wins!";
                }
                else if (p2.highestSet(3) > p1.highestSet(3)){
                    return "Player 2 wins!";
                }
                else{
                    return "Tie!";
                }
            }
            // In a full house, the higher set of 3 wins, and if they're equal, the higher pair wins
            else if (p1Hand.equals("Full House")){
                // Checking set of three
                if(p1.highestSet(3) > p2.highestSet(3)){
                    return "Player 1 wins!";
                }
                else if (p2.highestSet(3) > p1.highestSet(3)){
                    return "Player 2 wins!";
                }
                // Checking pair
                else{
                    ArrayList<Integer> p1Freq = p1.findRankingFrequency();
                    ArrayList<Integer> p2Freq = p2.findRankingFrequency();

                    // Finding the ranks of the pairs
                    int p1PairRank = 0;
                    for(int i = 0; i < p1Freq.size(); i ++){
                        if(p1Freq.get(i) == 2){
                            p1PairRank = i;
                        }
                    }
                    
                    int p2PairRank = 0;
                    for(int i = 0; i < p2Freq.size(); i ++){
                        if(p2Freq.get(i) == 2){
                            p2PairRank = i;
                        }
                    }

                    if(p1PairRank > p2PairRank){
                        return "Player 1 wins!";
                    }
                    else if (p2PairRank > p1PairRank){
                        return "Player 2 wins!";
                    }
                    else{
                        return "Tie!";
                    }
                }
            }
            // The highest set of for wins
            else if (p1Hand.equals("Four of a Kind")){
                if(p1.highestSet(4) > p2.highestSet(4)){
                    return "Player 1 wins!";
                }
                else if (p2.highestSet(4) > p1.highestSet(4)){
                    return "Player 2 wins!";
                }
                else{
                    return "Tie!";
                }
            }
            // In all cases, the better hand will be the one with the highest individual card
            else{
                ArrayList<Card> p1Cards = p1.getHand();
                ArrayList<Card> p2Cards = p2.getHand();

                int p1MaxRank = 0;
                int p2MaxRank = 0;

                for(Card card : p1Cards){
                    if(Utility.getRankValue(card.getRank()) > p1MaxRank){
                        p1MaxRank = Utility.getRankValue(card.getRank());
                    }
                }

                for(Card card : p2Cards){
                    if(Utility.getRankValue(card.getRank()) > p2MaxRank){
                        p2MaxRank = Utility.getRankValue(card.getRank());
                    }
                }

                System.out.println(p1MaxRank);
                System.out.println(p2MaxRank);

                if(p1MaxRank > p2MaxRank){
                    return "Player 1 wins!";
                }
                else if(p2MaxRank > p1MaxRank){
                    return "Player 2 wins!";
                }
            }



        }
        return "Tie!";
    }

    public static void play(){ //simulate card playing

    }
        
        

}