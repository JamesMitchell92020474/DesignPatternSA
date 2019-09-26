/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatternsa;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @title NumberCalls.java
 * @author James Mitchell 92020474
 * @date 26/09/2019
 */
public class NumberCalls extends Observable {
    // list of all subscribed observers
    private final ArrayList<Observer> observers;
    private Integer calloutNumber;
    boolean victory = false;
    //has list of numbers called, will be used to make sure that the same number is not called out twice
    private final ArrayList<Integer> numbersCalled;
  
    
    // Constructor
    public NumberCalls() {
        observers = new ArrayList<>();
        numbersCalled = new ArrayList<>(); 
    }
 
    //generates random integer between 1 and 10, then checks against numbersCalled ArrayList
    //if the number was called before, the rest of the method is ignored, otherwise it is added to the numbersCalled ArrayList
    public void generateRandomNumber(){
        this.calloutNumber = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        //if there are no numbers in the ArrayList, there is no need for the check
        if (numbersCalled.size()>0){
            for (int num: numbersCalled){
                if (this.calloutNumber == num){                    
                    return;
                }
            }
        }
        System.out.println("Calling out a random number between 1 and 10 inclusive...  the number is ...."+ this.calloutNumber);
        numbersCalled.add(this.calloutNumber);
        //updating observers with the new number information
        observers.forEach((observer) -> {
            observer.update(this, this);
        });
    } 
        
    //subscribes observer to the observable object 
    public void registerObserver(Observer observer) {
             observers.add(observer);
    }
    //removes subsriprion to the observable object for observer 
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i>=0) {
            observers.remove(observer);
        }
    }

    /**
     * @return the calloutNumber
     */
    public Integer getCalloutNumber() {
        return calloutNumber;
    }
    
    //getter method that checks the value of victory
    public boolean checkVictoryCondition(){
        return victory;
    }
}
