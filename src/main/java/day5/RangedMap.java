package day5;

import utils.math.Function;
import utils.math.Interval;
import utils.math.Proportional;

import java.util.*;

public class RangedMap  {

    // Source => destination
    // C est une liste de fonction, chaque fonction étant valable sur un intervalle
    List<Function> functions= new ArrayList<>();

    public void addRange(Double sourceStart, Double destinationStart,Double size){

        Function f = new Proportional(new Interval(sourceStart,size), destinationStart-sourceStart);
        functions.add(f);
    }


    List<Function> getFunctions(){
        return functions;
    }

    public Double getImage(Double x) {
       Optional<Function> function= functions.stream().
                filter(f -> f.isApplicable(x))
                .findFirst();

        if ( function.isPresent()){
            return function.get().getImage(x);
        } else
            return x;
    }


}
