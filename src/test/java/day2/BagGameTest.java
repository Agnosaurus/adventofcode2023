package day2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static day2.BagGame.createBag;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class BagGameTest {

    @Test
    public void testextractGameNumber(){
        assertEquals(84, (int) BagGame.extractGameNumber("Game 84: 4 blue, 1 green, 2 red; 7 blue, 6 red; 1 blue, 4 red, 3 green"));
    }

    @Test
    public void enumCast(){
        assertEquals(Color.RED, Color.valueOf("RED"));
        assertEquals(Color.RED, Color.valueOf("red".toUpperCase()));

    }

    @Test
    //4 blue, 1 green, 2 red
    public void testcreateBag(){
        Bag testBag = createBag("4 blue, 1 green, 2 red");

        assertEquals(4,testBag.getDices(Color.BLUE));
        assertEquals(1,testBag.getDices(Color.GREEN));
        assertEquals(2,testBag.getDices(Color.RED));

    }

    @Test
    public void testvalidateGame_simple(){
        Bag testBag = createBag("4 blue, 1 green, 2 red");
        BagGame game = new BagGame("day2/testGame",testBag);

        assertTrue(game.validateGame("Game 84: 4 blue, 1 green, 2 red"));
        assertTrue(game.validateGame("Game 84: 3 blue, 1 green, 2 red"));
        assertTrue(game.validateGame("Game 84: 4 blue, 0 green, 2 red"));
        assertTrue(game.validateGame("Game 84: 4 blue, 1 green, 1 red"));
        assertTrue(game.validateGame("Game 84: 0 blue, 0 green, 0 red"));
        assertFalse(game.validateGame("Game 84: 5 blue, 1 green, 2 red"));
        assertFalse(game.validateGame("Game 84: 4 blue, 2 green, 2 red"));
        assertFalse(game.validateGame("Game 84: 4 blue, 1 green, 3 red"));
    }

    @Test
    public void computeResult_1(){
        Bag testBag = createBag("14 blue, 13 green, 12 red");
        BagGame game = new BagGame("day2/gameresult",testBag);
        int result = game.getResult(game.getPossibleGames());
        log.info(""+result);
    }

    @Test
    public void computeResult_2(){

        BagGame game = new BagGame("day2/gameresult",new Bag());
        int result = game.getPowerGames();
        log.info(""+result);
    }

    @Test
    public void testcomputeMinBag(){
       Bag minBag = BagGame.computeMinBag("Game 75: 3 red, 3 green; 3 green, 12 red; 18 red, 2 blue; 3 green, 9 red, 1 blue; 14 red, 1 green; 15 red",new Bag());

        assertEquals(2,minBag.getDices(Color.BLUE));
        assertEquals(3,minBag.getDices(Color.GREEN));
        assertEquals(18,minBag.getDices(Color.RED));
    }
}
