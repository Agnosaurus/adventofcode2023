package day5;

import java.util.Arrays;
import java.util.List;

public enum Type {

    SEED(0),
    SOIL(1),
    FERTILIZER(2),
    WATER(3),
    LIGHT(4),
    TEMPERATURE(5),
    HUMIDITY(6),
    LOCATION(7);


    int index;

    Type(int index){
        this.index=index;
    }

    public static Type get(int index){
        return Arrays.stream(values()).filter(type -> type.index == index).findFirst().get();
    }

    public static List<Type> getSortedValues(){
        return List.of(SOIL,FERTILIZER,WATER,LIGHT,TEMPERATURE,HUMIDITY,LOCATION);
    }
}
