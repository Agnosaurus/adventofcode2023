package day3;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import utils.GridElement;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class GearRatiosTest {

    @Test
    public void testMapBuilding(){
        GearRatios game = new GearRatios("day3/mini");

        GridElement<Fragment> part = game.getGrid().getByCoord(2,2);
        assertEquals("3",part.getElement().getValue());
    }

    @Test
    public void buildSymbolList(){
        GearRatios game = new GearRatios("day3/real");
        Set<String> symbols = new HashSet<>();

        game.getGrid().getElements().forEach(e -> symbols.add(e.getElement().getValue()));

        symbols.forEach(log::info);

    }


    @Test
    public void testListSymbols(){
        GearRatios game = new GearRatios("day3/mini");
        game.getSymbols();
    }

    @Test
    public void testBuildNumbers(){
        GearRatios game = new GearRatios("day3/miniDup");

    }


    @Test
    public void testFullMini(){
        GearRatios game = new GearRatios("day3/mini");

    }

    @Test
    public void testDataBis(){
        GearRatios game = new GearRatios("day3/testData");

    }

    @Test
    public void testFuckthis(){
        GearRatios game = new GearRatios("day3/fuckThis");

    }

    // Use for mode A and B
    @Test
    public void testFull(){
        GearRatios game = new GearRatios("day3/real");

    }

}
