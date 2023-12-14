package day4;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class Card {
    int name;
    List<Integer> winningNumbers;
    List<Integer> numbers;

    private int score =0;
    private int match =0;

    public int computeScore(){
        int match = 0;
        for (Integer winningNb : winningNumbers){
            if (numbers.contains(winningNb)){
                match++;
            }
        }

        log.info("Nb of match "+match);

        int score = 0;

        if ( match >=0 )
            score = (int) Math.pow(2,match-1);

        this.setScore(score);
        this.setMatch(match);

        return score;

    }



    //83 86  6 31 17  9 48 53
    public void addNumbers(String line){
        log.info("Numbers "+line);

        List<Integer> listNumbers = Arrays.stream(line.split(" "))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt).sorted().collect(Collectors.toList());

        this.numbers = listNumbers;
    }

    //41 48 83 86 17
    public void addWinningNumbers(String line){
        log.info("Winning Numbers "+line);

        List<Integer> listNumbers = Arrays.stream(line.split(" "))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt).sorted().collect(Collectors.toList());

        this.winningNumbers = listNumbers;
    }

    public Card copy(){
        Card result = new Card();
        result.setMatch(this.getMatch());
        result.setName(this.getName());

        return result;
    }

}
