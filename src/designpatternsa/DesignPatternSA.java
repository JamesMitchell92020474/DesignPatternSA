/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatternsa;

/**
 *
 * @title DesignPatternSA.java
 * @author James Mitchell 92020474
 * @date 26/09/2019
 */
public class DesignPatternSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting the simple bingo game...");
        
        //Creating obervable and observer objects
        NumberCalls observable = new NumberCalls();
        Player player1 = new Player("Bob");
        Player player2 = new Player("Sue");
        Player player3 = new Player("Tim");
        
        //subscribing observers to the observable object that will call out the numbers
        observable.registerObserver(player1);
        observable.registerObserver(player2);
        observable.registerObserver(player3);
        //the object will call out the numbers until the vicrtory condition is achieved by one of the subscribed objects
        while(observable.checkVictoryCondition() == false){
            observable.generateRandomNumber();        
        }
        //the program terminates when a winner is announced
        System.exit(0);
    }
    
}
