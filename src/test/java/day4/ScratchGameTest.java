package day4;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ScratchGameTest {

    @Test
    public void testMini(){
        ScratchGame game = new ScratchGame("day4/scratchMini");
    }

    @Test
    public void test_step1(){
        ScratchGame game = new ScratchGame("day4/scratchCards");
    }
}
