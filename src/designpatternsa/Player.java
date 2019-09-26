/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatternsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @title Player.java
 * @author James Mitchell 92020474
 * @date 26/09/2019
 */
public class Player implements Observer {
    
    private final String personName;
    //bingo numbers for each player
    private List<Integer> bingoSet = new ArrayList<>(); 
    //to control how many numbers left to match, when = 0, BINGO!
    private int matchesLeft = 3;
    private NumberCalls numberCalls;
 
    // constructor
    public Player(String pName) {            
            
            personName = pName;
            bingoSet = createBingoSet();           
            System.out.print(personName + " has entered the game, their bingo card numbers are ");
            //displays numbers in Person's bingo set
            bingoSet.forEach((i) -> {
                System.out.print(i + ", ");
            });
            System.out.println();
            
    }
 
    //creates bingo set of 3 random numbers from 1 to 10 inclusive
    private ArrayList<Integer> createBingoSet(){
        ArrayList<Integer> newBingoSet = new ArrayList<>();
        for (int i=0; i<3; i++){
            int randNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            newBingoSet.add(randNum);                    
        }
        return newBingoSet;
    }
    
    // mandatory method because implements Observer
    @Override
    public void update(Observable o, Object numberCalls) {
        this.numberCalls = (NumberCalls)numberCalls;        
        //checks through the person't bingo set. If number matched the one called out, the amount of numbers to match is reduced
        bingoSet.stream().filter((num) -> (Objects.equals(num, this.numberCalls.getCalloutNumber()))).forEachOrdered((_item) -> {
            matchesLeft--;
        });  
        // Person matched all the numbers, print out victory message and set victory condition to be true
        if (matchesLeft == 0){
            System.out.println("BINGO! " + personName + " has all the numbers called! " + personName + " is the WINNER!");
            this.numberCalls.victory = true;            
        }
    }
    
}
