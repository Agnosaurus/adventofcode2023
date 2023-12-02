package day2;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static day2.Color.*;

@Slf4j
public class Bag {

    Map<Color,Integer> bagContent = new HashMap<>();

    Bag(){
        super();
        bagContent.put(RED,0);
        bagContent.put(GREEN,0);
        bagContent.put(BLUE,0);
    }

    Bag(int maxR, int maxG, int maxB){
        super();
        bagContent.put(RED,maxR);
        bagContent.put(GREEN,maxG);
        bagContent.put(BLUE,maxB);
    }


    public Bag cloneBag(){
        return new Bag(getDices(RED),getDices(GREEN),getDices(BLUE));
    }
    public int getDices(Color color){
        return bagContent.get(color);
    }

    public Map<Color,Integer>  getContent(){
        return bagContent;
    }


    public void addDice(int nb , Color color){
        bagContent.put(color,bagContent.get(color)+nb);
    }

    public void setDiceIfLess(int nb , Color color){
        bagContent.put(color,Math.max(bagContent.get(color),nb));
    }


    public void mergeBagContentIfLess(Bag bag){
        setDiceIfLess(bag.getDices(RED), RED);
        setDiceIfLess(bag.getDices(GREEN), GREEN);
        setDiceIfLess(bag.getDices(BLUE), BLUE);

    }


    public boolean isPossible(int currentR, int currentG, int currentB){
        return bagContent.get(RED) >= currentR
                && bagContent.get(GREEN) >= currentG
                && bagContent.get(BLUE) >= currentB;
    }

    public boolean contains(Bag bag){
        return bagContent.get(RED) >= bag.getContent().get(RED)
                && bagContent.get(GREEN) >= bag.getContent().get(GREEN)
                && bagContent.get(BLUE) >= bag.getContent().get(BLUE);
    }

    public int power(){
        return bagContent.get(RED) * bagContent.get(GREEN) *bagContent.get(BLUE);
    }

    @Override
    public String toString() {
        return "R"+bagContent.get(RED)+"-G"+bagContent.get(GREEN)+" B"+bagContent.get(BLUE);
    }
}
