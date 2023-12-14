package day5;

import lombok.extern.slf4j.Slf4j;
import utils.FileReaderUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Almanach {

    Map<Type,RangedMap> mappings = new HashMap<>();
    List<Double> seeds = new ArrayList<>();
    List<Range> seedRanges = new ArrayList<>();

    Almanach(String resource){
        super();

        FileReaderUtils utils = new FileReaderUtils();
        RangedMap currentMap = new RangedMap();
        try {
            List<String> data = utils.readFile(resource);
            for (String datum : data) {
                // Seed line
                if ( datum.startsWith("seeds")){
                    initSeeds(datum,seeds);

                    //Map declaration line
                } else if (datum.endsWith("map:")){
                    currentMap = new RangedMap();
                    Type type = extractType(datum);
                    mappings.put(type, currentMap);
                    //Map data line
                } else if ( !datum.trim().isEmpty()) {
                    addRange(datum,currentMap);
                }



            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Done");

        // Init maps
    }


    public Double resolveClosestSeed(){
        //location/seed
        Map<Double,Double> results = new HashMap<>();

        for (Double seed : seeds){
            Double location = applyMappings(seed);
            results.put(location,seed);
        }

        return results.keySet().stream().min(Double::compare).get();
    }


    public Double applyMappings(Double seed){
        Double curValue = seed;
        for (Type type : Type.getSortedValues()){
            RangedMap map = mappings.get(type);
          //  curValue= (Double) map.get(curValue);
            log.info("Applying {}, result is {}",type,curValue);
        }

        return curValue;
    }

    // Test OK
    public static Type extractType(String data){
        return  Type.valueOf(data.split("-")[2].split(" ")[0].toUpperCase());
    }


    //Test OK
    public void addRange(String rangeS,RangedMap rangedMap){
        String[] parsedRange = rangeS.split(" ");
        double sourceStart = Double.parseDouble(parsedRange[1]);
        double destStart = Double.parseDouble(parsedRange[0]);
        double range = Double.parseDouble(parsedRange[2]);

        rangedMap.addRange(sourceStart,destStart,range);
    }

    // seeds: 79 14 55 13
    public static void initSeeds(String seedsS,List<Double> seeds){
        String[] seedsA = seedsS.split("\\:")[1].split("\\ ");

        Arrays.stream(seedsA).filter(s -> !s.isEmpty()).forEach(seed -> seeds.add(Double.parseDouble(seed)));
    }




}
