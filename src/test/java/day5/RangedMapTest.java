package day5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RangedMapTest {

    RangedMap rangedMap = new RangedMap();

    @Test
    public void test_get(){

        rangedMap.addRange(10d,20d,5d);

        /**
        assertEquals(22d, rangedMap.get(12d));
        assertEquals(5d, rangedMap.get(5d));
*/
    }

}
