package day3;

import lombok.extern.slf4j.Slf4j;
import utils.FileReaderUtils;
import utils.Grid;
import utils.GridElement;


import java.util.*;
import java.util.stream.Collectors;


@Slf4j
public class GearRatios {

    Grid<Fragment> grid = new Grid<>();

    public Grid<Fragment> getGrid(){
        return grid;
    }


    public Integer mergeDigits(List<GridElement<Fragment>> currentNb ){
        return Integer.parseInt(currentNb.stream()
                .map( ge -> ge.getElement().getValue())
                .collect(Collectors.joining("")));
    }

    public void addGear(GridElement<Fragment> gear , int nb,Map<GridElement<Fragment>, List<Integer>> gearsMap){

        if (!gearsMap.containsKey(gear))
            gearsMap.put(gear,new ArrayList<>());

        List<Integer> gearList = gearsMap.get(gear);
        gearList.add(nb);
        gearsMap.put(gear,gearList);
    }


    public GearRatios(String resource){
        // On popule la map
        initMap(resource);
        List<Integer> resultSet = new ArrayList<>();

        Map<GridElement<Fragment>, List<Integer>> gearsMap = new HashMap<>();


        // On parcourt pour selectionner les chiffres
        List<GridElement<Fragment>> numbers = getNumbers();

        // on parcourt a nouveau pour les chiffres. Sur chaque chiffre trouve, on va voir a droite pour former le nombre

        for ( int i = 0 ; i < numbers.size()-2 ; i++){ // works because we stop on a 3 digits number
            GridElement<Fragment> firstNb = numbers.get(i);
            GridElement<Fragment> secondNb = numbers.get(i+1);
            GridElement<Fragment> thirdNb = numbers.get(i+2);

            List<GridElement<Fragment>> currentNb = new ArrayList<>();
            currentNb.add(firstNb);

            if ( secondNb.isNext(firstNb) ){
                currentNb.add(secondNb);
                i++;
            }

            if ( thirdNb.isNext(secondNb) && secondNb.isNext(firstNb)  ){
                currentNb.add(thirdNb);
                i++;
            }

            List<GridElement<Fragment>> adjacentSymbols = grid.getAdjacentCases(currentNb).stream().filter(ge -> ge.getElement().isASymbol()).collect(Collectors.toList());
            boolean adjacentToSymbol = !adjacentSymbols.isEmpty();

            if (adjacentToSymbol) {
                int nb = mergeDigits(currentNb);

                for ( GridElement<Fragment> symbol : adjacentSymbols){
                    addGear(symbol,nb,gearsMap);
                }

                resultSet.add(nb);

            }
        }

        // Building list of gear with 2 nb adjacent
        int result_B = 0;

        for (GridElement<Fragment> gear : gearsMap.keySet()){
            List<Integer> nbList =  gearsMap.get(gear);

            if(nbList.size()==2){
                result_B+=nbList.get(0)*nbList.get(1);
            }
        }

        log.info("Final result: "+result_B);

    }



    //Test OK
    public List<GridElement<Fragment>> getSymbols(List<GridElement<Fragment>> input){
        List<GridElement<Fragment>> result =  input.stream()
                .filter(f -> f.getElement().isASymbol())
                .collect(Collectors.toList());


        result.forEach(e->log.debug(e.toString()));

        return result;

    }

    public List<GridElement<Fragment>> getSymbols(){
        return getSymbols(grid.getElements());
    }

    public List<GridElement<Fragment>> getNumbers(){
        List<GridElement<Fragment>> result =  grid.getElements().stream()
                .filter(f -> f.getElement().isANumber())
                .collect(Collectors.toList());


        result.forEach(e->log.debug(e.toString()));

        return result;

    }


    //TEST OK
    private void initMap(String resource){
        FileReaderUtils utils = new FileReaderUtils();

        try {
            List<String> data = utils.readFile(resource);

            for ( int line = 0 ; line < data.size() ; line++){

                char[] chars = data.get(line).toCharArray();

                for ( int col = 0; col < chars.length ; col++){
                    String value = String.valueOf(chars[col]);
                    // log.info("{} - {} - {}",col,line,value);
                    grid.add(col,line,new Fragment(value));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
