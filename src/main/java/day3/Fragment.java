package day3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class Fragment {

    String value;

    private static List<String> SYMBOLS = List.of("@",
            "#",
            "$",
            "%",
            "&",
            "*",
            "+",
            "-",
            "/",
            "=");

    private static List<String> NUMBERS = List.of("0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9");


    public boolean isASymbol(){
        return SYMBOLS.contains(value);
    }

    public boolean isANumber(){
        return NUMBERS.contains(value);
    }


    @Override
    public String toString() {
        return value;
    }



}
