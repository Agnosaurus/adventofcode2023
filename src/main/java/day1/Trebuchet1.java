package day1;

import lombok.extern.slf4j.Slf4j;
import utils.FileReaderUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static day1.InputType.WITH_CHAR;

@Slf4j
public class Trebuchet1 {

    public int sumCalibrates(String file,InputType inputType) {

        int result=0;
        FileReaderUtils utils = new FileReaderUtils();
        try {
            List<String> data = utils.readFile(file);

            result = data.stream().mapToInt(e -> computeValue(e,inputType)).sum();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * Quick & dirty
     */
    public String formatLine(String line){
        return line.replaceAll("one","o1e")
                .replaceAll("two","t2o")
                .replaceAll("three","t3e")
                .replaceAll("four","f4r")
                .replaceAll("five","f5e")
                .replaceAll("six","s6x")
                .replaceAll("seven","s7n")
                .replaceAll("eight","e8t")
                .replaceAll("nine","n9e")
                ;
    }

    public int computeValue(String line,InputType inputType){

        String formattedLine = line;

        if (WITH_CHAR == inputType)
            formattedLine = formatLine(line);

        Pattern digitRegex = Pattern.compile("\\d");
        Matcher countEmailMatcher = digitRegex.matcher( formattedLine);

        int firstD=-1;
        int lastD = -1;
        int currentD = -1;

        while (countEmailMatcher.find()) {
            currentD= Integer.parseInt(countEmailMatcher.group());

            lastD = currentD;

            if ( firstD == -1)
                firstD =currentD;
        }

        int result = 10*firstD + lastD;
        log.debug(line+"  -->  "+formattedLine+"  -->  "+result);

        return result;
    }

}
