package day2;

import lombok.extern.slf4j.Slf4j;
import utils.FileReaderUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class BagGame {

    String resultResource;
    Bag gameBag;

    BagGame(String resultResource,Bag bag){
        super();
        this.resultResource = resultResource;
        this.gameBag = bag;
    }

    public List<Integer> getPossibleGames(){

        List<Integer> result= new ArrayList<>();
        FileReaderUtils utils = new FileReaderUtils();
        try {
            List<String> data = utils.readFile(resultResource);

            result = data.stream().filter(this::validateGame).map(BagGame::extractGameNumber).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }


    public int getPowerGames(){
        FileReaderUtils utils = new FileReaderUtils();
        try {
            List<String> data = utils.readFile(resultResource);

            return data.stream()
                    .map(line -> computeMinBag(line, new Bag()))
                    .mapToInt(Bag::power)
                    .reduce(0, Integer::sum);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    //Game 84: 4 blue, 1 green, 2 red; 7 blue, 6 red; 1 blue, 4 red, 3 green
    public static Bag computeMinBag(String line,Bag inputBag){
        Arrays.stream(line.split(":")[1]
                        .split(";"))
                .map(BagGame::createBag)
                .forEach(inputBag::mergeBagContentIfLess);

        log.info(inputBag.toString());
        return inputBag;
    }

    public int getResult(List<Integer>  input){
        return input.stream().reduce(0, Integer::sum);
    }

    //Game 84: 4 blue, 1 green, 2 red; 7 blue, 6 red; 1 blue, 4 red, 3 green
    //TEST OK
    public static Integer extractGameNumber(String line){
        return Integer.parseInt(line.split(":")[0].replaceAll("Game ",""));
    }

    //4 blue, 1 green, 2 red
    // Doit pouvoir se faire via regex pour le coup
    // TEST OK
    public static Bag createBag(String line){
        Bag bag = new Bag();
        String[] dices = line.split(",");
        Stream.of(dices).forEach(dice -> {

            String[] data = dice.trim().split(" ");
        //    log.info(dice+" -> " + data[0].trim() +" // "+ data[1].trim());
            bag.addDice(Integer.parseInt(data[0].trim()),Color.valueOf(data[1].trim().toUpperCase()));

        });

        return bag;
    }

    /**
     * sera valide si le bag de base peut "contains" l autre
     * @param line game to validate
     * @return
     */
    //Game 84: 4 blue, 1 green, 2 red; 7 blue, 6 red; 1 blue, 4 red, 3 green
    // TEST Ok
    public boolean validateGame(String line){

        boolean result = Arrays.stream(line.split(":")[1]
                        .split(";"))
                .map(BagGame::createBag)
                .allMatch(b -> gameBag.contains(b));

        log.info("Game line "+result + " // " +line);


        return result;

    }



}
