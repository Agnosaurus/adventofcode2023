package day1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static day1.InputType.NORMAL;
import static day1.InputType.WITH_CHAR;


@Slf4j
public class Trebuchet1Test {

    Trebuchet1 instance =new Trebuchet1() ;

    @Test
    public void testRealCase_WITH_CHAR(){
        int result = instance.sumCalibrates("day1/listday1", WITH_CHAR);
        log.info(""+result);
    }


    @Test
    public void testSmallCase_WITH_CHAR(){
        int result = instance.sumCalibrates("day1/test1", WITH_CHAR);
        Assertions.assertEquals(281, result);
    }

    public static Stream<Arguments> lineTestData(){
        return Stream.of(
                Arguments.of("1abc2", 12, NORMAL),
                Arguments.of("pqr3stu8vwx", 38, NORMAL),
                Arguments.of("a1b2c3d4e5f", 15, NORMAL),
                Arguments.of("treb7uchet", 77, NORMAL),
                Arguments.of("two1nine", 29, WITH_CHAR),
                Arguments.of("eightwothree", 83, WITH_CHAR),
                Arguments.of("abcone2threexyz", 13, WITH_CHAR),
                Arguments.of("xtwone3four", 24, WITH_CHAR),
                Arguments.of("4nineeightseven2", 42, WITH_CHAR),
                Arguments.of("zoneight234", 14, WITH_CHAR),
                Arguments.of("7pqrstsixteen", 76, WITH_CHAR)
        );
    }

    @ParameterizedTest
    @MethodSource("lineTestData")
    public void testLine( String line, int expected,InputType inputType){

        Assertions.assertEquals(instance.computeValue(line, inputType), expected);
    }

}