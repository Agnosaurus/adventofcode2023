package day5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static day5.Almanach.extractType;
import static day5.Almanach.initSeeds;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class AlmanachTest {

    @Test
    public void test_addRange(){
        Almanach almanach = new Almanach("day5/data_mini");
        RangedMap rangedMap = new RangedMap();
        almanach.addRange("50 78 2",rangedMap);
    }

    @Test
    public void test_extractType(){
        Type type = extractType("seed-to-soil map:");
        assertEquals(Type.SOIL,type);
    }

    @Test
    public void testCreate_mini(){
        Almanach almanach = new Almanach("day5/data_mini");
    }

    @Test
    public void testResult_2_mini(){
        Almanach2 almanach = new Almanach2("day5/data_mini");



       // step 1 : assertEquals(35,result);
       // assertEquals(46,result); //Step 2
    }

    @Test
    public void testResult_1_mini(){
        Almanach almanach = new Almanach("day5/data_mini");

        double result = almanach.resolveClosestSeed();
        log.info("Result: "+result);

        assertEquals(35,result);
    }

    @Test
    public void testResult_1_realData(){
        Almanach almanach = new Almanach("day5/data");


        double result = almanach.resolveClosestSeed();
        log.info("Result: "+result);

       // assertEquals(35,result);
    }

    @Test
    public void testResult_2_realData(){
        Almanach2 almanach = new Almanach2("day5/data");


        // assertEquals(35,result);
    }

    @Test
    public void testInitSeeds(){
        //    public static void initSeeds(String seedsS,List<Integer> seeds){

        List<Double> seeds = new ArrayList<>();

        initSeeds("seeds: 79 14 55 13",seeds);

        assertEquals(4, seeds.size());

    }
}
